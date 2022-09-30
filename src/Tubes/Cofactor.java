
package Tubes;

public class Cofactor {
    
    public static double[][] transpose(double[][] m) {
        // transpose matrix
        int row = m.length;
        int col = m[0].length;
        double[][] trans = new double[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                trans[j][i] = m[i][j];

            }
        }
        return trans;
    }


    public static void getCofactor(double[][] m, double[][] temp, int p, int q) {
        // Mendapatkan kofaktor dari m dan menyimpannya di temp
        int n = m.length;
        
        // for (int i = 0; i < n; i++) {
        //     int a = 0;
        //     for (int j = 0; j < n; j++) {
        //         int b = 0;
        //         for (int k = 0; k < n; k ++){
        //             if (k == i) {
        //                 continue;
        //             }
        //             temp[a][b] = m[j][k];
        //             b++;
        //         }
        //         a++;
        //     }
        // }

        int i = 0, j = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != p && col != q) {
                    temp[i][j++] = m[row][col];
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }


    public static double determinant(double[][] m, int n) {
        // Mendapatkan determinan dari m secara rekursif menggunakan metode kofaktor
        double det = 0;
        if (n == 1) {
            return m[0][0];
        }
        double[][] temp = new double[n][n];
        int sign = 1;
        for (int i = 0; i < n; i++) {
            getCofactor(m, temp, 0, i);
            det += sign * m[0][i] * determinant(temp, n - 1);
            sign = -sign;
        }
        return det;
    }

    public static double[][] adjoint(double[][] m){
        // Mendapatkan matriks adjoin dari m secara rekursif
        int n = m.length;
        double[][] adj = new double[n][n];
        if (n == 1){
            adj[0][0] = 1;
            return adj;
        }
        int sign = 1;
        double[][] temp = new double[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                getCofactor(m, temp, i, j);
                sign = ((i + j) % 2 == 0) ? 1 : -1;
                adj[j][i] = (sign) * (determinant(temp, n - 1));
            }
        }
        return adj;
    }

    public static double[][] inverse(double[][] m){
        // Mendapatkan matriks invers dari m dari adjoint dan determinan
        int n = m.length;
        double[][] inv = new double[n][n];
        double det = determinant(m, n);
        if (det == 0){
            System.out.println("Determinan 0, tidak memiliki invers");
            return inv;
        }
        double[][] adj = adjoint(m);
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                inv[i][j] = adj[i][j] / det;
            }
        }
        return inv;
    }
}

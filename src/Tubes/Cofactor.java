
package Tubes;

public class Cofactor {
    
    public static double[][] transpose(double[][] m) {
        // transpose matrix
        int row = m.length;
        int col = m[0].length;
        double[][] trans = new double[col][row];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                trans[j][i] = m[i][j];

            }
        }
        return trans;
    }


    public static void getCofactor(double[][] m, double[][] temp, int a, int b, int n) {
        // Mendapatkan kofaktor dari m dan menyimpannya di temp
        
        int row , col;
        row = col = 0;
        
        for (int i = 0 ; i < n ; i++){
            for (int j = 0 ; j < n ; j++){
                if (i != a && j != b){
                    temp[row][col++] = m[i][j];
                    if (col == n - 1){
                        col = 0;
                        row++;
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
            getCofactor(m, temp, 0, i, n);
            det += sign * m[0][i] * determinant(temp, n - 1);
            sign = -sign;
        }
        return det;
    }

    public static double[][] adjoint(double[][] m){
        // Mendapatkan matriks adjoin dari m secara rekursif
        int n = m.length;
        double[][] adj = new double[n][n];
        // if (n == 1){
        //     adj[0][0] = 1;
        //     return adj;
        // }
        int sign = 1;
        double[][] temp = new double[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                getCofactor(m, temp, i, j, n);
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

package LinearAlgebraMatrixOperations;

public class OperasiInverse {

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
                OperasiPrimitif.getCofactor(m, temp, i, j, n);
                sign = ((i + j) % 2 == 0) ? 1 : -1;
                adj[j][i] = (sign) * (OperasiDeterminan.DeterminanCofactor(temp, n - 1));
            }
        }
        return adj;
    }
    public static double[][] inverseCofactor(double[][] m){
        // Mendapatkan matriks invers dari m dari adjoint dan determinan
        int n = m.length;
        double[][] inv = new double[n][n];
        double det = OperasiDeterminan.DeterminanCofactor(m, n);
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
    
    public static void identity(double[][] matrix){
        int i, j, k;
        int m = matrix.length;
        double[][] copy = new double[m][m];
        double[][] identity = new double[m][m];

        // Check if matrix has inverse
        if(OperasiDeterminan.DeterminanOBE(matrix) == 0){
            System.out.println("Matriks tidak memiliki balikan");
        } else{
            // Copy Matrix
            OperasiPrimitif.copyMatrix(matrix, copy);

            // Create Identity Matrix
            for(i = 0; i < m; i++){
                for(j = 0; j < m; j++){
                    if(i == j){
                        identity[i][j] = 1d;
                    } else{
                        identity[i][j] = 0d;
                    }
                }
            }

            // Elimination
            OperasiPrimitif.gauss_jordan_inv(copy, identity);
            
            // Store inverse value to matrix
            for(i = 0; i < m; i++){
                for(j = 0; j < m; j++){
                    matrix[i][j] = identity[i][j];
                }
            }
        }
    }
}

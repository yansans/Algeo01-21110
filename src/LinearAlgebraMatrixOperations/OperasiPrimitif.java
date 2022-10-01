package LinearAlgebraMatrixOperations;

public class OperasiPrimitif {
    public static void copyMatrix(double[][] matrix, double[][] copy){
        int i, j;

        for(i = 0; i < matrix.length; i++){
            for(j = 0; j < matrix[0].length; j++){
                copy[i][j] = matrix[i][j];
            }
        }
    }

    public static double[][] PerkalianMatrix(double[][] matrix1, double[][] matrix2){
        int n = matrix1.length;
        int m = matrix2[0].length;
        int o = matrix1[0].length;
        double[][] matrix3 = new double[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                for(int k=0;k<o;k++){
                    matrix3[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return matrix3;
    }

    public static double[][] PerkalianMatrixKonstanta(double[][] matrix, double konstanta){
        int n = matrix.length;
        int m = matrix[0].length;
        double[][] matrix2 = new double[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                matrix2[i][j] = matrix[i][j] * konstanta;
            }
        }
        return matrix2;
    }

    public static double[] SolusiSPL(double[][] matrix, double[] nilai){
        int dimensi = matrix.length, col = matrix[0].length;
        double[] solusi = new double[col];
        for(int i=0;i<dimensi;i++){
            for(int j=0;j<dimensi;j++){
                solusi[i] += matrix[i][j] * nilai[j];
            }
        }
        return solusi;
    }

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

}

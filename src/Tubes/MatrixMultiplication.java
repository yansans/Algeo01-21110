package Tubes;

public class MatrixMultiplication {
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
        int dimensi = matrix.length;
        double[] solusi = new double[dimensi];
        for(int i=0;i<dimensi;i++){
            for(int j=0;j<dimensi;j++){
                solusi[i] += matrix[i][j] * nilai[j];
            }
        }
        return solusi;
    }

    public static void main(String args[]){

    }
}

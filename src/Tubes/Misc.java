package Tubes;

public class Misc {
    public static void copyMatrix(double[][] matrix, double[][] copy){
        int i, j;

        for(i = 0; i < matrix.length; i++){
            for(j = 0; j < matrix[0].length; j++){
                copy[i][j] = matrix[i][j];
            }
        }
    }
}
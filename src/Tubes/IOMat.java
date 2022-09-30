package Tubes;
import java.util.Scanner;
public class IOMat {

    public static void printMatrix(double[][] m) {
        int col = m[0].length;
        for (double[] doubles : m) {
            for (int j = 0; j < col; j++) {
                System.out.print(doubles[j]);
                if (j != col - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    
    public static double[][] inputMatrix(double[][] m, Scanner scan) {
        int row = m.length;
        int col = m[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                m[i][j] = scan.nextDouble();
            }
        }
        return m;
    } 
}

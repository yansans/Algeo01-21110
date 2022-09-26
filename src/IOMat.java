package iomat;
import java.util.Scanner;
public class IOMat {

    public static void printMatrix(float[][] m) {
        int col = m[0].length;
        for (float[] floats : m) {
            for (int j = 0; j < col; j++) {
                System.out.print(floats[j]);
                if (j != col - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    
    public static float[][] inputMatrix(float[][] m, Scanner scan) {
        int row = m.length;
        int col = m[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                m[i][j] = scan.nextFloat();
            }
        }
        return m;
    } 
}

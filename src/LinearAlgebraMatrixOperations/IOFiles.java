package LinearAlgebraMatrixOperations;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter; // Import the FileWriter class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.NoSuchElementException;
import java.util.Scanner; // Import the Scanner class to read text files
import static Tubes.IOMat.printMatrix;
import java.io.LineNumberReader;

public class IOFiles {

    public static double[][] readMatrix(String file){
        int row, col;
        double[][] mat;
        row = col = 0;
        row = col = 3;
        try {
            File matrix = new File(file);
            Scanner scan_row = new Scanner(matrix);
            System.out.println("Try");
//            while (scan_row.hasNextLine()) {
//                row++;
//                System.out.println("Line");
////                Scanner scan_col = new Scanner(scan_row.nextLine());
////                while (scan_col.hasNextDouble()) {
////                    col++;
////                    System.out.println("Row");
////                    scan_col.nextDouble();
//
//                scan_row.nextLine();
//            }
            System.out.println("scan 1");
            System.out.println(row);
            System.out.println(col);
            mat = new double[row][col];

            for (int i = 0; i < row ; i++){
                for (int j = 0 ; j < col ; j++){
                    if (scan_row.hasNextLine()){
                        mat[i][j] = scan_row.nextDouble();
                        }
                    }
                }

            System.out.println("scan 2");

//            double [][] x = new double[row -1][col];
//            double [][] y = new double[row -1][1];
//
//            for (int i = 0; i < row ; i++){
//                for (int j = 0 ; j < col ; j++){
//                    if (scan_row.hasNextLine()){
//                        if (j == col -1) y[i][0] = scan_row.nextInt();
//                        else{
//                            x[i][j] = scan_row.nextInt();
//                        }
//                    }
//                }
//            }

            scan_row.close();
            return mat;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return mat = new double[0][0];
    }
    public static void main(String[]args){
        double[][] matrix = new double[3][3];
        matrix = readMatrix("filename.txt");
        printMatrix(matrix);
    }
}

package LinearAlgebraMatrixOperations;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter; // Import the FileWriter class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class IOFiles {
    public static boolean readMatrix(String file, double[][] augmented){
        int row, col;
        row = col = 0;
        try {
            File matrix = new File(file);
            Scanner scan_row = new Scanner(matrix);
            while (scan_row.hasNextLine()) {
                ++row;
                Scanner scan_col = new Scanner(scan_row.nextLine());
                while (scan_col.hasNextLine()) ++col;
            }

            double [][] x = new double[row -1][col];
            double [][] y = new double[row -1][1];

            for (int i = 0; i < row ; i++){
                for (int j = 0 ; j < col ; j++){
                    if (scan_row.hasNextLine()){
                        if (j == col -1) y[i][0] = scan_row.nextInt();
                        else{
                            x[i][j] = scan_row.nextInt();
                        }
                    }
                }
            }

            scan_row.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }
}

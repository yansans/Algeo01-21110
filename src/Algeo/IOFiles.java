package src.Algeo;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static Tubes.IOMat.printMatrix;

public class IOFiles {

    public static boolean readMatrix(String file, double[][] mat) throws FileNotFoundException{
        int row, col;
        row = col = 0;
//        try {
            File matrix = new File(file);
            Scanner scan_row = new Scanner(matrix);
            while (scan_row.hasNextLine()) {
                row++;
                Scanner scan_col = new Scanner(scan_row.nextLine());
                int n = 0;
                while(scan_col.hasNextDouble()){
                    n++;
                    scan_col.nextDouble();
                }
                col = n;
            }
            scan_row.close();
            Scanner scan = new Scanner(matrix);


            mat = new double[row][col];

            for (int i = 0; i < row ; i++){
                for (int j = 0 ; j < col ; j++){
                    if (scan.hasNextLine()){
                        mat[i][j] = scan.nextDouble();
                    }
                }
            }
            scan.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("File tidak ditemukan.");
//            e.printStackTrace();
//        }
        return mat;
    }

    public static boolean writeMatrix(String file, double[][] matriks) throws IOException{
//        try {
            File myObj = new File(file);
            if (myObj.createNewFile()) {
                System.out.println("File dibuat: " + myObj.getName());
            } else {
                System.out.println("File sudah ada.");
                System.out.println("File akan di-overwrite ada.");
            }
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
        try {
            FileWriter Writer = new FileWriter(file);
            for (int i = 0 ; i < matriks.length ; i++){
                for (int j = 0; j < matriks[0].length; j++){
                    Writer.write(matriks[i][j] + ((j != matriks[0].length -1)? " " : "\n"));
                }
            }
            Writer.close();
            System.out.println("File berhasil ditulis.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return true;
    }

        public static void main(String[]args){
        double[][] matrix = new double[0][0];
        try {
            matrix = readMatrix("fileread1.txt");
        }catch (FileNotFoundException e){
            System.out.println("File tidak ditemukan");
        }
        System.out.println("1");
        printMatrix(matrix);
        try {
            writeMatrix("filewrite.txt", matrix);
        }catch (IOException e){
            System.out.println("Terjadi error.");
        }
    }
}

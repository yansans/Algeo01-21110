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
            System.out.println(col);
            System.out.println(row);
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
        return true;
    }

    public static boolean writeMatrix(String file, double[][] matriks) throws IOException{
//        try {
            File myObj = new File(file);
            if (myObj.createNewFile()) {
                System.out.println("File dibuat: " + myObj.getName());
            } else {
                System.out.println("File sudah ada.");
                System.out.println("File akan di-overwrite.");
            }
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//        try {
            FileWriter Writer = new FileWriter(file);
            for (int i = 0 ; i < matriks.length ; i++){
                for (int j = 0; j < matriks[0].length; j++){
                    Writer.write(matriks[i][j] + ((j != matriks[0].length -1)? " " : "\n"));
                }
            }
            Writer.close();
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
        return true;
    }

//        public static void main(String[]args){
//        double[][] matrix = new double[0][0];
//        boolean status = false;
//        Scanner scan = new Scanner(System.in);
//        String a = scan.nextLine();
//        try {
//            status = readMatrix(a, matrix);
//        }catch (FileNotFoundException e){
//            System.out.println("File tidak ditemukan");
//        }
//        if (status) {
////                printMatrix(matrix);
//                try {
//                    writeMatrix("bin/filewrite.txt", matrix);
//                } catch (IOException e) {
//                    System.out.println("Terjadi error.");
//                }
//            }
//        else {
//            System.out.println("File tidak ditemukan. Coba lagi.");
//            }
//    }
}

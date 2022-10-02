package src.Algeo;

import java.io.*;
import java.util.Scanner;
import static Tubes.IOMat.printMatrix;

public class IOFiles {

    public static double[][] readMatrix(String file) throws FileNotFoundException{
        int row, col;
        row = col = 0;

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
            double[][] mat;
            mat = new double[row][col];

            for (int i = 0; i < row ; i++){
                for (int j = 0 ; j < col ; j++){
                    if (scan.hasNextLine()){
                        mat[i][j] = scan.nextDouble();
                    }
                }
            }
            scan.close();
        return mat;
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

            FileWriter Writer = new FileWriter(file);
            for (int i = 0 ; i < matriks.length ; i++){
                for (int j = 0; j < matriks[0].length; j++){
                    Writer.write(matriks[i][j] + ((j != matriks[0].length -1)? " " : "\n"));
                }
            }
            Writer.close();
        return true;
    }

    public static boolean writePrint(String file) throws IOException  {

        File myObj = new File(file);
        if (myObj.createNewFile()) {
            System.out.println("File dibuat: " + myObj.getName());
        } else {
            System.out.println("File sudah ada.");
            System.out.println("File akan di-overwrite.");
        }
        PrintStream o = new PrintStream(myObj);
        PrintStream console = System.out;
        System.setOut(o);
        int m = 5;

        System.out.print("f(x) = ");
        for (double i = 0; i < m; i++) {
            if (i != m - 1) {
                if (i != 0) {
                    System.out.printf("%.4f", i);
                    System.out.print("x" + i + " + ");
                } else {
                    System.out.printf("%.4f + ", i);
                }
            } else {
                System.out.printf("%.4f", i);
                System.out.print("x" + i);
            }

        }
        return true;
    }

    public static double[][] readBicubic(String file)throws FileNotFoundException{
        int row, col;
        row = col = 4;
        File matrix = new File(file);

        double[][] mat;
        mat = new double[row][col];
        Scanner scan = new Scanner(matrix);
        for (int i = 0; i < row ; i++){
            for (int j = 0 ; j < col ; j++){
                if (scan.hasNextLine()){
                    mat[i][j] = scan.nextDouble();
                }
            }
        }
        scan.close();
        return mat;
    }

    public static double[][] readBicubicValue(String file)throws FileNotFoundException {
        int row, col;
        row = 1;
        col = 2;
        double[][] mat;
        File matrix = new File(file);
        mat = new double[col][row];
        Scanner scan = new Scanner(matrix);
        for (int i = 0 ; i < 4; i++){
            scan.nextLine();
        }
        for (int i = 0; i < col; i++){
            mat[i][0] = scan.nextDouble();
        }
        return mat;
    }

        public static void main(String[]Args){
        double[][] matrix = new double[1][1];
//        try {
//            matrix = readBicubicValue("fileread.txt");
//        }catch (FileNotFoundException e){
//            System.out.println("file tidak ada");
//        }
//        printMatrix(matrix);
        try {
            writePrint("filewrite.txt");
        }catch (IOException e){
            System.out.println("file tidak ada");
        }

    }
}

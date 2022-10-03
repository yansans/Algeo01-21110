package src.Algeo;

import java.io.*;
import java.util.Scanner;

import static src.Algeo.DoubleLinearReg.doubRegSolveIdent;
import static src.Algeo.IOTerminal.*;

public class IOFiles {
    public static double[][] readMatrix(String file) throws FileNotFoundException {
        int row, col;
        row = col = 0;

        File matrix = new File(file);
        Scanner scan_row = new Scanner(matrix);
        while (scan_row.hasNextLine()) {
            row++;
            Scanner scan_col = new Scanner(scan_row.nextLine());
            int n = 0;
            while (scan_col.hasNextDouble()) {
                n++;
                scan_col.nextDouble();
            }
            col = n;
        }
        scan_row.close();
        Scanner scan = new Scanner(matrix);
        double[][] mat;
        mat = new double[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (scan.hasNextLine()) {
                    mat[i][j] = scan.nextDouble();
                }
            }
        }
        scan.close();
        return mat;
    }


    public static void filesSPLCrammer(String file) {
        double[][] matrix = new double[1][1];
        try{
            matrix = readMatrix(file);
        }catch (FileNotFoundException e){
            System.out.println("File tidak ditemukan.");
            return;
        }
        double[][] Matrix = persamaanSPLAugmented(matrix);
        double[] nilai = nilaiSPLAugmented(matrix);
        boolean adaSolusi = cekDeterminan(Matrix);
        System.out.println("Masukkan nama file yang akan disimpan : ");
        Scanner input  = new Scanner(System.in);
        String name = input.nextLine();
        try {
            writeSPLCrammer(name, Matrix,nilai , adaSolusi);
        }catch (IOException e){
            System.out.println("Terjadi kesalahan.");
        }
    }

    public static void filesSPLInverse(String file) {
        double[][] matrix = new double[1][1];
        try{
            matrix = readMatrix(file);
        }catch (FileNotFoundException e){
            System.out.println("File tidak ditemukan.");
            return;
        }
        double[][] Matrix = persamaanSPLAugmented(matrix);
        double[] nilai = nilaiSPLAugmented(matrix);
        boolean adaSolusi = cekDeterminan(Matrix);
        System.out.println("Masukkan nama file yang akan disimpan : ");
        Scanner input  = new Scanner(System.in);
        String name = input.nextLine();
        try {
            writeSPLInverse(name, Matrix,nilai , adaSolusi);
        }catch (IOException e){
            System.out.println("Terjadi kesalahan.");
        }
    }

    public static void filesSPLGaussJordan(String file) {
        double[][] matrix = new double[1][1];
        try{
            matrix = readMatrix(file);
        }catch (FileNotFoundException e){
            System.out.println("File tidak ditemukan.");
            return;
        }
        double[][] Matrix = persamaanSPLAugmented(matrix);
        double[] nilai = nilaiSPLAugmented(matrix);
        boolean adaSolusi = cekDeterminan(Matrix);
        System.out.println("Masukkan nama file yang akan disimpan : ");
        Scanner input  = new Scanner(System.in);
        String name = input.nextLine();
        try {
            writeSPLGaussJordan(name, Matrix,nilai , adaSolusi);
        }catch (IOException e){
            System.out.println("Terjadi kesalahan.");
        }
    }

    public static void filesSPLGauss(String file) {
        double[][] matrix = new double[1][1];
        try{
            matrix = readMatrix(file);
        }catch (FileNotFoundException e){
            System.out.println("File tidak ditemukan.");
            return;
        }
        double[][] Matrix = persamaanSPLAugmented(matrix);
        double[] nilai = nilaiSPLAugmented(matrix);
        boolean adaSolusi = cekDeterminan(Matrix);
        System.out.println("Masukkan nama file yang akan disimpan : ");
        Scanner input  = new Scanner(System.in);
        String name = input.nextLine();
        try {
            writeSPLGauss(name, Matrix,nilai , adaSolusi);
        }catch (IOException e){
            System.out.println("Terjadi kesalahan.");
        }
    }


    public static void writeSPLGauss(String file, double[][] Matrix, double[] nilai, boolean adaSolusi)
            throws IOException{
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
        if(adaSolusi){
            double[] solusi = OperasiSPL.SPLgauss(Matrix, nilai);
            try {
                writeArray(file,solusi);
            }catch (IOException e){
                System.out.println("Terjadi kesalahan.");
            }
        }else{
            System.out.print("Tidak ada solusi.");
        }
    }

    public static void writeSPLGaussJordan(String file, double[][] Matrix, double[] nilai, boolean adaSolusi)
            throws IOException{
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
        if(adaSolusi){
            double[] solusi = OperasiSPL.SPLgauss_jordan(Matrix, nilai);
            try {
                writeArray(file,solusi);
            }catch (IOException e){
                System.out.println("Terjadi kesalahan.");
            }
        }else{
            System.out.print("Tidak ada solusi.");
        }
    }
    public static void writeSPLInverse(String file, double[][] Matrix, double[] nilai, boolean adaSolusi)
            throws IOException{
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
        if(adaSolusi){
            double[] solusi = OperasiSPL.SolusiSPLInverse(Matrix, nilai);
            try {
                writeArray(file,solusi);
            }catch (IOException e){
                System.out.println("Terjadi kesalahan.");
            }
        }else{
            System.out.print("Tidak ada solusi.");
        }
    }

    public static void writeSPLCrammer(String file, double[][] Matrix, double[] nilai, boolean adaSolusi)
        throws IOException{
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
        if(adaSolusi){
            double[] solusi = OperasiSPL.SolusiCrammer(Matrix, nilai);
            try {
                writeArray(file,solusi);
            }catch (IOException e){
                System.out.println("Terjadi kesalahan.");
            }
        }else{
            System.out.print("Tidak ada solusi.");
        }
    }

    public static void filesDeterminanOBE(String file)  {
        double[][] matrix = new double[1][1];
        try{
            matrix = readMatrix(file);
        }catch (FileNotFoundException e){
            System.out.println("File tidak ditemukan.");
            return;
        }
        System.out.println("Masukkan nama file yang akan disimpan : ");
        Scanner input  = new Scanner(System.in);
        String name = input.nextLine();
        try {
            writeDeterminanOBE(name,matrix);
        }catch (IOException e){
            System.out.println("Terjadi kesalahan.");
        }
        input.close();
    }

    public static void filesDeterminanCofactor(String file)  {
        double[][] matrix = new double[1][1];
        try{
            matrix = readMatrix(file);
        }catch (FileNotFoundException e){
            System.out.println("File tidak ditemukan.");
            return;
        }
        System.out.println("Masukkan nama file yang akan disimpan : ");
        Scanner input  = new Scanner(System.in);
        String name = input.nextLine();
        try {
            writeDeterminanCofactor(name,matrix);
        }catch (IOException e){
            System.out.println("Terjadi kesalahan.");
        }
        input.close();
    }

    public static void writeDeterminanCofactor(String file, double[][]Matrix ) throws IOException{
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
        if(Matrix.length != Matrix[0].length){
            System.out.print("Matrix bukan matrix segitiga, tidak memiliki determinan");
        }else{
            double determinan = OperasiDeterminan.DeterminanCofactor(Matrix, Matrix.length);
            System.out.println("Determinan Matrix : " + determinan);
        }
    }

    public static void writeDeterminanOBE(String file, double[][]Matrix ) throws IOException{
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
        if(Matrix.length != Matrix[0].length){
            System.out.print("Matrix bukan matrix segitiga, tidak memiliki determinan");
        }else{
            double determinan = OperasiDeterminan.DeterminanOBE(Matrix);
            System.out.println("Determinan Matrix : " + determinan);
        }
    }

    public static void filesInverseOBE(String file) {
        double[][] matrix = new double[1][1];
        try{
            matrix = readMatrix(file);
        }catch (FileNotFoundException e){
            System.out.println("File tidak ditemukan.");
            return;
        }
        double det = OperasiDeterminan.DeterminanOBE(matrix);
        System.out.println("Masukkan nama file yang akan disimpan : ");
        Scanner input  = new Scanner(System.in);
        String name = input.nextLine();
        try{
            writeInverseOBE(name, matrix, det);
        }catch (IOException e){
            System.out.println("Terjadi kesalahan.");
        }
    }
    public static void filesInverseAdjoint(String file) {
        double[][] matrix = new double[1][1];
        try{
            matrix = readMatrix(file);
        }catch (FileNotFoundException e){
            System.out.println("File tidak ditemukan.");
            return;
        }
        double det = OperasiDeterminan.DeterminanCofactor(matrix, matrix.length);
        System.out.println("Masukkan nama file yang akan disimpan : ");
        Scanner input  = new Scanner(System.in);
        String name = input.nextLine();
        try{
            writeInverseAdjoint(name, matrix, det);
        }catch (IOException e){
            System.out.println("Terjadi kesalahan.");
        }
    }
    public static void writeInverseOBE(String file, double[][] Matrix, double determinan)
            throws IOException{
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
        if(determinan == 0 || Matrix.length != Matrix[0].length){
            System.out.println("Matrix tidak memiliki Invers");
        }else {
            OperasiInverse.inverseIdentity(Matrix);
            writeMatrix(file,Matrix);
        }
    }

    public static void writeInverseAdjoint(String file, double[][] Matrix, double determinan)
            throws IOException{
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
        if(determinan == 0 || Matrix.length != Matrix[0].length){
            System.out.println("Matrix tidak memiliki Invers");
        }else {
            Matrix = OperasiInverse.inverseCofactor(Matrix);
            writeMatrix(file,Matrix);
        }
    }


    public static double[][] readBicubic(String file) throws FileNotFoundException {
        int row, col;
        row = col = 4;
        File matrix = new File(file);

        double[][] mat;
        mat = new double[row][col];
        Scanner scan = new Scanner(matrix);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (scan.hasNextLine()) {
                    mat[i][j] = scan.nextDouble();
                }
            }
        }
        scan.close();
        return mat;
    }
    public static double[][] readBicubicValue(String file) throws FileNotFoundException {
        int row, col;
        row = 1;
        col = 2;
        double[][] mat;
        File matrix = new File(file);
        mat = new double[col][row];
        Scanner scan = new Scanner(matrix);
        for (int i = 0; i < 4; i++) {
            scan.nextLine();
        }
        for (int i = 0; i < col; i++) {
            mat[i][0] = scan.nextDouble();
        }
        return mat;
    }

    public static void writeBicubic(String file, double[] nilai, double ax, double ay)throws IOException{
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
        double interpolasi = InterpolasiBicubic.interpolasiBicubic(nilai, ax, ay);
        System.out.printf("Nilai f(%f,%f) hasil interpolasi adalah : %f\n", ax, ay, interpolasi);
    }

    public static double[][] readPolinom(String file) throws FileNotFoundException {
        int row, col;
        row = col = 0;

        File matrix = new File(file);
        Scanner scan_row = new Scanner(matrix);
        while (scan_row.hasNextLine()) {
            row++;
            Scanner scan_col = new Scanner(scan_row.nextLine());
            while (scan_col.hasNextDouble()) {
                col++;
                scan_col.nextDouble();
            }
        }
        System.out.println(row);
        System.out.println(col);
        scan_row.close();
        Scanner scan = new Scanner(matrix);
        double[][] mat;
        mat = new double[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (scan.hasNextLine()) {
                    mat[i][j] = scan.nextDouble();
                }
            }
        }
        scan.close();
        return mat;
    }

    public static boolean writeArray(String file, double[] array) throws IOException {

        File myObj = new File(file);
        if (myObj.createNewFile()) {
            System.out.println("File dibuat: " + myObj.getName());
        } else {
            System.out.println("File sudah ada.");
            System.out.println("File akan di-overwrite.");
        }

        FileWriter Writer = new FileWriter(file);
        for (int i = 0; i < array.length; i++) {
                Writer.write(array[i] + ((i != array.length - 1) ? " " : "\n"));
        }
        Writer.close();
        return true;
    }
    public static boolean writeMatrix(String file, double[][] matriks) throws IOException {

        File myObj = new File(file);
        if (myObj.createNewFile()) {
            System.out.println("File dibuat: " + myObj.getName());
        } else {
            System.out.println("File sudah ada.");
            System.out.println("File akan di-overwrite.");
        }

        FileWriter Writer = new FileWriter(file);
        for (int i = 0; i < matriks.length; i++) {
            for (int j = 0; j < matriks[0].length; j++) {
                Writer.write(matriks[i][j] + ((j != matriks[0].length - 1) ? " " : "\n"));
            }
        }
        Writer.close();
        return true;
    }

    public static void inputFilesDoubleReg (String file){
        int n,m;
        double [][] aug;
        double[][] Matrix;
        double[][] nilai;
        double[][] var ;
        Scanner scan = new Scanner(System.in);
        try {
            aug = readMatrix(file);
        }catch (FileNotFoundException e){
            System.out.println("File tidak ditemukan.");
            return;
        }
        n = aug.length;
        m = aug[0].length;
        Matrix = new double[n][m-1];
        nilai = new double[n][1];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m-1;j++ ){
                Matrix[i][j] = aug[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
                nilai[i][0] = aug[i][m-1];
        }
        System.out.println("Masukkan semua nilai x yang ingin di taksir : ");
        var = InputRegresiY(m-2, scan);
        System.out.println("Masukkan nama file yang akan disimpan : ");
        Scanner input  = new Scanner(System.in);
        String name = input.nextLine();
        try {
            writeDoubleReg(name, Matrix, nilai, var);
        } catch (IOException e) {
            System.out.println("file tidak ada");
        }
        scan.close();
        input.close();
    }

    public static void writeDoubleReg(String file, double[][] x, double[][] y, double[][] a)
            throws IOException {

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

        double[][] solution = doubRegSolveIdent(x, y);

        int m = solution.length, n = solution[0].length;

        // Output Interpolation Result
        System.out.print("f(x) = ");
        for (int i = 0; i < m; i++) {
            if (i != m - 1) {
                if (i != 0) {
                    System.out.printf("%.4f", solution[i][0]);
                    System.out.print("x" + i + " + ");
                } else {
                    System.out.printf("%.4f + ", solution[i][0]);
                }
            } else {
                System.out.printf("%.4f", solution[i][0]);
                System.out.print("x" + i);
            }
        }
        System.out.println();
        double est = solution[0][0];
        System.out.print("f(xk) = ");
        for (int i = 1; i < m; i++) {
            est += solution[i][0] * a[i - 1][0];
        }
        System.out.printf("%.4f", est);
    }


    public static void inputFilesPolinom(String file){
        double[][] MatrixAugmented;
        try {
            MatrixAugmented = readMatrix(file);
        }catch (FileNotFoundException e){
            System.out.println("File tidak ditemukan.");
            System.out.println("Kembali ke menu sebelumnya.");
            return;
        }
        int n = MatrixAugmented.length;
        int m = MatrixAugmented[0].length;
        double[][] Matrix = new double[n][m-1];
        double[] nilai = new double[n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                Matrix[i][j] = MatrixAugmented[i][j];
            }
        }
        for(int j=0;j<n;j++){
            nilai[j] = MatrixAugmented[j][m-1];
        }

        Scanner scan = new Scanner(System.in);
        System.out.print("Masukkan nilai x yang ingin di taksir : ");
        double x = scan.nextDouble();
        System.out.println("Masukkan nama file yang akan disimpan : ");
        Scanner input  = new Scanner(System.in);
        String name = input.nextLine();
        try{
            writePolinom(name,Matrix, nilai, x);
        }catch (IOException e){
            System.out.print("Terjadi kesalahan.");
        }
        scan.close();
        input.close();

    }
    public static void writePolinom(String file, double[][] A, double[] b, double x)
            throws IOException {

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
        int i, j;
        int m = A.length, n = A[0].length;
        double y = 0d;
        double[] solution = new double[n];

        // Solve SPL
        solution = OperasiSPL.SPLgauss_jordan(A, b);

        // Taksiran
        for (i = 0; i < n; i++) {
            y += solution[i] * Math.pow(x, i);
        }

        // Output Interpolation Result
        System.out.print("f(x) = ");
        System.out.printf("%.4f", solution[n - 1]);
        System.out.print("x^" + (n - 1));
        for (i = n - 2; i >= 2; i--) {
            if (solution[i] >= 0) {
                System.out.print(" + ");
                System.out.printf("%.4f", solution[i]);
                System.out.print("x^" + i);
            } else {
                System.out.print(" - ");
                System.out.printf("%.4f", solution[i]);
                System.out.print("x^" + i);
            }
        }
        if (solution[1] >= 0) {
            System.out.print(" + ");
            System.out.printf("%.4f", solution[1]);
            System.out.print("x");
        } else {
            System.out.print(" - ");
            System.out.printf("%.4f", solution[1]);
            System.out.print("x");
        }
        if (solution[0] >= 0) {
            System.out.print(" + ");
            System.out.printf("%.4f,\n", solution[0]);
        } else {
            System.out.print(" - ");
            System.out.printf("%.4f,\n", solution[0]);
        }
        System.out.print("f(" + x + ") = ");
        System.out.printf("%.4f\n", y);
    }

    public static void main(String[] Args) {
        double[][] matrix = new double[1][1];
//        try {
//            matrix = readPolinom("fileread.txt");
//        } catch (FileNotFoundException e) {
//            System.out.println("file tidak ada");
//        }

        filesSPLCrammer("fileread.txt");


        }
    }


package src.Algeo;
import java.io.IOException;
import java.util.*;

import static src.Algeo.IOFiles.*;


public class IOTerminal {
    public static double[][] InputMatrix(){
        int n,m;
        Scanner scan = new Scanner(System.in);
        System.out.println("Masukkan dimensi matrix n,m : ");
        System.out.print("n : ");
        n = scan.nextInt();
        System.out.print("m : ");
        m = scan.nextInt();
        System.out.println("Berikan input matrix : ");
        double[][] MatrixOut = new double[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                MatrixOut[i][j] = scan.nextDouble();
            }
        }
        scan.close();
        return MatrixOut;
    }
    
    public static double[][] InputSPLAugmented(){
        int n,m;
        Scanner scan = new Scanner(System.in);
        System.out.println("Masukkan dimensi matrix n,m : ");
        System.out.print("n : ");
        n = scan.nextInt();
        System.out.print("m : ");
        m = scan.nextInt();
        System.out.println("Berikan input matrix augmented : ");
        double[][] MatrixSPL = new double[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                MatrixSPL[i][j] = scan.nextDouble();
            }
        }
        scan.close();
        return MatrixSPL;
    }

    public static double[][] InputRegresiX(int n, int m , Scanner scan){
        System.out.println("Berikan input regresi: ");
        double[][] MatrixSPL = new double[m][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                MatrixSPL[i][j] = scan.nextDouble();
            }
        }
        return MatrixSPL;
    }

    public static double[][] InputRegresiY(int m, Scanner scan){
        double[][] MatrixSPL = new double[m][1];
        for(int i=0;i<m;i++){
                MatrixSPL[i][0] = scan.nextDouble();
            }
        return MatrixSPL;
    }


    public static void DisplayArray(double[] array){
        for(int j=0;j<array.length;j++){
            System.out.print(array[j] + " ");
        }
        System.out.println();
    }

    public static void DisplayMatrix(double[][] Matrix){
        for(int i=0;i<Matrix.length;i++){
            DisplayArray(Matrix[i]);
        }
        System.out.println();
    }

    public static double[][] persamaanSPLAugmented(double[][] MatrixAugmented){
        int n = MatrixAugmented.length;
        int m = MatrixAugmented[0].length;
        double[][] Matrix = new double[n][m-1];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                Matrix[i][j] = MatrixAugmented[i][j];
            }
        }
        return Matrix;
    }

    public static double[] nilaiSPLAugmented(double[][] MatrixAugmented){
        int n = MatrixAugmented.length;
        int m = MatrixAugmented[0].length;
        double[] nilai = new double[n];
        for(int j=0;j<n;j++){
            nilai[j] = MatrixAugmented[j][m-1];
        }
        return nilai;        
    }

    public static boolean cekDeterminan(double[][] Matrix){
        // return true jika boolean != 0, false jika boolean 0;
        double determinan = OperasiDeterminan.DeterminanOBE(Matrix);
        boolean kondisi = determinan != 0;
        return kondisi;
    }

    public static void PrintSolusiSPL(double[] solusi){
        int n = solusi.length;
        System.out.println("Solusi persamaan tersebut adalah : ");
        for(int i=0;i<n;i++){
            System.out.println("x" + i + " : " + solusi[i]);
        }
    }

    public static void MenuSPLGauss(){
        double[][] MatrixAugmented = InputSPLAugmented();
        double[][] Matrix = persamaanSPLAugmented(MatrixAugmented);
        double[] nilai = nilaiSPLAugmented(MatrixAugmented);
        boolean adaSolusi = cekDeterminan(Matrix);
        if(adaSolusi){
            double[] solusi = OperasiSPL.SPLgauss(Matrix, nilai);
            PrintSolusiSPL(solusi);
        }else{
            System.out.print("Tidak ada solusi.");
        }
    }
    
    public static void MenuSPLGaussJordan(){
        double[][] MatrixAugmented = InputSPLAugmented();
        double[][] Matrix = persamaanSPLAugmented(MatrixAugmented);
        double[] nilai = nilaiSPLAugmented(MatrixAugmented);
        boolean adaSolusi = cekDeterminan(Matrix);
        if(adaSolusi){
            double[] solusi = OperasiSPL.SPLgauss_jordan(Matrix, nilai);
            PrintSolusiSPL(solusi);
        }else{
            System.out.print("Tidak ada solusi.");
        }
        
    }
    
    public static void MenuSPLInverse(){
        double[][] MatrixAugmented = InputSPLAugmented();
        double[][] Matrix = persamaanSPLAugmented(MatrixAugmented);
        double[] nilai = nilaiSPLAugmented(MatrixAugmented);
        boolean adaSolusi = cekDeterminan(Matrix);
        if(adaSolusi){
            double[] solusi = OperasiSPL.SolusiSPLInverse(Matrix, nilai);
            PrintSolusiSPL(solusi);
        }else{
            System.out.print("Tidak ada solusi.");
        }
    }
    
    public static void MenuSPLCrammer(){
        double[][] MatrixAugmented = InputSPLAugmented();
        double[][] Matrix = persamaanSPLAugmented(MatrixAugmented);
        double[] nilai = nilaiSPLAugmented(MatrixAugmented);
        boolean adaSolusi = cekDeterminan(Matrix);
        if(adaSolusi){
            double[] solusi = OperasiSPL.SolusiCrammer(Matrix, nilai);
            PrintSolusiSPL(solusi);
        }else{
            System.out.print("Tidak ada solusi.");
        }
    }
    
    public static void MenuDeterminanCofactor(int i){
        double[][] Matrix = InputMatrix();
        if (i == 1){
            if(Matrix.length != Matrix[0].length){
                System.out.print("Matrix bukan matrix segitiga, tidak memiliki determinan");
            }else{
                double determinan = OperasiDeterminan.DeterminanCofactor(Matrix, Matrix.length);
                System.out.println("Determinan Matrix : " + determinan);
            }
        }else if(i == 2){
            System.out.println("Masukkan nama file yang akan disimpan : ");
            Scanner input  = new Scanner(System.in);
            String name = input.nextLine();
            try{
            writeDeterminanCofactor(name,Matrix);
            }catch (IOException e){
                System.out.println("Terjadi kesalahan.");
            }
        }else{
            System.out.println("Input tidak dikenal.");
        }
    }

    public static void MenuDeterminanOBE(int o){
        double[][] Matrix = InputMatrix();
        if (o == 1){
        if(Matrix.length != Matrix[0].length){
            System.out.print("Matrix bukan matrix segitiga, tidak memiliki determinan");
        }else{
            double determinan = OperasiDeterminan.DeterminanOBE(Matrix);
            System.out.println("Determinan Matrix : " + determinan);
        }
        }else if(o == 2){
        System.out.println("Masukkan nama file yang akan disimpan : ");
        Scanner input  = new Scanner(System.in);
        String name = input.nextLine();
        try{
            writeDeterminanOBE(name,Matrix);
        }catch (IOException e){
            System.out.println("Terjadi kesalahan.");
        }} else{
        System.out.println("Input tidak dikenal.");
        }
    }

    public static void MenuInversAdjoin(int o){
        double[][] Matrix = InputMatrix();
        double determinan = OperasiDeterminan.DeterminanCofactor(Matrix, Matrix.length);
        if (o == 1){
        if(determinan == 0 || Matrix.length != Matrix[0].length){
            System.out.println("Matrix tidak memiliki Invers");
        }else{
            Matrix = OperasiInverse.inverseCofactor(Matrix);
            System.out.println("Invers Matrix: ");
            DisplayMatrix(Matrix);
        }
        }else if(o == 2){
            System.out.println("Masukkan nama file yang akan disimpan : ");
            Scanner input  = new Scanner(System.in);
            String name = input.nextLine();
            try{
                writeInverseAdjoint(name,Matrix,determinan);
            }catch (IOException e){
                System.out.println("Terjadi kesalahan.");
            }} else{
            System.out.println("Input tidak dikenal.");
        }
    }

    public static void MenuInversOBE(int o){
        double[][] Matrix = InputMatrix();
        double determinan = OperasiDeterminan.DeterminanOBE(Matrix);
        if (o == 1) {
            if (determinan == 0 || Matrix.length != Matrix[0].length) {
                System.out.println("Matrix tidak memiliki Invers");
            } else {
                OperasiInverse.inverseIdentity(Matrix);
                System.out.println("Invers Matrix: ");
                DisplayMatrix(Matrix);
            }
        }else if(o == 2){
            System.out.println("Masukkan nama file yang akan disimpan : ");
            Scanner input  = new Scanner(System.in);
            String name = input.nextLine();
            try{
                writeInverseOBE(name,Matrix,determinan);
            }catch (IOException e){
                System.out.println("Terjadi kesalahan.");
            }} else{
            System.out.println("Input tidak dikenal.");
        }
    }

    public static void MenuInterpolasiPolinom(){
        double[][] MatrixAugmented = InputSPLAugmented();
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
        scan.close();
        InterpolasiPolinom.estimate(Matrix, nilai, x);
    }

    public static void MenuInterpolasiBicubic(){
        double[] nilai = new double[16];
        double a;
        Scanner scan = new Scanner(System.in);
        for(int y=-1;y<3;y++){
            for(int x=-1;x<3;x++){
                System.out.printf("Masukkan nilai f(%d,%d) : ", x, y);
                a = scan.nextDouble();
                nilai[x+y*4+5] = a;
            }
        }
        System.out.print("Masukkan nilai yang ingin di interpolasi : ");
        double ax, ay;
        ax = scan.nextDouble();
        ay = scan.nextDouble();
        scan.close();

        double interpolasi = InterpolasiBicubic.interpolasiBicubic(nilai, ax, ay);
        System.out.printf("Nilai f(%f,%f) hasil interpolasi adalah : %f\n", ax, ay, interpolasi);
    }

    public static void MenuRegresiLinierBerganda(){
        int n,m;
        double[][] Matrix;
        double[][] nilai;
        double[][] var ;
        Scanner scan = new Scanner(System.in);
        System.out.println("Masukkan banyak peubah x (n) : ");
        System.out.print("n : ");
        n = scan.nextInt();
        System.out.println("Masukkan banyak sampel (m) : ");
        System.out.print("m : ");
        m = scan.nextInt();
        Matrix = InputRegresiX(n,m,scan);
        System.out.println("Berikan input nilai Y : ");
        nilai = InputRegresiY(m,scan);
        System.out.println("Masukkan semua nilai x yang ingin di taksir : ");
        var = InputRegresiY(n-1, scan);
        scan.close();
        DoubleLinearReg.estimateDoubReg(Matrix, nilai, var);
    }
}

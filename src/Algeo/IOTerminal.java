package Algeo;
import java.io.IOException;
import java.util.*;

import static Algeo.IOFiles.*;


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
        return determinan != 0;
    }

    public static boolean adaSolusiSPL(double[][] Matrix, double[] nilai){
        boolean adaDeterminan = cekDeterminan(Matrix);
        boolean solusiParametrik = SolusiParametrik.isParametrik(Matrix);
        if(solusiParametrik){
            SolusiParametrik.solusi(Matrix, nilai);
        }else if(adaDeterminan){
            return true;
        }else{
            System.out.println("Determinan 0, Tidak ada solusi.");
        }
        return false;
    }

    public static void PrintSolusiSPL(double[] solusi){
        int n = solusi.length;
        System.out.println("Solusi persamaan tersebut adalah : ");
        for(int i=0;i<n;i++){
            System.out.println("x" + i + " : " + solusi[i]);
        }
    }

    public static void MenuSPLGauss(int o){
        double[][] MatrixAugmented = InputSPLAugmented();
        double[][] Matrix = persamaanSPLAugmented(MatrixAugmented);
        double[] nilai = nilaiSPLAugmented(MatrixAugmented);
        if (o == 1){
            boolean adaSolusi = adaSolusiSPL(Matrix, nilai);
            if(adaSolusi){
                double[] solusi = OperasiSPL.SPLgauss(Matrix, nilai);
                PrintSolusiSPL(solusi);
        }else if(o == 2) {
                System.out.println("Masukkan nama file yang akan disimpan : ");
                Scanner input = new Scanner(System.in);
                String name = input.nextLine();
                try {
                    writeSPLGauss(name, Matrix, nilai);
                } catch (IOException e) {
                    System.out.println("Terjadi kesalahan.");
                }
            }
        }
    }
    
    public static void MenuSPLGaussJordan(int o) {
        double[][] MatrixAugmented = InputSPLAugmented();
        double[][] Matrix = persamaanSPLAugmented(MatrixAugmented);
        double[] nilai = nilaiSPLAugmented(MatrixAugmented);
        if (o == 1) {
            boolean adaSolusi = adaSolusiSPL(Matrix, nilai);
            if (adaSolusi) {
                double[] solusi = OperasiSPL.SPLgauss_jordan(Matrix, nilai);
                PrintSolusiSPL(solusi);
            } else if (o == 2) {
                System.out.println("Masukkan nama file yang akan disimpan : ");
                Scanner input = new Scanner(System.in);
                String name = input.nextLine();
                try {
                    writeSPLGaussJordan(name, Matrix, nilai);
                } catch (IOException e) {
                    System.out.println("Terjadi kesalahan.");
                }
            }
        }
    }
    
    public static void MenuSPLInverse(int o){
        double[][] MatrixAugmented = InputSPLAugmented();
        double[][] Matrix = persamaanSPLAugmented(MatrixAugmented);
        double[] nilai = nilaiSPLAugmented(MatrixAugmented);
        if (o == 1){
            boolean adaSolusi = adaSolusiSPL(Matrix, nilai);
            if(adaSolusi){
                double[] solusi = OperasiSPL.SolusiSPLInverse(Matrix, nilai);
                PrintSolusiSPL(solusi);
        }
        }else if(o == 2) {
            System.out.println("Masukkan nama file yang akan disimpan : ");
            Scanner input = new Scanner(System.in);
            String name = input.nextLine();
            try {
                writeSPLInverse(name, Matrix, nilai);
            } catch (IOException e) {
                System.out.println("Terjadi kesalahan.");
            }
        }
    }
    
    public static void MenuSPLCrammer(int o){
        double[][] MatrixAugmented = InputSPLAugmented();
        double[][] Matrix = persamaanSPLAugmented(MatrixAugmented);
        double[] nilai = nilaiSPLAugmented(MatrixAugmented);
        if(o == 1){
            boolean adaSolusi = adaSolusiSPL(Matrix, nilai);
            if(adaSolusi){
                double[] solusi = OperasiSPL.SolusiCrammer(Matrix, nilai);
                PrintSolusiSPL(solusi);
            }
        }else if(o == 2) {
            System.out.println("Masukkan nama file yang akan disimpan : ");
            Scanner input = new Scanner(System.in);
            String name = input.nextLine();
            try {
                writeSPLCrammer(name, Matrix, nilai);
            } catch (IOException e) {
                System.out.println("Terjadi kesalahan.");
            }
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
        }}
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
            }}
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
            }}
    }

    public static void MenuInterpolasiPolinom(){
        int i, j;
        Scanner sc = new Scanner(System.in);
        double x, y;
        int point;

        System.out.print("Masukkan jumlah titik : ");
        point = sc.nextInt();

        double[][] Matrix = new double[point][point];
        double[] nilai = new double[point];

        for(i = 0; i < point; i++){
            System.out.print("Masukkan titik x" + i + " y" + i + " : ");
            x = sc.nextDouble();
            y = sc.nextDouble();
            for(j = 0; j < point; j++){
                Matrix[i][j] = Math.pow(x, j);
            }
            nilai[i] = y;
        }
        System.out.print("Masukkan nilai x yang ingin di taksir : ");
        double xtaksir = sc.nextDouble();
        InterpolasiPolinom.estimate(Matrix, nilai, xtaksir);
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
        DoubleLinearReg.estimateDoubReg(Matrix, nilai, var);
    }
}

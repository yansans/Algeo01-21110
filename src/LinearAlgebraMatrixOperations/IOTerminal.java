package LinearAlgebraMatrixOperations;
import java.util.*;


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

    public static void PrintSolusiSPL(double[] solusi){

    }

    public static void MenuSPLGauss(){
        double[][] MatrixAugmented = InputSPLAugmented();
        double[] solusi = new double[4];
        PrintSolusiSPL(solusi);
    }
    
    public static void MenuSPLGaussJordan(){
        
    }
    
    public static void MenuSPLInverse(){
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
        // DebugMatrix.DisplayMatrix(Matrix);
        double[] solusi = SPLInv.SolusiSPLInverse(Matrix, nilai);
        DisplayArray(solusi);
    }
    
    public static void MenuSPLCrammer(){
        
    }
    
    public static void MenuDeterminanCofactor(){ // selesai
        double[][] Matrix = InputMatrix();
        if(Matrix.length != Matrix[0].length){
            System.out.print("Matrix bukan matrix segitiga, tidak memiliki determinan");
        }else{
            double determinan = Cofactor.determinant(Matrix, Matrix.length);
            System.out.println("Determinan Matrix : " + determinan);
        }
    }

    public static void MenuDeterminanOBE(){ // selesai
        double[][] Matrix = InputMatrix();
        if(Matrix.length != Matrix[0].length){
            System.out.print("Matrix bukan matrix segitiga, tidak memiliki determinan");
        }else{
            double determinan = DetOBE.DeterminanOBE(Matrix);
            System.out.println("Determinan Matrix : " + determinan);
        }
    }

    public static void MenuInversAdjoin(){ // selesai
        double[][] Matrix = InputMatrix();
        double determinan = Cofactor.determinant(Matrix, Matrix.length);
        if(determinan == 0 || Matrix.length != Matrix[0].length){
            System.out.println("Matrix tidak memiliki Invers");
        }else{
            Matrix = Cofactor.inverse(Matrix);
            System.out.println("Invers Matrix: ");
            DisplayMatrix(Matrix);
        }
    }

    public static void MenuInversOBE(){ // selesai
        double[][] Matrix = InputMatrix();
        Matrix = SPLInv.InverseOBE(Matrix);
        double determinan = DetOBE.DeterminanOBE(Matrix);
        if(determinan == 0 || Matrix.length != Matrix[0].length){
            System.out.println("Matrix tidak memiliki Invers");
        }else{
            System.out.println("Invers Matrix: ");
            DisplayMatrix(Matrix);
        }
    }

    public static void MenuInterpolasiPolinom(){
        System.out.print("Masukkan nilai n : ");
        Scanner scan = new Scanner(System.in);
        double n = scan.nextDouble();
        scan.close();

    }

    public static void MenuInterpolasiBicubic(){ // selesai
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

        double interpolasi = BicubicInterpolation.InterpolasiBicubic(nilai, ax, ay);
        System.out.printf("Nilai f(%f,%f) hasil interpolasi adalah : %f\n", ax, ay, interpolasi);
    }

    public static void MenuRegresiLinierBerganda(){

    }

    public static void main(String args[]){

    }

}

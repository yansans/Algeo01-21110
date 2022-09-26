package Tubes;
import java.util.Scanner;

public class DebugMatrix{
    
    // Studi kasus 1 matrix A
    public static double[][] Matrix1 = { {1,1,-1,-1},
                                        {2,5,-7,-5},
                                        {2,-1,1,3},
                                        {5,2,-4,2}};

    // Studi kasus 3 matrix a
    public static double[][] Matrix2 = { {8,1,3,2},
                                        {2,9,-1,-2},
                                        {1,3,2,-1},
                                        {1,0,6,4}};

    // Studi kasus Interpolasi Bicubic
    public static double[][] Matrix3 = { {153,59,210,96},
                                        {125,161,72,81},
                                        {98,101,42,12},
                                        {21,51,0,16}};

    // Studi kasus Regresi Liner Berganda
    public static double[][] Matrix4 = { {20,863.1f,1530.4f,587.84f},
                                        {863.1f,54876.89f,67000.09f,25283.395f},
                                        {1530.4f,67000.09f,117912.32f,44976.867f},
                                        {587.84f,25283.395f,44976.867f,17278.5086f}};
    
    public static double[][] CopyMatrix(double[][] Matrix){
        int dimensi = Matrix.length;
        
        double[][] MatrixClone = new double[dimensi][dimensi];
        for(int i=0;i<dimensi;i++){ // kolom yang akan di nol kan
            for(int j=0;j<dimensi;j++){ // meng-nol kan kolom dibawahnya
                MatrixClone[i][j] = Matrix[i][j];
            }
        }

        return MatrixClone;
    }

    public static void DisplayMatrix(double[][] Matrix){
        int dimensi = Matrix.length;
        
        for(int i=0;i<dimensi;i++){
            for(int j=0;j<dimensi;j++){
                System.out.print(Matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static double[][] InputMatrix(){
        int n,m;
        Scanner scan = new Scanner(System.in);
        System.out.println("Masukkan dimensi matrix n,m : ");
        n = scan.nextInt();
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

    public static void main(String[] args){
        DisplayMatrix(Matrix1);
        DisplayMatrix(Matrix2);
        DisplayMatrix(Matrix3);
        DisplayMatrix(Matrix4);
    }
}

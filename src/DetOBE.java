package Tubes;

public class DetOBE{
    public static double DeterminanOBE(double[][] Matrix){
        int dimensi = Matrix.length;
        double[][] matrixOBE = DebugMatrix.CopyMatrix(Matrix);
        
        for(int i=0;i<dimensi;i++){ // kolom yang akan di nol kan
            for(int j=i+1;j<dimensi;j++){ // meng-nol kan kolom dibawahnya
                double kali = matrixOBE[j][i]/matrixOBE[i][i];
                for(int k=0;k<dimensi;k++){
                    matrixOBE[j][k] -= kali* matrixOBE[i][k];
                }
            }
        }
        double determinan = 1;
        for(int i=0;i<dimensi;i++){
            determinan *= matrixOBE[i][i];
        }
        return determinan;
    }

    public static void main(String[] args){
        System.out.println(DetOBE.DeterminanOBE(DebugMatrix.Matrix1));
        System.out.println(DetOBE.DeterminanOBE(DebugMatrix.Matrix2));
        System.out.println(DetOBE.DeterminanOBE(DebugMatrix.Matrix3));
        System.out.println(DetOBE.DeterminanOBE(DebugMatrix.Matrix4));
    }

}


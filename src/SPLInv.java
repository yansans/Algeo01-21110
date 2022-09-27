package Tubes;


public class SPLInv {
    public static double[][] InverseOBE(double[][] matrix){
        int dimensi = matrix.length;
        double[][] matrixCopy = DebugMatrix.CopyMatrix(matrix);
        double[][] matrixInverse = new double[dimensi][dimensi];
        for(int i=0;i<dimensi;i++){
            matrixInverse[i][i] = 1;
        }

        for(int i=0;i<dimensi;i++){ // kolom yang akan di nol kan
            for(int j=i+1;j<dimensi;j++){ // meng-nol kan kolom dibawahnya
                double kali = matrixCopy[j][i]/matrixCopy[i][i];
                for(int k=0;k<dimensi;k++){
                    matrixCopy[j][k] -= kali* matrixCopy[i][k];
                    matrixInverse[j][k] -= kali* matrixInverse[i][k];
                }
            }
        }

        for(int i=0;i<dimensi;i++){ // membuat nilai diagonal utama menjadi 1
            double kali = (1.00f)/matrixCopy[i][i];
            for(int j=0;j<dimensi;j++){
                matrixCopy[i][j] *= kali;
                matrixInverse[i][j] *= kali;
            }
        }

        for(int i=dimensi-1;i>=0;i--){ // kolom yang akan di nol kan
            for(int j=dimensi-1;j>i;j--){ // meng-nol kan kolom diatasnya
                double kali = matrixCopy[i][j]/matrixCopy[j][j];
                for(int k=0;k<dimensi;k++){
                    matrixCopy[i][k] -= kali* matrixCopy[j][k];
                    matrixInverse[i][k] -= kali* matrixInverse[j][k];
                }
            }
        }
        
        return matrixInverse;
    }

    public static void main(String[] args){
        DebugMatrix.DisplayMatrix(InverseOBE(DebugMatrix.Matrix1));
        DebugMatrix.DisplayMatrix(InverseOBE(DebugMatrix.Matrix2));
        DebugMatrix.DisplayMatrix(InverseOBE(DebugMatrix.Matrix3));
        DebugMatrix.DisplayMatrix(InverseOBE(DebugMatrix.Matrix4));
    }
}

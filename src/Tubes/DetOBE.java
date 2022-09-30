package Tubes;

public class DetOBE{
    public static double DeterminanOBE(double[][] Matrix){
        int dimensi = Matrix.length;
        double[][] matrixOBE = DebugMatrix.CopyMatrix(Matrix);
        int tukarcounter = 0;
        
        for(int i=0;i<dimensi;i++){ // kolom yang akan di nol kan
            if(matrixOBE[i][i] == 0 && i!=dimensi-1){ // apabila diagonal utama nol, tukar barus
                boolean found = false;
                for(int j=i+1;j<dimensi;j++){
                    if(matrixOBE[j][i] != 0){
                        found = true;
                        for(int k=0;k<dimensi;k++){ // menukar baris
                            double temp = matrixOBE[j][k];
                            matrixOBE[j][k] = matrixOBE[i][k];                            
                            matrixOBE[i][k] = temp;                            
                        }
                        break;
                    }
                }
                if(!found){ // jika tidak bisa menukar baris
                    return 0;
                }else{
                    tukarcounter++;
                }
            }
            for(int j=i+1;j<dimensi;j++){ // meng-nol kan kolom dibawahnya
                double kali = matrixOBE[j][i]/matrixOBE[i][i];

                for(int k=0;k<dimensi;k++){
                    matrixOBE[j][k] -= kali* matrixOBE[i][k];
                }
            }
        }
        double determinan = Math.pow(-1, tukarcounter);
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


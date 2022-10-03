package Algeo;

public class OperasiDeterminan {
    
    public static double DeterminanCofactor(double[][] m, int n) {
        // Mendapatkan determinan dari m secara rekursif menggunakan metode kofaktor
        double det = 0;
        if (n == 1) {
            return m[0][0];
        }
        double[][] temp = new double[n][n];
        int sign = 1;
        for (int i = 0; i < n; i++) {
            OperasiPrimitif.getCofactor(m, temp, 0, i, n);
            det += sign * m[0][i] * DeterminanCofactor(temp, n - 1);
            sign = -sign;
        }
        return det;
    }

    public static double DeterminanOBE(double[][] Matrix){
        int dimensi = Matrix.length;
        double[][] matrixOBE = new double[dimensi][dimensi]; 
        OperasiPrimitif.copyMatrix(Matrix, matrixOBE);
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
}

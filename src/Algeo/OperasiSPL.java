package src.Algeo;

public class OperasiSPL {
    public static double[] SPLgauss_jordan(double[][] A, double[] b){
        int i, j;
        int m = A.length, n = A[0].length;
        int zero_row = 0;
        double[] solution = new double[n];
        double[][] augmented = new double[m][n+1];

        // Set Solutions to undefined
        for(i = 0; i < n; i++){
            solution[i] = Double.NaN;
        }

        // Create Matrix Augmented
        for(i = 0; i < m; i++){
            for(j = 0; j < n+1; j++){
                if(j == n){
                    augmented[i][j] = b[i];
                } else{
                    augmented[i][j] = A[i][j];
                }
            }
        }

        // Gaussian Elimination
        OperasiPrimitif.gauss_jordan(augmented);

        // Check Solution
        for(j = 0; j < n+1; j++){
            zero_row = j;
            if(augmented[m-1][j] != 0){
                break;
            }
        }
        if(zero_row == n && augmented[m-1][n] == 0){ // Solusi parametrik
            return solution;
        } else if(zero_row == n){                   // Tidak memiliki solusi
            return solution;
        } else{
            for(i = 0; i < m; i++){
                solution[i] = augmented[i][n];
            }
            return solution;
        }
    }



    public static double[] SPLgauss(double[][] A, double[] b){
        int i, j;
        int m = A.length, n = A[0].length;
        int zero_row = 0;
        double value = 0d;
        double[] solution = new double[n];
        double[][] augmented = new double[m][n+1];

        // Set Solutions to undefined
        for(i = 0; i < n; i++){
            solution[i] = Double.NaN;
        }

        // Create Matrix Augmented
        for(i = 0; i < m; i++){
            for(j = 0; j < n+1; j++){
                if(j == n){
                    augmented[i][j] = b[i];
                } else{
                    augmented[i][j] = A[i][j];
                }
            }
        }

        // Gaussian Elimination
        OperasiPrimitif.gauss(augmented);

        // Check Solution
        for(j = 0; j < n+1; j++){
            zero_row = j;
            if(augmented[m-1][j] != 0){
                break;
            }
        }
        if(zero_row == n && augmented[m-1][n] == 0){    // Solusi parametrik
            return solution;
        } else if(zero_row == n){                       // Tidak memiliki solusi
            return solution;
        } else{
            solution[n-1] = augmented[m-1][n];
            value = 0d; 
            for(i = m-2; i >= 0; i--){
                for(j = i+1; j < n; j++){
                    value += solution[j] * augmented[i][j];
                }
                solution[i] = augmented[i][n] - value;
                value = 0;
            }
            return solution;
        }
    }
    
    public static double[] SolusiCrammer(double[][] matrix, double[] nilai){
        int n = nilai.length;
        double[] solusi = new double[n];
        double determinanUtama = OperasiDeterminan.DeterminanOBE(matrix);
        if(determinanUtama == 0){
            return solusi; // jika determinan matrix utama 0 maka tidak ada solusi
        }

        double[][] matrixtmp = new double[n][n];
        for(int i=0;i<n;i++){ // iterate seluruh kolom yang akan diganti
            OperasiPrimitif.copyMatrix(matrix, matrixtmp); // copy matrix
            for(int j=0;j<n;j++){
                matrixtmp[j][i] = nilai[j]; // ganti nilai kolom matrix
            }
            double determinanKecil = OperasiDeterminan.DeterminanOBE(matrixtmp);
            solusi[i] = determinanKecil/determinanUtama;
        }
        return solusi;
    }
}

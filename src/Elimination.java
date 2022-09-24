import java.util.*;

public class Elimination {
    public static void gauss(double[][] matrix, int m, int n){
        int i, j, k;
        double ratio = 0d;
        int zero_row1 = 0, zero_row2 = 0;
        // Forward Elimination
        for(i = 0; i < m-1; i++){
            pivot(matrix, m, n);
            for(j = 0; j < n; j++){
                zero_row1 = j;
                if(matrix[i][j] != 0){
                    break;
                }
                if(j == n-1 && matrix[i][n-1] == 0){ 
                    zero_row1++;
                }
            }
            for(k = i+1; k < m; k++){
                for(j = 0; j < n; j++){
                    zero_row2 = j;
                    if(matrix[k][j] != 0){
                        break;
                    }
                }
                if(zero_row1 == zero_row2){
                    ratio = matrix[k][zero_row2] / matrix[i][zero_row1];
                    for(j = 0; j < n; j++){
                        matrix[k][j] -= ratio * matrix[i][j];
                    }
                }
            }
        }

        // Leading one
        // Membagi setiap baris dengan elemen pertama tidak nol pada baris tersebut agar terbentuk satu utama
        for(i = 0; i < m; i++){
            for(j = 0; j < n; j++){
                zero_row1 = j;
                if(matrix[i][j] != 0){
                    break;
                }
                if(j == n-1 && matrix[i][n-1] == 0){ 
                zero_row1++;
                } 
            }
            if(zero_row1 != n){
                ratio = matrix[i][zero_row1];
                for(j = 0; j < n; j++){
                    matrix[i][j] /= ratio;
                }
            }
        }
    }




    public static void gauss_jordan(double[][] matrix, int m, int n){
        gauss(matrix, m, n);

        int i, j, k;
        int lead_one = 0;
        double ratio = 0d;
        // Backward Elimination
        for(i = 0; i < m; i++){
            for(j = 0; j < n; j++){
                lead_one = j;
                if(matrix[i][j] == 1){
                    break;
                }
                if(j == n-1 && matrix[i][n-1] == 0){ 
                    lead_one++;
                }
            }
            if(lead_one != n){
                for(k = 0; k < m; k++){
                    if(k != i && matrix[k][lead_one] != 0){
                        ratio = matrix[k][lead_one] / matrix[i][lead_one];
                        for(j = 0; j < n; j++){
                            matrix[k][j] -= ratio * matrix[i][j];
                        }
                    }
                }
            }
        }
    }





    public static void pivot(double[][] matrix, int m, int n){
        int i, j, k;
        // Operasi Pertukaran Baris
        int zero_row1 = 0, zero_row2 = 0;
        double[] temp_array = new double[n];

        for(k = 0; k < m; k++){
            for(i = 0; i < m-1; i++){   // Mengkomparasikan indeks kolom matriks dengan elemen tidak nol pertama antara baris ke-i dengan baris setelahnya
                for(j = 0; j < n; j++){     // Mengecek indeks elemen tidak nol baris i
                    zero_row1 = j;
                    if(matrix[i][j] != 0){
                        break;
                    }
                    if(j == n-1 && matrix[i][n-1] == 0){    // Jika tidak ada elemen tidak nol pada baris i, maka indeks elemen tidak nol dianggap bernilai n
                    zero_row1++;
                }
            }
                for(j = 0; j < n; j++){     // Mengecek indeks elemen tidak nol baris setelah baris i
                    zero_row2 = j;
                    if(matrix[i+1][j] != 0){
                        break;
                    }
                }

                if(zero_row1 > zero_row2){  // Pertukaran baris
                    for(j = 0; j < n; j++){
                        temp_array[j] = matrix[i][j];
                        matrix[i][j] = matrix[i+1][j];
                        matrix[i+1][j] = temp_array[j];
                    }
                }
            }
        }
    }
}
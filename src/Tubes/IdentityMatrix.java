package Tubes;

public class IdentityMatrix {
    public static void inverse(double[][] matrix, int m){
        int i, j, k;
        double zero = 0d, one = 1d;
        double[][] copy = new double[m][m];
        double[][] identity = new double[m][m];
        boolean inverse = true;

        // Check if matrix has inverse
        if(DetOBE.DeterminanOBE(matrix) == 0){
            System.out.println("Matriks tidak memiliki balikan");
        } else{
            // Copy Matrix
            Misc.copyMatrix(matrix, copy);

            // Create Identity Matrix
            for(i = 0; i < m; i++){
                for(j = 0; j < m; j++){
                    if(i == j){
                        identity[i][j] = one;
                    } else{
                        identity[i][j] = zero;
                    }
                }
            }

            // Elimination
            gauss_jordan_inv(copy, identity);
            
            // Store inverse value to matrix
            for(i = 0; i < m; i++){
                for(j = 0; j < m; j++){
                    matrix[i][j] = identity[i][j];
                }
            }
        }
    }
    






    public static void gauss_inv(double[][] matrix, double[][] identity){
        int i, j, k;
        int m = matrix.length;
        double ratio = 0d;
        int zero_row = 0, zero_row1 = 0, zero_row2 = 0;
        // Forward Elimination
        for(i = 0; i < m-1; i++){
            leadingOne_inv(matrix, identity, i);
            pivot_inv(matrix, identity);
            for(j = 0; j < m; j++){
                zero_row1 = j;
                if(matrix[i][j] != 0){
                    break;
                }
                if(j == m-1 && matrix[i][m-1] == 0){ 
                    zero_row1++;
                }
            }
            for(k = i+1; k < m; k++){
                for(j = 0; j < m; j++){
                    zero_row2 = j;
                    if(matrix[k][j] != 0){
                        break;
                    }
                }
                if(zero_row1 == zero_row2){
                    ratio = matrix[k][zero_row2] / matrix[i][zero_row1];
                    for(j = 0; j < m; j++){
                        matrix[k][j] -= ratio * matrix[i][j];
                        identity[k][j] -= ratio * identity[i][j];
                    }
                }
            }
        }
        leadingOne_inv(matrix, identity, i);
    }




    public static void gauss_jordan_inv(double[][] matrix, double[][] identity){
        int i, j, k;
        int lead_one = 0, m = matrix.length;
        double ratio = 0d;

        gauss_inv(matrix, identity);
        // Backward Elimination
        for(i = 0; i < m; i++){
            for(j = 0; j < m; j++){
                lead_one = j;
                if(matrix[i][j] == 1){
                    break;
                }
                if(j == m-1 && matrix[i][m-1] == 0){ 
                    lead_one++;
                }
            }
            if(lead_one != m){
                for(k = 0; k < m; k++){
                    if(k != i && matrix[k][lead_one] != 0){
                        ratio = matrix[k][lead_one] / matrix[i][lead_one];
                        for(j = 0; j < m; j++){
                            matrix[k][j] -= ratio * matrix[i][j];
                            identity[k][j] -= ratio * identity[i][j];
                        }
                    }
                }
            }
        }
    }





    public static void pivot_inv(double[][] matrix, double[][] identity){
        int i, j, k;
        // Operasi Pertukaran Baris
        int zero_row1 = 0, zero_row2 = 0, m = matrix.length;
        double[] temp_array1 = new double[m];
        double[] temp_array2 = new double[m];

        for(k = 0; k < m; k++){
            for(i = 0; i < m-1; i++){   // Mengkomparasikan indeks kolom matriks dengan elemen tidak nol pertama antara baris ke-i dengan baris setelahnya
                for(j = 0; j < m; j++){     // Mengecek indeks elemen tidak nol baris i
                    zero_row1 = j;
                    if(matrix[i][j] != 0){
                        break;
                    }
                    if(j == m-1 && matrix[i][m-1] == 0){    // Jika tidak ada elemen tidak nol pada baris i, maka indeks elemen tidak nol dianggap bernilai n
                    zero_row1++;
                }
            }
                for(j = 0; j < m; j++){     // Mengecek indeks elemen tidak nol baris setelah baris i
                    zero_row2 = j;
                    if(matrix[i+1][j] != 0){
                        break;
                    }
                }

                if(zero_row1 > zero_row2){  // Pertukaran baris
                    for(j = 0; j < m; j++){
                        temp_array1[j] = matrix[i][j];
                        matrix[i][j] = matrix[i+1][j];
                        matrix[i+1][j] = temp_array1[j];

                        temp_array2[j] = identity[i][j];
                        identity[i][j] = identity[i+1][j];
                        identity[i+1][j] = temp_array2[j];
                    }
                }
            }
        }
    }





    public static void leadingOne_inv(double[][] matrix, double[][] identity, int i){
        int j, zero_row = 0;
        int m = matrix.length;
        double ratio;

        for(j = 0; j < m; j++){
                zero_row = j;
                if(matrix[i][j] != 0){
                    break;
                }
                if(j == m-1 && matrix[i][m-1] == 0){ 
                    zero_row++;
                }
            }
            ratio = matrix[i][zero_row];
        for(j = 0; j < m; j++){
            matrix[i][j] /= ratio;
            identity[i][j] /= ratio;
        }
    }
}

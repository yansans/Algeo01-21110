public class Identity {
    public static void inverse(double[][] matrix, int m){
        int i, j, k;
        double zero = 0d, one = 1d;
        double[][] copy = new double[m][m];
        double[][] identity = new double[m][m];
        boolean inverse = true;

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
        gauss_jordan_inv(copy, identity, m);

        // Check if matrix has inverse
        for(i = 0; i < m; i++){
            for(j = 0; j < m; j++){
                if(copy[i][j] == one){
                    break;
                }
                if(j == m-1 && copy[i][j] == 0){
                    inverse = false;
                    break;
                }
            }
            if(inverse == false){
                break;
            }
        }
        if(inverse == false){
            System.out.println("Matriks tidak memiliki balikan");
        } else{
            // Store inverse value to matrix
            for(i = 0; i < m; i++){
                for(j = 0; j < m; j++){
                    matrix[i][j] = identity[i][j];
                }
            }
        }
    }






    public static void gauss_inv(double[][] matrix, double[][] identity, int m){
        int i, j, k;
        double ratio = 0d;
        int zero_row1 = 0, zero_row2 = 0;
        // Forward Elimination
        for(i = 0; i < m-1; i++){
            pivot_inv(matrix, identity, m);
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

        // Leading one
        // Membagi setiap baris dengan elemen pertama tidak nol pada baris tersebut agar terbentuk satu utama
        for(i = 0; i < m; i++){
            for(j = 0; j < m; j++){
                zero_row1 = j;
                if(matrix[i][j] != 0){
                    break;
                }
                if(j == m-1 && matrix[i][m-1] == 0){ 
                zero_row1++;
                } 
            }
            if(zero_row1 != m){
                ratio = matrix[i][zero_row1];
                for(j = 0; j < m; j++){
                    matrix[i][j] /= ratio;
                    identity[i][j] /= ratio;
                }
            }
        }
    }




    public static void gauss_jordan_inv(double[][] matrix, double[][] identity,  int m){
        gauss_inv(matrix, identity, m);

        int i, j, k;
        int lead_one = 0;
        double ratio = 0d;
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





    public static void pivot_inv(double[][] matrix, double[][] identity, int m){
        int i, j, k;
        // Operasi Pertukaran Baris
        int zero_row1 = 0, zero_row2 = 0;
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
}
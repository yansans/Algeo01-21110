package LinearAlgebraMatrixOperations;

public class OperasiPrimitif {
    public static void copyMatrix(double[][] matrix, double[][] copy){
        int i, j;

        for(i = 0; i < matrix.length; i++){
            for(j = 0; j < matrix[0].length; j++){
                copy[i][j] = matrix[i][j];
            }
        }
    }

    public static double[][] PerkalianMatrix(double[][] matrix1, double[][] matrix2){
        int n = matrix1.length;
        int m = matrix2[0].length;
        int o = matrix1[0].length;
        double[][] matrix3 = new double[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                for(int k=0;k<o;k++){
                    matrix3[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return matrix3;
    }

    public static double[][] PerkalianMatrixKonstanta(double[][] matrix, double konstanta){
        int n = matrix.length;
        int m = matrix[0].length;
        double[][] matrix2 = new double[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                matrix2[i][j] = matrix[i][j] * konstanta;
            }
        }
        return matrix2;
    }

    public static double[] SolusiSPL(double[][] matrix, double[] nilai){
        int dimensi = matrix.length, col = matrix[0].length;
        double[] solusi = new double[col];
        for(int i=0;i<dimensi;i++){
            for(int j=0;j<dimensi;j++){
                solusi[i] += matrix[i][j] * nilai[j];
            }
        }
        return solusi;
    }

    public static double[][] transpose(double[][] m) {
        // transpose matrix
        int row = m.length;
        int col = m[0].length;
        double[][] trans = new double[col][row];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                trans[j][i] = m[i][j];
            }
        }
        return trans;
    }

    public static void getCofactor(double[][] m, double[][] temp, int a, int b, int n) {
        // Mendapatkan kofaktor dari m dan menyimpannya di temp

        int row , col;
        row = col = 0;

        for (int i = 0 ; i < n ; i++){
            for (int j = 0 ; j < n ; j++){
                if (i != a && j != b){
                    temp[row][col++] = m[i][j];
                    if (col == n - 1){
                        col = 0;
                        row++;
                    }
                }

            }
        }
    }

    public static void gauss(double[][] matrix){
        int i, j, k;
        int m = matrix.length, n = matrix[0].length;
        double ratio = 0d;
        int zero_row1 = 0, zero_row2 = 0;
        // Forward Elimination
        for(i = 0; i < m-1; i++){
            pivot(matrix);
            leadingOne(matrix, i);
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
        leadingOne(matrix, i);
    }

    public static void gauss_jordan(double[][] matrix){
        gauss(matrix);

        int i, j, k;
        int m = matrix.length, n = matrix[0].length;
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

    public static void pivot(double[][] matrix){
        int i, j, k;
        int m = matrix.length, n = matrix[0].length;
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

    public static void leadingOne(double[][] matrix, int i){
        int j, zero_row = 0;
        int n = matrix[0].length;
        double ratio;

        for(j = 0; j < n; j++){
                zero_row = j;
                if(matrix[i][j] != 0){
                    break;
                }
                if(j == n-1 && matrix[i][n-1] == 0){ 
                    zero_row++;
                }
            }
            ratio = matrix[i][zero_row];
        for(j = 0; j < n; j++){
            matrix[i][j] /= ratio;
        }
    }
    
    public static void gauss_inv(double[][] matrix, double[][] identity){
        int i, j, k;
        int m = matrix.length;
        double ratio = 0d;
        int zero_row = 0, zero_row1 = 0, zero_row2 = 0;
        // Forward Elimination
        for(i = 0; i < m-1; i++){
            pivot_inv(matrix, identity);
            leadingOne_inv(matrix, identity, i);
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

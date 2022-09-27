public class SPL_Elimination {
    public static double[] gauss_jordan(double[][] A, double[][] b, int m, int n){
        int i, j;
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
                    augmented[i][j] = b[i][0];
                } else{
                    augmented[i][j] = A[i][j];
                }
            }
        }

        // Gaussian Elimination
        Elimination.gauss_jordan(augmented, m, n+1);

        // Check Solution
        for(j = 0; j < n+1; j++){
            zero_row = j;
            if(augmented[m-1][j] != 0){
                break;
            }
        }
        if(zero_row == n && augmented[m-1][n] == 0){
            System.out.println("Solusi SPL berbentuk parametrik");
            return solution;
        } else if(zero_row == n){
            System.out.println("SPL tidak memiliki solusi");
            return solution;
        } else{
            for(i = 0; i < m; i++){
                solution[i] = augmented[i][n];
            }
            return solution;
        }
    }





    public static double[] gauss(double[][] A, double[][] b, int m, int n){
        int i, j;
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
                    augmented[i][j] = b[i][0];
                } else{
                    augmented[i][j] = A[i][j];
                }
            }
        }

        // Gaussian Elimination
        Elimination.gauss(augmented, m, n+1);

        // Check Solution
        for(j = 0; j < n+1; j++){
            zero_row = j;
            if(augmented[m-1][j] != 0){
                break;
            }
        }
        if(zero_row == n && augmented[m-1][n] == 0){
            System.out.println("Solusi SPL berbentuk parametrik");
            return solution;
        } else if(zero_row == n){
            System.out.println("SPL tidak memiliki solusi");
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
}
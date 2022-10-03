package src.Algeo;

public class SolusiParametrik {
    public static void solusi(double[][] A, double[] b){
        int i, j, k = 0, l, p, q;
        int nonzero_count;
        int hex = 0x70;
        int UNDEF = -9999;
        double value;
        double[][] matrix = new double[A.length][A[0].length + 1];

        // Create Matrix Augmented
        for(i = 0; i < A.length; i++){
            for(j = 0; j < A[0].length + 1; j++){
                if(j == A[0].length){
                    matrix[i][j] = b[i];
                } else{
                    matrix[i][j] = A[i][j];
                }
            }
        }

        int m = matrix.length, n = matrix[0].length - 1;
        double[] solution = new double[n];
        String[] parvariable = new String[n];
        String[] parsolution = new String[n];
        // Set solutions to undefined
        for(i = 0; i < n-1; i++){
            solution[i] = UNDEF;
        }

        // Gauss-Jordan Elimination
        OperasiPrimitif.gauss_jordan(matrix);

        // Parametrik
        p = n-1;
        for(i = m-1; i >= 0; i--){
            nonzero_count = 0;
            // Count the number of nonzero elements in a row (without the last column)
            for(j = 0; j < n; j++){
                if(matrix[i][j] != 0){
                    nonzero_count++;
                    k = j; // The col index of the last nonzero element
                }
            }
            // If only one element, then the solution is in the last column
            if(nonzero_count == 1){
                solution[k] = matrix[i][n];
                // Look for the parametric solution (that is, the column of the zero element after the only nonzero)
                for(l = k; l < n; l++){
                    if(matrix[i][l] == 0 && solution[l] == UNDEF){
                        parvariable[l] = Character.toString((char)hex);
                        hex++;
                    }
                }
            }
            // OR if there are no nonzero elemets in the row, the parametric solution is clear
            else if(nonzero_count == 0){
                parvariable[p] = Character.toString((char)hex);
                hex++;
                p--;
            }
        }
        // The solution with more than one element in a row
        for(i = m-1; i >= 0; i--){
            nonzero_count = 0;
            // Count the nonzero elements in a row
            for(j = n-1; j >= 0; j--){
                if(matrix[i][j] != 0){
                    nonzero_count++;
                    k = j; // Index of the first nonzero element
                }
            }
            // If more than one element, then subtract the rightmost column of the row (it is the solution)
            if(nonzero_count > 1){
                solution[k] = matrix[i][n];
                for(l = k+1; l < n; l++){
                    // If the solution of the current index not parametric
                    if(parvariable[l] == null && solution[l] != UNDEF){
                        solution[k] -= matrix[i][l] * solution[l];
                    }
                }
            }
        }
        // Check if there are any parametric solutions left
        for(i = 0; i < n; i++){
            if(solution[i] == UNDEF && parvariable[i] == null && matrix[i][i] == 0){
                parvariable[i] = Character.toString((char)hex);
                hex++;
            }
        }

        // Complete the parametric solution
        for(i = 0; i < m; i++){
            // Check the first index of nonzero element in a row
            for(j = 0; j < n; j++){
                k = j;
                if(matrix[i][j] != 0){
                    break;
                }
            }
            // Completion
            if(solution[i] != UNDEF && solution[i] != 0){
                parsolution[i] = solution[i] + " ";
            } else if(solution[i] != UNDEF && solution[i] == 0){
                parsolution[i] = "";
            }
            for(j = k; j < n; j++){
                if(matrix[i][j] > 0 && parvariable[j] != null && solution[i] != 0){
                    parsolution[i] += "- " + Double.toString(matrix[i][j]) +  parvariable[j] + " ";
                } else if(matrix[i][j] < 0 && parvariable[j] != null && solution[i] != 0){
                    parsolution[i] += "+ " + Double.toString(-matrix[i][j]) +  parvariable[j] + " ";
                } else if(matrix[i][j] > 0 && parvariable[j] != null && solution[i] == 0){
                    parsolution[i] += "- " + Double.toString(-matrix[i][j]) +  parvariable[j] + " ";
                } else if(matrix[i][j] < 0 && parvariable[j] != null && solution[i] == 0){
                    parsolution[i] += Double.toString(-matrix[i][j]) +  parvariable[j] + " ";
                }
            }
        }
        
        // Complete the remaining solutions
        for(i = 0; i < n-1; i++){
            if(parsolution[i] == null && parvariable[i] != null){
                parsolution[i] = parvariable[i];
            } else if(parsolution[i] == null && solution[i] != UNDEF){
                parsolution[i] = solution[i] + "";
            }
        }
        if(parvariable[n-1] == null){
            parsolution[n-1] = solution[n-1] + "";
        } else{
            parsolution[n-1] = parvariable[n-1];
        }

        // Output Parametric Solution
        for(i = 0; i < n; i++){
            System.out.println("x" + (i+1) + " = " + parsolution[i]);
       }
    }
    
    public static boolean isParametrik(double [][]matrix){
        int i, j, zero_row = 0;
        int m = matrix.length, n = matrix[0].length;
        OperasiPrimitif.gauss_jordan(matrix);

        for(j = 0; j < n; j++){
            zero_row = j;
            if(matrix[m-1][j] != 0){
                break;
            }
        }
        if((zero_row == n-1 && matrix[m-1][n-1] == 0) | (n - 1 > m)){    // Solusi parametrik
            return true;
        } else{
            return false;
        }
    }
}

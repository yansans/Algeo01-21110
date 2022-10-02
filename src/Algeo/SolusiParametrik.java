public class SolusiParametrik {
    public static String[] output(double[][] matrix){
        int i, j, k = 0, l;
        int m = matrix.length, n = matrix[0].length;
        double value;
        int hex = 0x61;
        int index = n-2;
        double[] solution = new double[n-1];
        String[] suppose = new String[n-1];
        String[] parsolution = new String[n-1];
        // Set solutions to undefined
        for(i = 0; i < n-1; i++){
            solution[i] = -9999;
            suppose[i] = "UNDEF";
            parsolution[i] = "UNDEF";
        }

        // Gauss Jordan
        OperasiPrimitif.gauss_jordan(matrix);

        for(i = m-1; i >= 0; i--){
            for(j = 0; j < n; j++){
                if(matrix[i][j] != 0){
                    break;
                }
                if(j == n-1 && matrix[i][n-1] == 0){
                    suppose[k] = Character.toString((char)hex);
                    k++;
                    hex++;
                    index--;
                }
            }
        }
        j = 0;
        for(i = 0; i < m-k; i++){
            solution[j] = matrix[j][n-1];
            j++;
        }

        i = 0;
        while(solution[i] != -9999){
            if(solution[i] != 0){
                parsolution[i] = solution[i] + " ";
            } else{
                parsolution[i] = "";
            }
            j = 0;
            l = index + 1;
            while(j != k){
                if(matrix[i][l] < 0){
                    parsolution[i] += "+" + Double.toString(-matrix[i][l]) + suppose[j] + " ";
                    l++;
                    j++;
                } else{
                    parsolution[i] += Double.toString(-matrix[i][l]) + suppose[j] + " ";
                    l++;
                    j++;
                }
            }
            k--;
            i++;     
        }
        i = 0;
        while(parsolution[i] != "UNDEF"){
            i++;
        }
        j = 0;
        while(suppose[j] != "UNDEF"){
            parsolution[i] = suppose[j];
            i++;
            j++;
        }

        return parsolution;
    }

    public static void main(String[] args){
        int i;
        double[][] matrix = {{0, 0, -2, 0,7, 12},
                            {2, 4, -10, 6, 12, 28},
                            {2, 4, -5, 6, -5, -1}};
        String[] solution = output(matrix);

        for(i = 0; i < matrix[0].length - 1; i++){
            System.out.println(solution[i]);
        }
    }
}
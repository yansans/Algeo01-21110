import java.lang.Math;

public class Interpolation {
    public static double polinom(double[][] A, double[][] b, double x){
        int i, j;
        int m = A.length, n = A[0].length;
        double y = 0d;
        double[] solution = new double[n];

        // Solve SPL
        solution = SPL_Elimination.gauss_jordan(A, b);

        // Taksiran
        for(i = 0; i < n; i++){
            y += solution[i] * Math.pow(x, i);
        }
        return y;
    }
}
package LinearAlgebraMatrixOperations;
import java.lang.Math;

public class InterpolasiPolinom {
    public static void estimate(double[][] A, double[] b, double x){
        int i, j;
        int m = A.length, n = A[0].length;
        double y = 0d;
        double[] solution = new double[n];

        // Solve SPL
        solution = OperasiSPL.SPLgauss_jordan(A, b);

        // Taksiran
        for(i = 0; i < n; i++){
            y += solution[i] * Math.pow(x, i);
        }
        
        // Output Interpolation Result
        System.out.print("f(x) = ");
        System.out.printf("%.4f", solution[n-1]);
        System.out.print("x^" + (n-1));
        for(i = n-2; i >= 2; i--){
            if(solution[i] >= 0){
                System.out.print(" + ");
                System.out.printf("%.4f", solution[i]);
                System.out.print("x^" + i);
            } else{
                System.out.print(" - ");
                System.out.printf("%.4f", solution[i]);
                System.out.print("x^" + i);
            }
        }
        if(solution[1] >= 0){
            System.out.print(" + ");
            System.out.printf("%.4f", solution[1]);
            System.out.print("x");
        } else{
            System.out.print(" - ");
            System.out.printf("%.4f", solution[1]);
            System.out.print("x");
        }
        if(solution[0] >= 0){
            System.out.print(" + ");
            System.out.printf("%.4f,\n", solution[0]);
        } else{
            System.out.print(" - ");
            System.out.printf("%.4f,\n", solution[0]);
        }
        System.out.print("f(" + x + ") = ");
        System.out.printf("%.4f\n", y);
    }
}
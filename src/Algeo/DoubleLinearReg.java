package Algeo;

public class DoubleLinearReg {

    public static double[][] doubRegSolveCof(double[][] x, double[][] y){
        double[][] xt ; //[x.length][x[0].length]
        double[][] invxxt;
        double [][] xxt ;
        xt = OperasiPrimitif.transpose(x);
        xxt = OperasiPrimitif.PerkalianMatrix(xt,x);
        invxxt = OperasiInverse.inverseCofactor(xxt);

        // b =  (XX^)^-1X^Y
        double[][] solve = OperasiPrimitif.PerkalianMatrix(OperasiPrimitif.PerkalianMatrix(invxxt, xt),y);
        return solve;
    }

    public static double[][] doubRegSolveIdent(double[][] x, double[][] y){
        double[][] xt ; //[x.length][x[0].length]
        double [][] xxt ;
        xt = OperasiPrimitif.transpose(x);
        xxt = OperasiPrimitif.PerkalianMatrix(xt,x);
        double[][] invxxt = new double[xxt.length][xxt.length];
        OperasiPrimitif.copyMatrix(xxt, invxxt);
        OperasiInverse.inverseIdentity(invxxt);
        // b =  (XX^)^-1X^Y
        double[][] solve = OperasiPrimitif.PerkalianMatrix(invxxt, OperasiPrimitif.PerkalianMatrix(xt,y));
        return solve;
    }

    public static void estimateDoubReg(double[][] x, double[][] y, double[][]a){
        double[][] solution = doubRegSolveIdent(x, y);

        int m = solution.length, n = solution[0].length;

        // Output Interpolation Result
        System.out.print("f(x) = ");
        for (int i = 0 ; i < m; i++){
            if (i != m-1){
                if (i != 0){
                    System.out.printf("%.4f", solution[i][0]);
                    System.out.print("x"+i+" + ");
                }else{
                    System.out.printf("%.4f + ", solution[i][0]);
                }
            }else{
                System.out.printf("%.4f", solution[i][0]);
                System.out.print("x"+i);
            }

        }
        System.out.println();
        double est = solution[0][0];
        System.out.print("f(xk) = ");
        for (int i = 1 ; i < m ; i++){
            est += solution[i][0] * a[i-1][0];
        }
        System.out.printf("%.4f",est);
    }
}

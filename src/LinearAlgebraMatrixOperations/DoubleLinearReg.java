package LinearAlgebraMatrixOperations;

public class DoubleLinearReg {

    public static double[][] doubRegSolveCof(double[][] x, double[][] y){
        double[][] xt ; //[x.length][x[0].length]
        double[][] invxxt;
        double [][] xxt ;
        xt = LinearAlgebraMatrixOperations.OperasiPrimitif.transpose(x);
        xxt = OperasiPrimitif.PerkalianMatrix(xt,x);
        invxxt = LinearAlgebraMatrixOperations.OperasiInverse.inverseCofactor(xxt);

        // b =  (XX^)^-1X^Y
        double[][] solve = OperasiPrimitif.PerkalianMatrix(OperasiPrimitif.PerkalianMatrix(invxxt, xt),y);
        return solve;
    }

//    public static double[][] doubRegSolveIdent(double[][] x, double[][] y){
//        double[][] xt ; //[x.length][x[0].length]
//        double [][] xxt ;
//        xt = transpose(x);
//        xxt = PerkalianMatrix(xt,x);
//        double[][] invxxt = new double[xxt.length][xxt.length];
//        copyMatrix(xxt, invxxt);
//        inverse(invxxt, xxt.length);
//        // b =  (XX^)^-1X^Y
//        double[][] solve = PerkalianMatrix(invxxt,PerkalianMatrix(xt,y));
//        return solve;
//    }

}

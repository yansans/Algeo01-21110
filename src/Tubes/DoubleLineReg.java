package Tubes;

import static Tubes.Cofactor.*;
import static Tubes.MatrixMultiplication.*;
import static Tubes.IdentityMatrix.*;

public class DoubleLineReg {

    public static double[][] doubRegSolve(double[][] x, double[][] y){
        double[][] xt ; //[x.length][x[0].length]
        double[][] invxxt ;
        double [][] xxt ;
        xt = transpose(x);
        xxt = PerkalianMatrix(xt,x);
        invxxt = xxt;
        inverse(invxxt,xxt.length);
        // b =  (XX^)^-1X^Y
        double[][] solve = PerkalianMatrix(PerkalianMatrix(invxxt, xt),y);
        return solve;
    }

}

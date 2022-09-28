package Tubes;


public class BicubicInterpolation {
    public static double InterpolasiLinear(double[] nilai, double ax){
        double[][] matrix ={{1,0}, 
                            {1,1}};
        double[] solusi = SPLCrammer.SolusiCrammer(matrix, nilai);

        double interpolasi = 0;
        for(int i=0;i<2;i++){
            interpolasi += solusi[i] * Math.pow(ax, i);
        }
        return interpolasi;
    }

    public static double InterpolasiCubic(double[] nilai, double ax){
        double[][] matrix = {   {1,-1,1,-1},
                                {1,0,0,0},
                                {1,1,1,1},
                                {1,2,4,8}};
        double[] solusi = SPLCrammer.SolusiCrammer(matrix, nilai); // {a0, a1, a2, a3}
        
        double interpolasi = 0;
        for(int i=0;i<4;i++){
            interpolasi += solusi[i] * Math.pow(ax, i);
        }
        return interpolasi;
    }

    public static double InterpolasiCubicSpline(double[] nilai, double ax){
        double[][] matrix ={{1,0,0,0},
                            {1,1,1,1},
                            {0,1,0,0},
                            {0,1,2,3}};
        double[][] derivativematrix = { {0,2,0,0},
                                        {0,0,2,0},
                                        {-1,0,1,0},
                                        {0,-1,0,1}};
        matrix = SPLInv.InverseOBE(matrix); // X^-1
        derivativematrix = MatrixMultiplication.PerkalianMatrixKonstanta(derivativematrix, 0.5); // D
        matrix = MatrixMultiplication.PerkalianMatrix(matrix, derivativematrix); // X^-1 * D
        double[] solusi = MatrixMultiplication.SolusiSPL(matrix, nilai);

        int dimensi = 4;
        double interpolasi = 0;
        for(int i=0;i<dimensi;i++){
            interpolasi += solusi[i] * Math.pow(ax, i);
        }
        return interpolasi;
    }

    public static double[] BarisMatrixInterpolasiBilinear(double x, double y){
        double[] baris = {1, x, y, x*y};
        return baris;
    }

    public static double InterpolasiBilinear(double[] nilai, double ax, double ay){
        // input array nilai = {f(0,0), f(1,0), f(0,1), f(1,1)}
        double[][] MatrixModel = new double[4][4];
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                MatrixModel[j+i*2] = BarisMatrixInterpolasiBilinear(j, i);
            }
        }
        double[] solusi = SPLCrammer.SolusiCrammer(MatrixModel, nilai);
        
        double interpolasi = 0;
        double[] baris = BarisMatrixInterpolasiBilinear(ax, ay);
        for(int i=0;i<4;i++){
            interpolasi += baris[i] * solusi[i];
        }
        return interpolasi;
    }

    public static double[] BarisMatrixInterpolasiBicubicNormal(double x, double y){
        double[] baris = new double[16];
        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                baris[i+j*4] = Math.pow(x, i) * Math.pow(y, j);
            }
        }
        return baris;
    }    
    
    public static double[] BarisMatrixInterpolasiBicubicDx(double x, double y){
        double[] baris = new double[16];
        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                if(i == 0)baris[i+j*4] = 0;
                else baris[i+j*4] = i * Math.pow(x, i-1) * Math.pow(y, j);
            }
        }
        return baris;
    }

    public static double[] BarisMatrixInterpolasiBicubicDy(double x, double y){
        double[] baris = new double[16];
        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                if(j == 0)baris[i+j*4] = 0;
                else baris[i+j*4] = j * Math.pow(x, i) * Math.pow(y, j-1);
            }
        }
        return baris;
    }

    public static double[] BarisMatrixInterpolasiBicubicDxy(double x, double y){
        double[] baris = new double[16];
        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                if(i == 0 || j == 0)baris[i+j*4] = 0;
                else baris[i+j*4] = i * j * Math.pow(x, i-1) * Math.pow(y, j-1);
            }
        }
        return baris;
    }

    public static double InterpolasiBicubic(double[] nilai, double ax, double ay){
        double[][] MatrixModel = new double[16][16];
        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){
                MatrixModel[x+y*4] = BarisMatrixInterpolasiBicubicNormal(x-1, y-1);
            }
        }
        double[] solusi = SPLCrammer.SolusiCrammer(MatrixModel, nilai);
        
        double interpolasi = 0;
        double[] baris = BarisMatrixInterpolasiBicubicNormal(ax, ay);
        for(int i=0;i<16;i++){
            interpolasi += baris[i] * solusi[i];
        }
        return interpolasi;
    }

    public static double InterpolasiBicubicSpline(double[] nilai, double ax, double ay){
        double[][] MatrixModel = new double[16][16];
        for(int y=0;y<2;y++){ // baris normal
            for(int x=0;x<2;x++){
                MatrixModel[x+y*2] = BarisMatrixInterpolasiBicubicNormal(x, y);
            }
        }
        for(int y=0;y<2;y++){ // baris Dx
            for(int x=0;x<2;x++){
                MatrixModel[x+y*2+4] = BarisMatrixInterpolasiBicubicDx(x, y);
            }
        }
        for(int y=0;y<2;y++){ // baris Dy
            for(int x=0;x<2;x++){
                MatrixModel[x+y*2+8] = BarisMatrixInterpolasiBicubicDy(x, y);
            }
        }
        for(int y=0;y<2;y++){ // baris Dxy
            for(int x=0;x<2;x++){
                MatrixModel[x+y*2+12] = BarisMatrixInterpolasiBicubicDxy(x, y);
            }
        }

        double[][] MatrixDerivative = new double[16][16];
        for(int y=0;y<2;y++){ // baris normal
            for(int x=0;x<2;x++){
                MatrixDerivative[x+y*2][x+y*4+5] = 1;
            }
        }
        for(int y=0;y<2;y++){ // baris Dx
            for(int x=0;x<2;x++){
                MatrixDerivative[x+y*2+4][x+y*4+6] = 0.5;
                MatrixDerivative[x+y*2+4][x+y*4+4] = -0.5;
            }
        }
        for(int y=0;y<2;y++){ // baris Dy
            for(int x=0;x<2;x++){
                MatrixDerivative[x+y*2+8][x+y*4+9] = 0.5;
                MatrixDerivative[x+y*2+8][x+y*4+1] = -0.5;
            }
        }
        for(int y=0;y<2;y++){ // baris Dxy
            for(int x=0;x<2;x++){
                MatrixDerivative[x+y*2+12][x+y*4+10] = 0.25;
                MatrixDerivative[x+y*2+12][x+y*4+4] = -0.25;
                MatrixDerivative[x+y*2+12][x+y*4+1] = -0.25;
                MatrixDerivative[x+y*2+12][x+y*4+5] = 0.25;
            }
        }
        MatrixModel = Cofactor.inverse(MatrixModel); // X^-1
        double[][] RecycleMatrix = MatrixMultiplication.PerkalianMatrix(MatrixModel, MatrixDerivative); // X^-1 * D
        double[] solusi = MatrixMultiplication.SolusiSPL(RecycleMatrix, nilai);

        double interpolasi = 0;
        double[] baris = BarisMatrixInterpolasiBicubicNormal(ax, ay);
        for(int i=0;i<16;i++){
            interpolasi += baris[i] * solusi[i];
        }
        return interpolasi;
    }

    public static void main(String[] args){

    }
}

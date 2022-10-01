package LinearAlgebraMatrixOperations;

public class OperasiInverse {

    public static double[][] adjoint(double[][] m){
        // Mendapatkan matriks adjoin dari m secara rekursif
        int n = m.length;
        double[][] adj = new double[n][n];
        // if (n == 1){
        //     adj[0][0] = 1;
        //     return adj;
        // }
        int sign = 1;
        double[][] temp = new double[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                OperasiPrimitif.getCofactor(m, temp, i, j, n);
                sign = ((i + j) % 2 == 0) ? 1 : -1;
                adj[j][i] = (sign) * (OperasiDeterminan.determinanCofactor(temp, n - 1));
            }
        }
        return adj;
    }
    public static double[][] inverseCofactor(double[][] m){
        // Mendapatkan matriks invers dari m dari adjoint dan determinan
        int n = m.length;
        double[][] inv = new double[n][n];
        double det = OperasiDeterminan.determinanCofactor(m, n);
        if (det == 0){
            System.out.println("Determinan 0, tidak memiliki invers");
            return inv;
        }
        double[][] adj = adjoint(m);
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                inv[i][j] = adj[i][j] / det;
            }
        }
        return inv;
    }
}

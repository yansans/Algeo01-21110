package Tubes;

public class SPLCrammer{
    
    public static double[] SolusiCrammer(double[][] matrix, double[] nilai){
        int n = nilai.length;
        double[] solusi = new double[n];
        double determinanUtama = DetOBE.DeterminanOBE(matrix);
        if(determinanUtama == 0){
            return solusi; // jika determinan matrix utama 0 maka tidak ada solusi
        }

        double[][] matrixtmp;
        for(int i=0;i<n;i++){ // iterate seluruh kolom yang akan diganti
            matrixtmp = DebugMatrix.CopyMatrix(matrix); // copy matrix
            for(int j=0;j<n;j++){
                matrixtmp[j][i] = nilai[j]; // ganti nilai kolom matrix
            }
            double determinanKecil = DetOBE.DeterminanOBE(matrixtmp);
            solusi[i] = determinanKecil/determinanUtama;
        }
        return solusi;
    }

    public static void PrintCrammer(double[][] matrix, double[] nilai){
        int n = nilai.length;
        double determinanUtama = DetOBE.DeterminanOBE(matrix);
        if(determinanUtama == 0){
            System.out.print("Tidak ada solusi.");
        }else{
            double[] solusi = SolusiCrammer(matrix, nilai);
            System.out.println("Solusi persamaan tersebut adalah : ");
            for(int i=0;i<n;i++){
                System.out.println("x" + i + " : " + solusi[i]);
            }
        }
    }

    public static void main(String[] args){
        // contoh soal PPT eliminasi Gauss - Jordan
        double[][] persamaan1 = {{2,3,-1}, {4,4,-3}, {-2,3,-1}};
        double[] nilai1 = {5,3,1};
        PrintCrammer(persamaan1, nilai1);

        // contoh soal PPT penyelesaian SPL dengan Inverse
        double[][] persamaan2 = {{1,2,3}, {2,5,3}, {1,0,8}};
        double[] nilai2 = {5,3,1};
        PrintCrammer(persamaan2, nilai2);
    }
}

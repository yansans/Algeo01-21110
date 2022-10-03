package src.Algeo;
import java.io.File;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ScalingCitra{
    public static int[][] takeImage(String inputImageAbsPath) {
        BufferedImage img = null;
        // BufferedImage img = new BufferedImage(4, 4, BufferedImage.TYPE_4BYTE_ABGR); // image file
        // Read the source image or throw an exception
        try {
            img = ImageIO.read(new File(inputImageAbsPath));
        } catch(Exception e) {
            e.printStackTrace();
        }

        // Get the image width and height dimensions
        int width = img.getWidth();
        int height = img.getHeight();
        int[][] matrix = new int[height][width];

        // Convert to grayscale by looping over pixels, beginning at top-most left coordinate (0,0)
        for (int y = 0; y < height; y++) { // y = rows
            for (int x = 0; x < width; x++) { // x = columns
        
                // Get the pixel value at this (x,y) coordinate
                int p = img.getRGB(x,y);
        
                // Extract the alpha, R, G, B values from pixel p
                int a = (p>>24) & 0xff; // Shift bits and unsign
                int r = (p>>16) & 0xff;
                int g = (p>>8) & 0xff;
                int b = p & 0xff;
        
                // Calculate average color (grayscale it)
                int avg = (r+g+b)/3;
        
                // Replace RGB value with avg
                p = (a<<24) | (avg<<16) | (avg<<8) | avg;
                matrix[y][x] = p;
            }
        }
        return matrix;
    }

    public static void saveImage(String output, int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < height; y++) { // y = rows
            for (int x = 0; x < width; x++) { // x = columns
                image.setRGB(x, y, matrix[y][x]);
            }
        }
        // Save or throw exception
        try {
            System.out.println("... Saving image to " + output);
            ImageIO.write(image, "jpg", new File(output));
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("... Image saved.");
    }
    
    public static double[][] IntToDoubleMat(int[][] Matrix){
        int n = Matrix.length;
        int m = Matrix[0].length;
        double[][] Matrixout = new double[n][m];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                Matrixout[i][j] = Matrix[i][j];
            }
        }
        return Matrixout;
    }

    public static int[][] DoubleToIntMat(double[][] Matrix){
        int n = Matrix.length;
        int m = Matrix[0].length;
        int[][] Matrixout = new int[n][m];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                Matrixout[i][j] = (int)Matrix[i][j];
            }
        }
        return Matrixout;
    }

    public static boolean inbound(double[][] Matrix, int i, int j){
        // check if index i,j is inside matrix
        int n = Matrix.length;
        int m = Matrix[0].length;
        boolean cond = (i < n) && (j < m);
        return cond;
    }

    public static double[][] prosesBicubic(double[][] Matrix){
        int n = Matrix.length;
        int m = Matrix[0].length;
        double[][] hasil = new double[2*n][2*m];
        boolean[][] processed = new boolean[2*n][2*m];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                hasil[i*2][j*2] = Matrix[i][j];
                processed[i*2][j*2] = true;
            }
        }
        
        // interpolasi sisi
        for(int i=1;i<2*n;i+=2){
            hasil[i][0] = hasil[i-1][0];
            processed[i][0] = true;
        }
        for(int j=1;j<2*m;j+=2){
            hasil[0][j] = hasil[0][j-1];
            processed[0][j] = true;
        }
        for(int i=1;i<2*n;i++){
            hasil[i][1] = (hasil[i-1][1] + hasil[i][0])/2;
            processed[i][1] = true;
        }
        for(int j=1;j<2*m;j++){
            hasil[1][j] = (hasil[0][j] + hasil[1][j-1])/2;
            processed[1][j] = true;
        }
        
        for(int i=0;i<2*n;i+=2){ // iterate all idx
            for(int j=0;j<2*m;j+=2){
                double last = hasil[i][j];
                double[] value = new double[16];
                for(int k=0;k<4;k++){
                    for(int l=0;l<4;l++){
                        if(inbound(hasil, i+2*k, j+2*l) && !processed[i+2*k][j+2*l]){
                            last = hasil[i+2*k][j+2*l];
                        }
                        value[4*k + l] = last;
                    }
                }
                
                double[] solusiInterpolasi = InterpolasiBicubic.solusiInterpolasiBicubicSpline(value);
                for(int k=2;k<5;k++){
                    for(int l=2;l<5;l++){
                        if(!inbound(hasil, i+k, j+l) || processed[i+k][j+l])continue;
                        double interpolasi = InterpolasiBicubic.hasilInterpolasiBicubicSpline(solusiInterpolasi, (k-2)/2, (l-2)/2);
                        hasil[i+k][j+l] = interpolasi;
                        processed[i+k][j+l] = true;
                    }
                }
            }
        }

        return hasil;
    }

    public static void main(String args[]){
        String input, output;
        Scanner scan = new Scanner(System.in);
        System.out.print("Masukkan path image yang ingin diperbesar : ");
        input = scan.nextLine();
        System.out.print("Masukkan path dimana image akan disimpan : ");
        output = scan.nextLine();

        scan.close();
        int[][] convertedImage = takeImage(input);
        double[][] doublematrix = IntToDoubleMat(convertedImage);
        double[][] hasilproses = prosesBicubic(doublematrix);
        int[][] outputmatrix = DoubleToIntMat(hasilproses);
        saveImage(output, outputmatrix);
        
        System.out.println("\nScaling selesai.");
    }
}
package src.Algeo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.exit;
import static src.Algeo.IOFiles.*;


/*
    1 1 2 | 3
    0 1 1 | 2
    0 0 0 | 0

    y[]
    x[]
    bagi[]
    loop search baris 0 = [baris3] = batas
    hasil[] = new int[n]
    loop i travesal [batas-1 ... 0]
        loop i travesal [batas-1 ... 0]
           y[j] -= x[j][i]
           if i == j
                bagi[i] = x[i][j]
    x3 = a
    x2 = (x[2][4] - x[2][1] - x[2][3]) / x[2][2]
    x2 = 2 - 5 - a / 1
    x2 = 2/1 - 5/1 - a/1 = -3 + a
    x1 = (x[1][4] - x[1][3] - x[1][2] / x[1][1]
 */

public class Menu {

    public static void daftarMenu(){
        System.out.print("""
                Menu
                1. Sistem Persamaan Linier
                2. Determinan
                3. Matriks balikan
                4. Interpolasi Polinom
                5. Interpolasi Bicubic
                6. Regresi linier berganda
                7. Keluar
                Masukan nomor menu :
                """);

    }

    public static void subMenu(){
        System.out.print("""
                Menu
                1. Metode eliminasi Gauss
                2. Metode eliminasi Gauss-Jordan
                3. Metode Matriks balikan
                4. Kaidah Cramer
                5. Kembali
                Masukan nomor menu :
                """);
    }

    public static int ioMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.print("""
                Menu
                1. Input Terminal
                2. Input Files
                Masukan nomor menu :
                """);
        scan.close();
        return scan.nextInt();
    }
    public static void filesIO(){
        double[][] matrix = new double[1][1];
        boolean statusread, statuswrite;
        statusread = statuswrite = false;
        Scanner scan = new Scanner(System.in);
        System.out.println("Masukan nama dan/atau lokasi file");
        String file = scan.nextLine();
        try {
            matrix = readMatrix(file);
        }catch (FileNotFoundException e){
            System.out.println("File tidak ditemukan");
        }
        System.out.println("Masukan nama dan/atau lokasi penyimpanan file");
        String save = scan.nextLine();
        try {
            writeMatrix(save, matrix);
        } catch (IOException e) {
            System.out.println("Terjadi error.");
            System.out.println("File gagal disimpan.");
        }
            System.out.println("File berhasil disimpan.");
    }

    public static void spl(int menu2){
        if (menu2 == 1){

        } else if (menu2 == 2) {

        } else if (menu2 == 3) {

        }
    }
    public static void determinan(int menu2){
        if (menu2 == 1) {

        } else if (menu2 == 2) {

        } else if (menu2 == 3) {

        }
    }
    public static void inverse(int menu2){
        if (menu2 == 1) {

        } else if (menu2 == 2) {

        } else if (menu2 == 3) {

        }
    }
    public static void operasiMatriks(int menu1, int menu2) {
        if (menu1 == 1) {

        } else if (menu1 == 2) {


        } else if (menu1 == 3) {

        }
    }

    public static void aplikasiMatriks(int menu1){
        if (menu1 == 1) {

        } else if (menu1 == 2) {

        } else if (menu1 == 3) {

        }
    }

    public static void menuLoop() {
        int menu1, menu2;
        Scanner input = new Scanner(System.in);
        menu1 = input.nextInt();
        while (true) {
            if (menu1 >= 1 && menu1 <= 3) {
                subMenu();
                menu2 = input.nextInt();
                while (menu2 != 5) {
                    System.out.println("Masukan menu yang valid.");
                    menu2 = input.nextInt();
                }
            } else if (menu1 >= 4 && menu1 <= 6) {
            }
            if (menu1 < 1 || menu1 > 7) {
                System.out.println("Masukan menu yang valid.");

            }
            if (menu1 == 7) {
                input.close();
                quit();
            }
            daftarMenu();
            menu1 = input.nextInt();
        }
    }

    public static void quit(){
        System.out.println("Exiting...");
        exit(0);
    }

    public static void main(String[]args) {
        daftarMenu();
        menuLoop();

    }
}

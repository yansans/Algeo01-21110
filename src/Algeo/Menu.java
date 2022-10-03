package src.Algeo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.exit;
import static src.Algeo.IOFiles.*;

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

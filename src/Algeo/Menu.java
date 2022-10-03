package Algeo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.exit;
import static Algeo.IOFiles.*;
import static Algeo.IOTerminal.*;
import static Algeo.ScalingCitra.*;

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
                7. Perbesaran Citra
                8. Keluar
                Masukan nomor menu :
                """);
    }

    public static int subMenuSPL(){
        Scanner scan = new Scanner(System.in);
        System.out.print("""
                Menu
                1. Metode eliminasi Gauss
                2. Metode eliminasi Gauss-Jordan
                3. Metode Matriks balikan
                4. Kaidah Cramer
                Masukan nomor menu :
                """);
        return scan.nextInt();
    }

    public static int subMenuDet(){
        Scanner scan = new Scanner(System.in);
        System.out.print("""
                Menu
                1. Metode OBE
                2. Metode Kofaktor
                Masukan nomor menu :
                """);
        return scan.nextInt();
    }

    public static int subMenuInv(){
        Scanner scan = new Scanner(System.in);
        System.out.print("""
                Menu
                1. Metode OBE
                2. Metode Adjoin
                Masukan nomor menu :
                """);
        return scan.nextInt();
    }

    public static int iMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.print("""
                Menu
                1. Input Terminal
                2. Input Files
                Masukan nomor menu :
                """);
        return scan.nextInt();
    }
    public static int oMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.print("""
                Menu
                1. Output Terminal
                2. Output Files
                Masukan nomor menu :
                """);
        return scan.nextInt();
    }

    public static void spl(int menu2){
        int i = iMenu();
        if (menu2 == 1){
            if (i == 1) {
                int o = oMenu();
                MenuSPLGauss(o);
                return;
            } else if (i == 2) {
                Scanner input = new Scanner(System.in);
                System.out.println("Masukkan nama file yang akan disimpan : ");
                String file = input.nextLine();
                filesSPLGauss(file);
                return;
            } else {
                System.out.println("Input tidak dikenal.");
                return;
            }
        } else if (menu2 == 2) {
            if (i == 1) {
                int o = oMenu();
                MenuSPLGaussJordan(o);
                return;
            } else if (i == 2) {
                Scanner input = new Scanner(System.in);
                System.out.println("Masukkan nama file yang akan disimpan : ");
                String file = input.nextLine();
                filesSPLGaussJordan(file);
                return;
            } else {
                System.out.println("Input tidak dikenal.");
                return;
            }
        } else if (menu2 == 3) {
            if (i == 1) {
                int o = oMenu();
                MenuSPLInverse(o);
                return;
            } else if (i == 2) {
                Scanner input = new Scanner(System.in);
                System.out.println("Masukkan nama file yang akan disimpan : ");
                String file = input.nextLine();
                filesSPLInverse(file);
                return;
            } else {
                System.out.println("Input tidak dikenal.");
                return;
            }
        }else if (menu2 == 4) {
            if (i == 1) {
                int o = oMenu();
                MenuSPLCrammer(o);
                return;
            } else if (i == 2) {
                Scanner input = new Scanner(System.in);
                System.out.println("Masukkan nama file yang akan disimpan : ");
                String file = input.nextLine();
                filesSPLCrammer(file);
                return;
            } else {
                System.out.println("Input tidak dikenal.");
                return;
            }
        }else{
            System.out.println("Input tidak dikenal.");
        }
    }
    public static void determinan(int menu2) {
        int i = iMenu();
        if (menu2 == 1) {
            if (i == 1) {
                int o = oMenu();
                MenuDeterminanOBE(o);
                return;
            } else if (i == 2) {
                Scanner input = new Scanner(System.in);
                System.out.println("Masukkan nama file yang akan disimpan : ");
                String file = input.nextLine();
                filesDeterminanOBE(file);
                return;
            } else {
                System.out.println("Input tidak dikenal.");
                return;
            }
        } else if (menu2 == 2) {
            if (i == 1) {
                int o = oMenu();
                MenuDeterminanCofactor(o);
                return;
            } else if (i == 2) {
                Scanner input = new Scanner(System.in);
                System.out.println("Masukkan nama file yang akan disimpan : ");
                String file = input.nextLine();
                filesDeterminanCofactor(file);
                return;
            } else {
                System.out.println("Input tidak dikenal.");
                return;
            }
        }else{
            System.out.println("Input tidak dikenal.");
        }
    }
    public static void inverse(int menu2){
        int i = iMenu();
        if (menu2 == 1) {
            if (i == 1) {
                int o = oMenu();
                MenuInversOBE(o);
                return;
            } else if (i == 2) {
                Scanner input = new Scanner(System.in);
                System.out.println("Masukkan nama file yang akan disimpan : ");
                String file = input.nextLine();
                filesInverseOBE(file);
                return;
            } else {
                System.out.println("Input tidak dikenal.");
                return;
            }
        } else if (menu2 == 2) {
            if (i == 1) {
                int o = oMenu();
                MenuInversAdjoin(o);
                return;
            } else if (i == 2) {
                Scanner input = new Scanner(System.in);
                System.out.println("Masukkan nama file yang akan disimpan : ");
                String file = input.nextLine();
                filesInverseAdjoint(file);
                return;
            } else {
                System.out.println("Input tidak dikenal.");
                return;
            }
        }else{
            System.out.println("Input tidak dikenal.");
        }
    }
    public static void operasiMatriks(int menu1) {
        int menu2;
        if (menu1 == 1) {
            menu2 = subMenuSPL();
            if (menu2 > 0 && menu2 < 5){
                spl(menu2);
            }else return;
        } else if (menu1 == 2) {
            menu2 = subMenuDet();
            if (menu2 > 0 && menu2 < 3) {
                determinan(menu2);
            }else return;
        } else if (menu1 == 3) {
            menu2 = subMenuInv();
            if (menu2 > 0 && menu2 < 3) {
                inverse(menu2);
            }else return;
        } else{
            System.out.println("Input tidak dikenal.");
        }
    }

    public static void interPol(){
        int i = iMenu();
        if (i == 1){
            int o = oMenu();
            MenuInterpolasiPolinom();
            return;
        }else if(i == 2){
            Scanner input = new Scanner(System.in);
            System.out.println("Masukkan nama file yang akan disimpan : ");
            String file = input.nextLine();
            inputFilesPolinom(file);
            return;
        }else{
            System.out.println("Input tidak dikenal.");
        }
    }

    public static void interBic(){
        int i = iMenu();
        if (i == 1){
            int o = oMenu();
            MenuInterpolasiBicubic();
            return;
        }else if(i == 2){
            Scanner input = new Scanner(System.in);
            System.out.println("Masukkan nama file yang akan disimpan : ");
            String file = input.nextLine();
            filesBicubic(file);
            return;
        }else{
            System.out.println("Input tidak dikenal.");
        }
    }

    public static void doubReg(){
        int i = iMenu();
        if (i == 1){
            int o = oMenu();
            MenuRegresiLinierBerganda();
            return;
        }else if(i == 2){
            Scanner input = new Scanner(System.in);
            System.out.println("Masukkan nama file yang akan disimpan : ");
            String file = input.nextLine();
            inputFilesDoubleReg(file);
            return;
        }else{
            System.out.println("Input tidak dikenal.");
        }
    }
    public static void scalingImage(){
        scalingCitra();
    }
    public static void aplikasiMatriks(int menu1){
        if (menu1 == 4) {
            interPol();
        } else if (menu1 == 5) {
            interBic();
        } else if (menu1 == 6) {
            doubReg();
        }else if (menu1 == 7){
            scalingImage();
        }else{
            System.out.println("Input tidak dikenal.");
        }
    }


    public static void menuLoop() {
        daftarMenu();
        int menu1, menu2;
        Scanner input = new Scanner(System.in);
        menu1 = input.nextInt();
        while (true) {
            if (menu1 >= 1 && menu1 <= 3) {
                operasiMatriks(menu1);
                System.out.println("Kembali ke menu utama.");
            } else if (menu1 >= 4 && menu1 <= 7) {
                aplikasiMatriks(menu1);
                System.out.println("Kembali ke menu utama.");
            }
            if (menu1 < 1 || menu1 > 8) {
                System.out.println("Masukan menu yang valid.");
            }
            if (menu1 == 8) {
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

}

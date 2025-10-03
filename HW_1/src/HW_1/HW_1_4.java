package HW_1;

import java.util.Scanner;

public class HW_1_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введіть три цілих числа: ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        double geomMean = Math.cbrt(a * b * c);
        System.out.printf("Середнє геометричне = %.4f%n", geomMean);
    }
}
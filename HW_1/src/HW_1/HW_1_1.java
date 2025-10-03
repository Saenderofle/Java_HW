package HW_1;

import java.util.Scanner;

public class HW_1_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введіть ім’я: ");
        String name = sc.nextLine();
        System.out.println("Привіт, " + name + "!");
    }
}
package HW_2;

import java.util.Scanner;

public class HW_2_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть число (0-255): ");
        int number = scanner.nextInt();

        // Обмежуємо число одним байтом (0-255) для коректності прикладу
        number = number & 0xFF;

        // Виводимо двійкове представлення для наочності
        String binary = String.format("%8s", Integer.toBinaryString(number)).replace(' ', '0');
        System.out.println("Двійковий запис: " + binary);

        int count = 0;
        int temp = number;

        // Перевіряємо 7 пар сусідніх бітів
        for (int i = 0; i < 7; i++) {
            // Маска 3 (двійкове 11) перевіряє два молодші біти
            if ((temp & 3) == 3) {
                count++;
            }
            // Зсуваємо число вправо на 1 позицію
            temp >>= 1;
        }

        System.out.println("Кількість входжень '11': " + count);
    }
}
package HW_2;

import java.util.Scanner;

public class HW_2_07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Введення масиву
        System.out.print("Введіть кількість елементів масиву: ");
        int n = scanner.nextInt();
        int[] array = new int[n];

        System.out.println("Введіть елементи масиву:");
        double sum = 0;
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
            sum += array[i];
        }

        // 2. Обчислення середнього арифметичного
        double average = sum / n;
        System.out.println("\nСереднє арифметичне: " + average);

        // 3. Обчислення та виведення відхилень (дисперсії елементів)
        System.out.print("Дисперсія (відхилення): ");
        for (int i = 0; i < n; i++) {
            // Обчислюємо за формулою: |x - avg|
            double deviation = Math.abs(array[i] - average);

            // Виводимо ціле число, якщо дробова частина відсутня
            if (deviation == (long) deviation) {
                System.out.print((long) deviation + (i < n - 1 ? ", " : ""));
            } else {
                System.out.print(deviation + (i < n - 1 ? ", " : ""));
            }
        }
        System.out.println();
    }
}
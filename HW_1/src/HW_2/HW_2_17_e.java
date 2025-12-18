package HW_2;

import java.util.Scanner;

public class HW_2_17_e {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть x (|x| < 1): ");
        double x = scanner.nextDouble();

        System.out.print("Введіть точність epsilon (> 0): ");
        double eps = scanner.nextDouble();

        double sum = calculateSin(x, eps);

        System.out.printf("Обчислена сума: %.10f\n", sum);
        System.out.printf("Значення Math.sin(x): %.10f\n", Math.sin(x));
    }

    /**
     * Обчислює суму ряду Тейлора для sin(x) з точністю eps
     */
    public static double calculateSin(double x, double eps) {
        double currentTerm = x; // Перший член ряду (k=0): x^1 / 1!
        double sum = currentTerm;
        int k = 1;

        // Продовжуємо, поки абсолютна величина доданка > eps
        while (Math.abs(currentTerm) > eps) {
            // Рекурентний перехід до наступного члена:
            // Множимо на -x^2 та ділимо на (2k * (2k + 1))
            currentTerm *= -(x * x) / ((2 * k) * (2 * k + 1));
            sum += currentTerm;
            k++;

            // Запобіжник для нескінченних циклів (якщо ряд не збігається)
            if (k > 1000000) break;
        }

        return sum;
    }
}
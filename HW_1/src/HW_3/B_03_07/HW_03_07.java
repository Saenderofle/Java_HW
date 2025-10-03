package HW_3.B_03_07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class HW_03_07 {
    private double x;
    private double y;
    private double radius;

    public HW_03_07(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }


    public double getArea() {
        return Math.PI * radius * radius;
    }

    
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    // Перевірка перетину двох кіл
    public boolean intersects(HW_03_07 other) {
        double distance = Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
        return distance < (this.radius + other.radius) && distance > Math.abs(this.radius - other.radius);
    }

    // Пошук пар кіл, що перетинаються
    public static ArrayList<String> findIntersectingPairs(HW_03_07[] circles) {
        ArrayList<String> intersectingPairs = new ArrayList<>();
        for (int i = 0; i < circles.length; i++) {
            for (int j = i + 1; j < circles.length; j++) {
                if (circles[i].intersects(circles[j])) {
                    intersectingPairs.add("Коло " + i + " перетинається з Коло " + j);
                }
            }
        }
        return intersectingPairs;
    }

    // Пошук кола з найбільшою площею
    public static HW_03_07 findLargestCircle(HW_03_07[] circles) {
        return Arrays.stream(circles)
                .max(Comparator.comparing(HW_03_07::getArea))
                .orElse(null);
    }

    @Override
    public String toString() {
        return "Коло{центр=(" + x + ", " + y + "), радіус=" + radius + ", площа=" + String.format("%.2f", getArea()) + "}";
    }

    public static void main(String[] args) {
        HW_03_07[] HW0307s = {
                new HW_03_07(0, 0, 5),
                new HW_03_07(8, 0, 5),
                new HW_03_07(20, 20, 10),
                new HW_03_07(15, 15, 4)
        };

        // Виведення всіх кіл
        System.out.println("Список кіл:");
        for (int i = 0; i < HW0307s.length; i++) {
            System.out.println("Коло " + i + ": " + HW0307s[i]);
        }

        // Виведення пар кіл, що перетинаються
        System.out.println("\nПари кіл, що перетинаються:");
        ArrayList<String> intersectingPairs = findIntersectingPairs(HW0307s);
        if (intersectingPairs.isEmpty()) {
            System.out.println("Немає кіл, що перетинаються.");
        } else {
            for (String pair : intersectingPairs) {
                System.out.println(pair);
            }
        }

        // Виведення кола з найбільшою площею
        System.out.println("\nКоло з найбільшою площею:");
        HW_03_07 largestCircle = findLargestCircle(HW0307s);
        if (largestCircle != null) {
            System.out.println(largestCircle);
        }
    }
}
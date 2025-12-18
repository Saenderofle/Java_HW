package HW_7;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HW_7_1 {
    public static void main(String[] args) {
        String fileF = "F.bin";
        String fileG = "G.bin";
        double thresholdA = 10.5; // Задане число a

        // Створення файлу F
        createFileF(fileF, new double[]{2.5, 12.0, 5.7, 20.1, 8.3, 15.0});

        // Зчитування масиву з файлу F
        double[] numbersFromF = readArrayFromFile(fileF);

        // Побудова файлу G
        buildFileG(fileG, numbersFromF, thresholdA);

        // Перевірка (вивід результату)
        System.out.println("Числа з файлу G (більші за " + thresholdA + "):");
        for (double d : readArrayFromFile(fileG)) {
            System.out.print(d + " ");
        }
    }

    public static void createFileF(String fileName, double[] data) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
            for (double d : data) dos.writeDouble(d);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static double[] readArrayFromFile(String fileName) {
        List<Double> list = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            while (dis.available() > 0) {
                list.add(dis.readDouble());
            }
        } catch (IOException e) { /* Кінець файлу */ }

        return list.stream().mapToDouble(Double::doubleValue).toArray();
    }

    public static void buildFileG(String fileName, double[] data, double a) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
            for (double d : data) {
                if (d > a) dos.writeDouble(d);
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}
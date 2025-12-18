package HW_7;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Клас іграшки повинен підтримувати серіалізацію
class Toy implements Serializable {
    String name;
    double price;
    int minAge;
    int maxAge;

    public Toy(String name, double price, int minAge, int maxAge) {
        this.name = name;
        this.price = price;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    @Override
    public String toString() {
        return String.format("Іграшка: %s, Ціна: %.2f грн, Вік: %d-%d", name, price, minAge, maxAge);
    }
}

public class ToyManager {
    public static void main(String[] args) {
        String allToysFile = "all_toys.dat";
        String filteredToysFile = "filtered_toys.dat";
        int childAge = 5; // Заданий вік

        // a) Створення файлу
        List<Toy> initialToys = List.of(
                new Toy("М'яч", 250, 3, 10),
                new Toy("Лялька", 500, 4, 8),
                new Toy("Конструктор", 1200, 6, 99),
                new Toy("Брязкальце", 100, 0, 2)
        );
        saveToysToFile(allToysFile, initialToys);

        // b) Зчитування та фільтрація
        List<Toy> allToys = readToysFromFile(allToysFile);
        List<Toy> suitableToys = new ArrayList<>();

        for (Toy toy : allToys) {
            if (childAge >= toy.minAge && childAge <= toy.maxAge) {
                suitableToys.add(toy);
            }
        }

        // Запис у новий файл
        saveToysToFile(filteredToysFile, suitableToys);

        // Вивід результату
        System.out.println("Іграшки для дитини віком " + childAge + " років:");
        suitableToys.forEach(System.out::println);
    }

    public static void saveToysToFile(String fileName, List<Toy> toys) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(toys);
        } catch (IOException e) { e.printStackTrace(); }
    }

    @SuppressWarnings("unchecked")
    public static List<Toy> readToysFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<Toy>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
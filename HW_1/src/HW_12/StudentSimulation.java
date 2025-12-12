package HW_12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StudentSimulation {

    public static void main(String[] args) {
        // Вкажіть тут назву вашого вхідного файлу
        String filename = "input14.txt";
        processDirtyFile(filename);
    }

    public static void processDirtyFile(String filename) {
        try {
            // 1. Зчитуємо весь файл як один великий рядок
            String content = new String(Files.readAllBytes(Paths.get(filename)));

            // 2. Видаляємо сміттєві теги через RegEx
            content = content.replaceAll("\\\\", " ");

            // 3. Розбиваємо на слова (токени) по будь-яких пробілах
            String[] tokens = content.trim().split("\\s+");

            if (tokens.length < 3) {
                System.out.println("File is too short or empty.");
                return;
            }

            int cursor = 0;
            // Читаємо шапку (тип, ціль, гроші)
            String typeStr = tokens[cursor++];
            int creditGoal = Integer.parseInt(tokens[cursor++]);
            int startMoney = Integer.parseInt(tokens[cursor++]);

            Student student = createStudent(typeStr, creditGoal, startMoney);

            System.out.println("Processing student: " + student.getClass().getSimpleName());
            System.out.println("Credit Goal: " + creditGoal + ", Initial Money: " + startMoney);

            // Читаємо команди трійками: Action -> Target -> Value
            while (cursor < tokens.length) {
                if (student.isExpelled()) break; // Якщо відрахований - виходимо

                // Перевірка, чи вистачає токенів на повну команду
                if (cursor + 2 >= tokens.length) break;

                String action = tokens[cursor++];
                String target = tokens[cursor++];
                String valueStr = tokens[cursor++];

                try {
                    int value = Integer.parseInt(valueStr);
                    Visitor visitor = createVisitor(action, target, value);

                    if (visitor != null) {
                        student.accept(visitor);
                    } else {
                        System.out.println("Skipping unknown command: " + action + " " + target);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing value: " + valueStr);
                }
            }

            // Виводимо результат
            System.out.println("--------------------------------------------------");
            System.out.println("Final Credits: " + student.getCredits() + " / " + creditGoal);
            System.out.println("Final Money: " + student.getMoney());

            if (student.hasDiploma()) {
                System.out.println("RESULT: Student obtained the diploma!");
            } else {
                System.out.print("RESULT: Student did NOT obtain the diploma. ");
                if (student.isExpelled()) {
                    System.out.println("Reason: Expelled (no money).");
                } else {
                    System.out.println("Reason: Not enough credits.");
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static Student createStudent(String type, int goal, int money) {
        type = type.toLowerCase();
        if (type.contains("humanitarian") && type.contains("natural")) {
            return new MixedStudent(goal, money);
        } else if (type.contains("humanitarian")) {
            return new HumanitarianStudent(goal, money);
        } else if (type.contains("natural")) {
            return new NaturalStudent(goal, money);
        }
        return new MixedStudent(goal, money); // Fallback
    }

    private static Visitor createVisitor(String action, String target, int value) {
        action = action.toLowerCase();
        target = target.toLowerCase();

        switch (action) {
            case "teach":
                if (target.contains("humanitarian")) return new HumanitarianTeacher(value);
                if (target.contains("natural")) return new NaturalTeacher(value);
                break;
            case "obtain":
                if (target.contains("scholarship")) return new Accountant(value);
                if (target.contains("help") || target.contains("parent")) return new Parent(value);
                break;
            case "pay":
                if (target.contains("hostel")) return new HostelDirector(value);
                if (target.contains("canteen") || target.contains("food") || target.contains("dining")) return new DiningRoom(value);
                break;
        }
        return null;
    }
}
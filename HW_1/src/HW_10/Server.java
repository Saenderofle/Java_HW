package HW_10;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 12345;
    private static final String FILE_PATH = "F.txt"; // Файл повинен бути в папці проекту

    public static void main(String[] args) {
        List<String> fileLines = new ArrayList<>();

        // 1. Читаємо файл F у пам'ять
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileLines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Помилка читання файлу: " + e.getMessage());
            return;
        }

        // 2. Запуск сервера
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущено. Очікування клієнта...");

            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                System.out.println("Клієнт під'єднався.");
                String word;

                while ((word = in.readLine()) != null) {
                    if (word.equalsIgnoreCase("exit")) break;

                    List<Integer> foundLines = new ArrayList<>();
                    for (int i = 0; i < fileLines.size(); i++) {
                        // Пошук слова в рядку (без врахування регістру)
                        if (fileLines.get(i).toLowerCase().contains(word.toLowerCase())) {
                            foundLines.add(i + 1); // +1 для людського формату рядків
                        }
                    }

                    // Відправляємо результат клієнту (номери через кому або "Not found")
                    if (foundLines.isEmpty()) {
                        out.println("Слово не знайдено");
                    } else {
                        out.println("Рядки: " + foundLines.toString());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
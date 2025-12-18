package HW_10;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Підключено до сервера. Введіть слово для пошуку (або 'exit'):");

            while (true) {
                System.out.print("> ");
                String word = scanner.nextLine();

                out.println(word); // Відправка слова на сервер

                if (word.equalsIgnoreCase("exit")) break;

                String response = in.readLine(); // Отримання відповіді
                System.out.println("Відповідь сервера: " + response);
            }

        } catch (IOException e) {
            System.err.println("Помилка зв'язку: " + e.getMessage());
        }
    }
}
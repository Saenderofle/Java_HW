package HW_11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HW_11_11 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть назву міста (англійською, наприклад kharkiv): ");
        String city = scanner.nextLine().trim().toLowerCase();
        scanner.close();

        String targetUrl = "https://www.timeanddate.com/weather/ukraine/" + city;

        System.out.println("Запит до: " + targetUrl);
        System.out.println("Очікуйте отримання даних...");

        try {

            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // Обов'язково додаємо User-Agent, інакше сайт поверне 403 Forbidden
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                // 4. Виведення поточної дати
                System.out.println("\n--- Прогноз погоди для: " + city.toUpperCase() + " ---");
                System.out.println("Поточна дата: " + LocalDate.now());
                System.out.println("------------------------------------------------");

                // 5. Парсинг температур (Макс / Мін)
                // На сайті формат виглядає як: >10&nbsp;/&nbsp;2&nbsp;°C< або подібне
                // Регулярний вираз шукає: число, роздільник, слеш, роздільник, число, градус
                // (\-?\d+) -> захоплює число (можливо від'ємне) - ГРУПА 1 (Макс)
                // .*?      -> будь-які символи (пробіли, &nbsp;)
                // /        -> символ слеша
                // .*?      -> будь-які символи
                // (\-?\d+) -> захоплює друге число - ГРУПА 2 (Мін)
                // .*?°C    -> закінчується символом градуса Цельсія

                String html = content.toString();

                // Цей патерн шукає температури в таблиці прогнозу
                Pattern pattern = Pattern.compile(">(\\-?\\d+)(?:&nbsp;|\\s)*/(?:&nbsp;|\\s)*(\\-?\\d+)(?:&nbsp;|\\s)*°C<");
                Matcher matcher = pattern.matcher(html);

                int daysFound = 0;

                // прогноз на 2 тижні
                while (matcher.find() && daysFound < 14) {
                    String maxTemp = matcher.group(1);
                    String minTemp = matcher.group(2);

                    daysFound++;
                    System.out.printf("День %2d: Макс: %3s°C | Мін: %3s°C%n", daysFound, maxTemp, minTemp);
                }

                if (daysFound == 0) {
                    System.out.println("Не вдалося знайти дані прогнозу. Можливо, назва міста введена неправильно.");
                } else {
                    System.out.println("------------------------------------------------");
                    System.out.println("Всього значень: " + (daysFound * 2) + " (Макс + Мін)");
                }

            } else {
                System.out.println("Помилка! Код відповіді сайту: " + responseCode);
                if (responseCode == 404) {
                    System.out.println("Місто не знайдено. Перевірте правильність написання англійською.");
                }
            }

        } catch (Exception e) {
            System.out.println("Виникла помилка при з'єднанні:");
            e.printStackTrace();
        }
    }
}
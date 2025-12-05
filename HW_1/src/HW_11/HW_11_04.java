package HW_11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HW_11_04 {
    public static void main(String[] args) {
        String targetUrl = "https://time.is/Kyiv";

        try {
            // 1. Налаштування з'єднання
            URL url = new URL(targetUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // ВАЖЛИВО: Додаємо User-Agent,інакше сайт поверне помилку
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();


                String exactTimeStr = null;

                // Регулярний вираз для пошуку часу у форматі <time id="clock">HH:mm:ss</time>
                Pattern pattern = Pattern.compile("<time id=\"clock\">(\\d{2}:\\d{2}:\\d{2})</time>");

                while ((inputLine = in.readLine()) != null) {
                    Matcher matcher = pattern.matcher(inputLine);
                    if (matcher.find()) {
                        exactTimeStr = matcher.group(1);
                        break;
                    }
                }
                in.close();

                if (exactTimeStr != null) {
                    // 3. Обробка та порівняння часу
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime webTime = LocalTime.parse(exactTimeStr, formatter);


                    LocalTime localTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);

                    System.out.println("--- Результат ---");
                    System.out.println("Час на сайті time.is: " + webTime);
                    System.out.println("Локальний час ПК:     " + localTime);


                    long diff = ChronoUnit.SECONDS.between(localTime, webTime);

                    if (diff == 0) {
                        System.out.println("Статус: Час ідеально синхронізовано.");
                    } else {
                        System.out.println("Статус: Час відрізняється на " + Math.abs(diff) + " сек.");
                        if (Math.abs(diff) > 0 && Math.abs(diff) < 2) {
                            System.out.println("(Це може бути затримка мережі).");
                        }
                    }

                } else {
                    System.out.println("Не вдалося знайти тег <time id=\"clock\"> на сторінці.");
                }

            } else {
                System.out.println("Помилка з'єднання. Код відповіді: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package HW_6;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HW_06_01 {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String todayString = today.format(formatter);
        //    \\d{2}\\.\\d{2}\\.\\d{4}  -> Дві цифри, крапка, дві цифри, крапка, чотири цифри
        //    __\\.__\\.____           -> Два підкреслення, крапка, два, крапка, чотири

        String regex = "\\d{2}\\.\\d{2}\\.\\d{4}|__\\.__\\.____";
        String text = "Сьогоднішня дата: 25.10.2024. Дедлайн був 01.01.2023. " +
                "Будь ласка, впишіть дату народження: __.__.____.";
        // String.replaceAll() знаходить всі збіги з regex і замінює їх
        String result = text.replaceAll(regex, todayString);

        System.out.println("--- Оригінальний текст ---");
        System.out.println(text);
        System.out.println("\n--- Текст після заміни ---");
        System.out.println(result);
    }
}
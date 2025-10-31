package HW_6;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HW_06_02 {
    public static void main(String[] args) {
        // \\+?  Необов'язковий знак '+' на початку
        // \\s* -> Необов'язкові пробіли
        // (\\(\\d+\\)|\\d+)  Перший блок: або цифри в дужках (\\(\\d+\\)), або просто цифри (\\d+)
        //   [\\s\\-]?  Необов'язковий роздільник: пробіл або дефіс
        //   (\\(\\d+\\)|\\d+)  Наступний блок: знову або в дужках, або без
        // )+ Ця група (роздільник + блок) повинна бути 1 або більше разів
        String regex = "\\+?\\s*(\\(\\d+\\)|\\d+)([\\s\\-]?(\\(\\d+\\)|\\d+))+";

        String text = "Мої номери: +380 (44) 123-45-67, 099-123-4567, та робочий 123 45 67. " +
                "Ще є старий (044)1234567.";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        System.out.println("Знайдені номери телефонів у тексті:");

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}

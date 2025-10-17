package HW_5;

public class HW_b_01 {

    public static String removeParenthesesContent(String input) throws IllegalArgumentException {
        if (input == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        int depth = 0;

        for (char c : input.toCharArray()) {
            if (c == '(') {
                depth++;

                if (depth > 1) {
                    throw new IllegalArgumentException("Помилка: Знайдено вкладені дужки.");
                }
            } else if (c == ')') {
                if (depth == 0) {
                    throw new IllegalArgumentException("Помилка: Закриваюча дужка без відповідної відкриваючої.");
                }
                depth--;
            } else {
                if (depth == 0) {
                    sb.append(c);
                }
            }
        }

        if (depth != 0) {
            throw new IllegalArgumentException("Помилка: Не всі відкриті дужки закрито.");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s1 = "Це (просто) текст.";
        String s2 = "Тут (кілька) груп (дужок).";
        String s3 = "(Перша) і (остання) групи.";
        String s4 = "Рядок взагалі без дужок.";

        System.out.println("'" + s1 + "' -> '" + removeParenthesesContent(s1) + "'");
        System.out.println("'" + s2 + "' -> '" + removeParenthesesContent(s2) + "'");
        System.out.println("'" + s3 + "' -> '" + removeParenthesesContent(s3) + "'");
        System.out.println("'" + s4 + "' -> '" + removeParenthesesContent(s4) + "'");


        System.out.println("\n--- Приклади з помилками (викличуть виняток) ---");

        try {
            String sError1 = "Це (текст (з вкладенням))";
            System.out.println("Тест 1 (вкладені): " + sError1);
            removeParenthesesContent(sError1);
        } catch (IllegalArgumentException e) {
            System.out.println("  -> Очікувана помилка: " + e.getMessage());
        }

        try {
            String sError2 = "Це (незакритий текст";
            System.out.println("Тест 2 (незакрито): " + sError2);
            removeParenthesesContent(sError2);
        } catch (IllegalArgumentException e) {
            System.out.println("  -> Очікувана помилка: " + e.getMessage());
        }

        try {
            String sError3 = "Це ) зайва дужка";
            System.out.println("Тест 3 (зайва закриваюча): " + sError3);
            removeParenthesesContent(sError3);
        } catch (IllegalArgumentException e) {
            System.out.println("  -> Очікувана помилка: " + e.getMessage());
        }
    }
}
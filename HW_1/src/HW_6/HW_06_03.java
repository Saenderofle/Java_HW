package HW_6;

import java.util.regex.Pattern;

public class HW_06_03 {

    public static class ExpressionValidator {

        private static final String TERM = "(?:[+-]?\\s*\\d+)";
        private static final String OPERATOR = "(?:\\s*[*/+-]\\s*)";
        private static final String REGEX = "\\s*" + TERM + "(?:" + OPERATOR + TERM + ")*" + "\\s*";

        public static boolean isValidExpression(String expression) {
            if (expression == null) {
                return false;
            }
            return Pattern.matches(REGEX, expression);
        }

        public static void main(String[] args) {
            String[] validExpressions = {
                    "+2 - 57*33 + 25/ - 4",
                    "5",
                    "-10",
                    "+10",
                    "1 + 1",
                    " 100 / 2 * -5 ",
                    "25*2-10+ 4 / 2",
                    "1+2-3*4/5",
                    "  10   +   20  ",
                    "-5*-3+-2/-1"
            };

            String[] invalidExpressions = {
                    "1 +",
                    "+ 1 +",
                    "* 5",
                    "5 5",
                    "1 + / 5",
                    "1 + a",
                    "",
                    "   ",
                    "1 + 2.5",
                    "1 ++ 2",
                    "/ 5",
                    "5 *",
                    "1 2 + 3"
            };

            System.out.println("--- Правильні вирази ---");
            for (String expr : validExpressions) {
                System.out.println("'" + expr + "': " + isValidExpression(expr));
            }

            System.out.println("\n--- Неправильні вирази ---");
            for (String expr : invalidExpressions) {
                System.out.println("'" + expr + "': " + isValidExpression(expr));
            }
        }
    }
}
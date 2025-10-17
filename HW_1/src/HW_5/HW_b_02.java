package HW_5;

public class HW_b_02 {

        public static boolean checkPropertyA(String input) {
            if (input == null || input.isEmpty()) {
                return false;
            }

            char firstChar = input.charAt(0);
            if (!Character.isDigit(firstChar) || firstChar == '0') {
                return false;
            }

            int requiredLetters = Character.getNumericValue(firstChar);

            if (input.length() != requiredLetters + 1) {
                return false;
            }

            for (int i = 1; i < input.length(); i++) {
                if (!Character.isLetter(input.charAt(i))) {
                    return false;
                }
            }

            return true;
        }

        public static boolean checkPropertyB(String input) {
            if (input == null || input.isEmpty()) {
                return false;
            }

            int digitCount = 0;
            int digitValue = -1;
            int totalLength = input.length();

            for (char c : input.toCharArray()) {
                if (Character.isDigit(c)) {
                    digitCount++;
                    digitValue = Character.getNumericValue(c);
                }
            }

            return digitCount == 1 && digitValue == totalLength;
        }

        public static boolean checkPropertyC(String input) {
            if (input == null || input.isEmpty()) {
                return false;
            }

            int digitSum = 0;
            int totalLength = input.length();

            for (char c : input.toCharArray()) {
                if (Character.isDigit(c)) {
                    digitSum += Character.getNumericValue(c);
                }
            }

            return digitSum == totalLength;
        }

        public static void main(String[] args) {
            String[] testStrings = {
                    "5abcde",
                    "7abcdefg",
                    "3ab",
                    "4abc1",
                    "0abcd",
                    "abcde",
                    "abc7def",
                    "abc5def",
                    "ab1c2d",
                    "1",
                    "9",
                    "a1b2c3",
                    "h2k4p",
                    ""
            };

            for (String s : testStrings) {
                System.out.println("Рядок: \"" + s + "\" (довжина " + s.length() + ")");
                System.out.println("  a) " + checkPropertyA(s));
                System.out.println("  b) " + checkPropertyB(s));
                System.out.println("  c) " + checkPropertyC(s));
                System.out.println("---------------------------------");
            }
        }
    }
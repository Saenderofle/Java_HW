package HW_8;

import java.util.ArrayDeque;
import java.util.Deque;


public class B08_02 {

    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : s.toCharArray()) {

            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }
            else if (ch == ')' || ch == ']' || ch == '}') {

                if (stack.isEmpty()) return false;

                char top = stack.pop();

                if (!matches(top, ch)) return false;
            }
        }
        return stack.isEmpty();
    }

    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')')
                || (open == '[' && close == ']')
                || (open == '{' && close == '}');
    }

    public static void main(String[] args) {
        String s1 = "({[]})";
        String s2 = "([)]";
        String s3 = "(({}[]))";
        String s4 = "{[()]}{";

        System.out.println(s1 + " → " + isValid(s1)); // true
        System.out.println(s2 + " → " + isValid(s2)); // false
        System.out.println(s3 + " → " + isValid(s3)); // true
        System.out.println(s4 + " → " + isValid(s4)); // false
    }
}

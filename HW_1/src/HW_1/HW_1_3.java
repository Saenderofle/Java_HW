package HW_1;

public class HW_1_3 {
    public static void main(String[] args) {
        try {
            int product = 1;
            for (String arg : args) {
                product *= Integer.parseInt(arg);
            }
            System.out.println("Добуток аргументів = " + product);
        } catch (NumberFormatException e) {
            System.out.println("Усі аргументи мають бути цілими числами!");
        }
    }
}
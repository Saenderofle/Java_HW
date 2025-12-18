package HW_8;

import java.util.LinkedList;
import java.util.NoSuchElementException;


public class B08_01<T> {
    private final LinkedList<T> stack = new LinkedList<>();

    public void push(T element) {
        stack.addFirst(element);
    }

    public T pop() {
        if (stack.isEmpty()) throw new NoSuchElementException("Стек порожній!");
        return stack.removeFirst();
    }

    public T peek() {
        if (stack.isEmpty()) throw new NoSuchElementException("Стек порожній!");
        return stack.getFirst();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void printRecursive() {
        printRecursiveHelper(0);
    }

    private void printRecursiveHelper(int index) {
        if (index >= stack.size()) return;
        System.out.println(stack.get(index));
        printRecursiveHelper(index + 1);
    }

    public void clearRecursive() {
        if (!stack.isEmpty()) {
            stack.removeFirst();
            clearRecursive();
        }
    }

    @Override
    public String toString() {
        return stack.toString();
    }

    public static void main(String[] args) {
        B08_01<Object> s = new B08_01<>();

        System.out.println("➕ Додаємо елементи у стек:");
        s.push("Hello");
        s.push(42);
        s.push(3.14);
        s.push(true);
        System.out.println("Поточний стек: " + s);

        System.out.println("\n🔍 Рекурсивний вивід:");
        s.printRecursive();

        System.out.println("\n🔝 Верхній елемент: " + s.peek());

        System.out.println("\n🧹 Очищаємо стек рекурсивно:");
        s.clearRecursive();
        System.out.println("Порожній стек? " + s.isEmpty());
    }
}

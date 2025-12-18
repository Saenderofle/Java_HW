package HW_9;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class HW_9 {
    // Параметри моделювання
    static final int N = 3;           // Кількість кухарів
    static final int T1 = 500, T2 = 1500;   // Інтервал приходу клієнтів (мс)
    static final int T3 = 2000, T4 = 5000;  // Час готування страви (мс)
    static final int T5 = 1000;       // Поріг очікування (мс)
    static final int TOTAL_CLIENTS = 10;    // Загальна кількість клієнтів для симуляції

    static Semaphore cooks = new Semaphore(N);
    static AtomicInteger longWaitCount = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        Thread[] clientThreads = new Thread[TOTAL_CLIENTS];

        System.out.println("--- Ресторан відчинено (Кухарів: " + N + ") ---");

        for (int i = 0; i < TOTAL_CLIENTS; i++) {
            // Випадковий інтервал приходу клієнта
            Thread.sleep(random.nextInt(T2 - T1 + 1) + T1);

            int clientId = i + 1;
            clientThreads[i] = new Thread(() -> simulateClient(clientId));
            clientThreads[i].start();
        }

        // Чекаємо завершення всіх потоків
        for (Thread t : clientThreads) {
            t.join();
        }

        System.out.println("\n--- Результати моделювання ---");
        System.out.println("Кількість клієнтів, що чекали більше ніж " + T5 + " мс: " + longWaitCount.get());
    }

    private static void simulateClient(int id) {
        Random random = new Random();
        long arrivalTime = System.currentTimeMillis();

        System.out.println("Клієнт " + id + " прийшов о " + (arrivalTime % 100000));

        try {
            // Клієнт намагається отримати доступ до кухаря
            cooks.acquire();

            long startTimeProcessing = System.currentTimeMillis();
            long waitTime = startTimeProcessing - arrivalTime;

            System.out.println("Клієнт " + id + " почав обслуговуватись. Очікування: " + waitTime + " мс.");

            if (waitTime > T5) {
                longWaitCount.incrementAndGet();
            }

            // Моделювання готування страви
            int cookingTime = random.nextInt(T4 - T3 + 1) + T3;
            Thread.sleep(cookingTime);

            System.out.println("Клієнт " + id + " отримав страву і пішов (готувалось: " + cookingTime + " мс)");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Кухар звільняється
            cooks.release();
        }
    }
}
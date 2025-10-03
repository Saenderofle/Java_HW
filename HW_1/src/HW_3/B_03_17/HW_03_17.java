package HW_3.B_03_17;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;

public class HW_03_17 {
    private String destination;
    private String trainNumber;
    private LocalTime departureTime;
    private int seats;

    // Конструктор
    public HW_03_17(String destination, String trainNumber, LocalTime departureTime, int seats) {
        this.destination = destination;
        this.trainNumber = trainNumber;
        this.departureTime = departureTime;
        this.seats = seats;
    }


    public String getDestination() {
        return destination;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public int getSeats() {
        return seats;
    }

    // Метод для зручного виведення інформації про поїзд
    @Override
    public String toString() {
        return "Поїзд{" +
                "пункт призначення='" + destination + '\'' +
                ", номер поїзда='" + trainNumber + '\'' +
                ", час відправлення=" + departureTime +
                ", кількість місць=" + seats +
                '}';
    }

    // Метод для отримання поїздів за пунктом призначення, відсортованих за часом відправлення
    public static HW_03_17[] getTrainsByDestination(HW_03_17[] trains, String destination) {
        return Arrays.stream(trains)
                .filter(train -> train.getDestination().equalsIgnoreCase(destination))
                .sorted(Comparator.comparing(HW_03_17::getDepartureTime))
                .toArray(HW_03_17[]::new);
    }

    // Метод для отримання поїздів з кількістю місць меншою за задану
    public static HW_03_17[] getTrainsWithLessSeats(HW_03_17[] trains, int maxSeats) {
        return Arrays.stream(trains)
                .filter(train -> train.getSeats() < maxSeats)
                .toArray(HW_03_17[]::new);
    }


    public static void main(String[] args) {
        // Створення масиву
        HW_03_17[] trains = {
                new HW_03_17("Київ", "T001", LocalTime.of(8, 30), 150),
                new HW_03_17("Львів", "T002", LocalTime.of(12, 15), 80),
                new HW_03_17("Київ", "T003", LocalTime.of(6, 45), 200),
                new HW_03_17("Одеса", "T004", LocalTime.of(15, 20), 120),
                new HW_03_17("Київ", "T005", LocalTime.of(10, 0), 90)
        };

        // a) Виведення поїздів до Києва, відсортованих за часом відправлення
        System.out.println("Поїзди до Києва, відсортовані за часом відправлення:");
        HW_03_17[] kyivTrains = getTrainsByDestination(trains, "Київ");
        for (HW_03_17 train : kyivTrains) {
            System.out.println(train);
        }

        // b) Виведення поїздів з кількістю місць меншою за 100
        System.out.println("\nПоїзди з кількістю місць менше 100:");
        HW_03_17[] lessSeatsTrains = getTrainsWithLessSeats(trains, 100);
        for (HW_03_17 train : lessSeatsTrains) {
            System.out.println(train);
        }
    }
}
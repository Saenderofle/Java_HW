package HW_4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Рівні комфортності вагонів. Порядок елементів enum використовується
 * для сортування (від вищого комфорту до нижчого).
 */
enum ComfortLevel {
    LUXURY("Люкс"),
    SV("Спальний (СВ)"),
    COMPARTMENT("Купе"),
    PLATZKART("Плацкарт"),
    SEATING_FIRST_CLASS("Сидячий (1 клас)"),
    SEATING_SECOND_CLASS("Сидячий (2 клас)");

    private final String description;

    ComfortLevel(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}

/**
 * Базовий абстрактний клас для всього рухомого складу.
 */
abstract class RailwayVehicle {
    private final String vehicleId;

    public RailwayVehicle(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    @Override
    public String toString() {
        return "ID: " + vehicleId;
    }
}

class Locomotive extends RailwayVehicle {
    public Locomotive(String vehicleId) {
        super(vehicleId);
    }
    @Override
    public String toString() {
        return "Локомотив | " + super.toString();
    }
}

/**
 * Абстрактний клас для пасажирських вагонів.
 * Реалізує Comparable для сортування за замовчуванням за рівнем комфорту.
 */
abstract class Wagon extends RailwayVehicle implements Comparable<Wagon> {
    private final int passengerCapacity;
    private final int baggageCapacity;
    private final ComfortLevel comfortLevel;

    public Wagon(String vehicleId, int passengerCapacity, int baggagePerPerson, ComfortLevel comfortLevel) {
        super(vehicleId);
        this.passengerCapacity = passengerCapacity;
        this.baggageCapacity = passengerCapacity * baggagePerPerson;
        this.comfortLevel = comfortLevel;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public int getBaggageCapacity() {
        return baggageCapacity;
    }

    public ComfortLevel getComfortLevel() {
        return comfortLevel;
    }

    @Override
    public int compareTo(Wagon other) {
        return this.getComfortLevel().ordinal() - other.getComfortLevel().ordinal();
    }

    @Override
    public String toString() {
        return String.format("Вагон | %-20s | Тип: %-22s | Пасажири: %-3d | Багаж: %-5d кг",
                super.toString(), getComfortLevel(), getPassengerCapacity(), getBaggageCapacity());
    }
}

// Конкретні реалізації вагонів
class LuxuryWagon extends Wagon {
    public LuxuryWagon(String vehicleId) { super(vehicleId, 12, 50, ComfortLevel.LUXURY); }
}
class SVWagon extends Wagon {
    public SVWagon(String vehicleId) { super(vehicleId, 18, 50, ComfortLevel.SV); }
}
class CompartmentWagon extends Wagon {
    public CompartmentWagon(String vehicleId) { super(vehicleId, 36, 36, ComfortLevel.COMPARTMENT); }
}
class PlatzkartWagon extends Wagon {
    public PlatzkartWagon(String vehicleId) { super(vehicleId, 54, 36, ComfortLevel.PLATZKART); }
}
class SeatingFirstClassWagon extends Wagon {
    public SeatingFirstClassWagon(String vehicleId) { super(vehicleId, 64, 36, ComfortLevel.SEATING_FIRST_CLASS); }
}
class SeatingSecondClassWagon extends Wagon {
    public SeatingSecondClassWagon(String vehicleId) { super(vehicleId, 81, 36, ComfortLevel.SEATING_SECOND_CLASS); }
}

/**
 * Клас PassengerTrain інкапсулює логіку керування складом потяга:
 * додавання вагонів, розрахунки, сортування та пошук.
 */
class PassengerTrain {
    private final Locomotive locomotive;
    private final List<Wagon> wagons;
    private final String trainNumber;

    public PassengerTrain(String trainNumber, Locomotive locomotive) {
        this.trainNumber = trainNumber;
        this.locomotive = locomotive;
        this.wagons = new ArrayList<>();
    }

    public void addWagon(Wagon wagon) {
        this.wagons.add(wagon);
    }

    public int calculateTotalPassengerCapacity() {
        return wagons.stream().mapToInt(Wagon::getPassengerCapacity).sum();
    }

    public int calculateTotalBaggageCapacity() {
        return wagons.stream().mapToInt(Wagon::getBaggageCapacity).sum();
    }

    public void sortWagonsByComfort() {
        Collections.sort(wagons);
    }

    public List<Wagon> findWagonsByPassengerRange(int min, int max) {
        return wagons.stream()
                .filter(w -> w.getPassengerCapacity() >= min && w.getPassengerCapacity() <= max)
                .collect(Collectors.toList());
    }

    public void printTrainComposition() {
        System.out.println("Склад потягу №" + trainNumber + ":");
        System.out.println(locomotive);
        wagons.forEach(System.out::println);
        System.out.println("-".repeat(80));
    }
}

/**
 * Демо клас для демонстрації роботи коду.
 * 
 */
class RailwayDemo {
    public static void main(String[] args) {
        Locomotive locomotive = new Locomotive("ЧС4-150");
        PassengerTrain train = new PassengerTrain("49 (Київ - Львів)", locomotive);

        train.addWagon(new PlatzkartWagon("Wagon-006"));
        train.addWagon(new CompartmentWagon("Wagon-003"));
        train.addWagon(new LuxuryWagon("Wagon-001"));
        train.addWagon(new SeatingSecondClassWagon("Wagon-009"));
        train.addWagon(new PlatzkartWagon("Wagon-007"));
        train.addWagon(new SVWagon("Wagon-002"));
        train.addWagon(new CompartmentWagon("Wagon-004"));
        train.addWagon(new SeatingFirstClassWagon("Wagon-008"));
        train.addWagon(new CompartmentWagon("Wagon-005"));

        System.out.println("## 1. Початковий склад потягу:");
        train.printTrainComposition();

        int totalPassengers = train.calculateTotalPassengerCapacity();
        int totalBaggage = train.calculateTotalBaggageCapacity();
        System.out.println("## 2. Загальні показники потягу:");
        System.out.printf("Загальна кількість пасажирів: %d%n", totalPassengers);
        System.out.printf("Загальна місткість багажу: %d кг%n", totalBaggage);
        System.out.println("-".repeat(80));

        train.sortWagonsByComfort();
        System.out.println("## 3. Склад потягу після сортування за комфортом:");
        train.printTrainComposition();

        int minPassengers = 30;
        int maxPassengers = 60;
        List<Wagon> foundWagons = train.findWagonsByPassengerRange(minPassengers, maxPassengers);

        System.out.printf("## 4. Пошук вагонів з кількістю пасажирів від %d до %d:%n", minPassengers, maxPassengers);
        foundWagons.forEach(System.out::println);
        System.out.println("-".repeat(80));
    }
}
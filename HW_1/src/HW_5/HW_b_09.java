package HW_5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HW_b_09 {

    record Country(String name, double area, String continent) {}

    public static void main(String[] args) {
        String inputFile = "countries.txt";
        String outputFileA = "small_countries.txt";
        String outputFileB = "continent_counts.txt";

        try {
            createSampleFile(inputFile);

            List<Country> countries = readCountriesFromFile(inputFile);
            System.out.println("Файл " + inputFile + " успішно зчитано. Знайдено країн: " + countries.size());

            double maxArea = 500000;
            findCountriesByArea(countries, maxArea, outputFileA);
            System.out.println("Завдання (a): Результат збережено у " + outputFileA);

            countCountriesByContinent(countries, outputFileB);
            System.out.println("Завдання (b): Результат збережено у " + outputFileB);

        } catch (IOException e) {
            System.err.println("Сталася помилка під час роботи з файлами: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void findCountriesByArea(List<Country> countries, double maxArea, String outputFilename) throws IOException {

        List<String> smallCountries = countries.stream()
                .filter(country -> country.area() <= maxArea)
                .map(Country::toString)
                .collect(Collectors.toList());

        writeLinesToFile(outputFilename, smallCountries);
    }

    public static void countCountriesByContinent(List<Country> countries, String outputFilename) throws IOException {

        Map<String, Long> continentCounts = countries.stream()
                .collect(Collectors.groupingBy(
                        Country::continent,
                        Collectors.counting()
                ));

        List<String> results = continentCounts.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .sorted()
                .collect(Collectors.toList());

        writeLinesToFile(outputFilename, results);
    }

    private static List<Country> readCountriesFromFile(String filename) throws IOException {
        List<Country> countries = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length == 3) {
                    try {
                        String name = parts[0].trim();
                        double area = Double.parseDouble(parts[1].trim());
                        String continent = parts[2].trim();
                        countries.add(new Country(name, area, continent));
                    } catch (NumberFormatException e) {
                        System.err.println("Помилка формату числа (пропущено): " + line);
                    }
                } else {
                    System.err.println("Неправильний формат рядка (пропущено): " + line);
                }
            }
        }
        return countries;
    }

    private static void writeLinesToFile(String filename, List<String> lines) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    private static void createSampleFile(String filename) throws IOException {
        List<String> lines = List.of(
                "# Назва,Площа,Континент",
                "Україна,603628,Європа",
                "Польща,312696,Європа",
                "Німеччина,357022,Європа",
                "Ватикан,0.44,Європа",
                "Китай,9596961,Азія",
                "Індія,3287263,Азія",
                "Японія,377975,Азія",
                "США,9833520,Північна Америка",
                "Канада,9984670,Північна Америка",
                "Мексика,1964375,Північна Америка",
                "Бразилія,8515767,Південна Америка",
                "Аргентина,2780400,Південна Америка",
                "Єгипет,1010408,Африка",
                "Нігерія,923768,Африка",
                "Австралія,7692024,Австралія"
        );
        writeLinesToFile(filename, lines);
    }
}
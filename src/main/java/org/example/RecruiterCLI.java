package org.example;

import java.util.List;
import java.util.Scanner;

public class RecruiterCLI {

    private final CandidateService service;
    private final Scanner scanner = new Scanner(System.in);

    public RecruiterCLI(CandidateService service) {
        this.service = service;
    }

    public void start() {
        while (true) {
            System.out.println("\n--- Rekryteringssystem ---");
            System.out.println("1. Lägg till kandidat");
            System.out.println("2. Ta bort kandidat");
            System.out.println("3. Visa alla kandidater");
            System.out.println("4. Filtrera kandidater");
            System.out.println("5. Sortera kandidater (förnamn A-Ö)");
            System.out.println("6. Visa kort sammanfattning");
            System.out.println("0. Avsluta");
            System.out.print("Välj ett alternativ: ");

            int choice = readIntSafe();
            switch (choice) {
                case 1 -> addCandidate();
                case 2 -> removeCandidate();
                case 3 -> showAll();
                case 4 -> filterMenu();
                case 5 -> sortCandidates();
                case 6 -> showSummaries();
                case 0 -> {
                    System.out.println("Avslutar programmet...");
                    return;
                }
                default -> System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }

    private int readIntSafe() {
        while (true) {
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.print("Skriv ett heltal tack: ");
            }
        }
    }

    private void addCandidate() {
        System.out.print("Förnamn: ");
        String first = scanner.nextLine();

        System.out.print("Efternamn: ");
        String last = scanner.nextLine();

        System.out.print("Ålder: ");
        int age = readIntSafe();

        System.out.print("Bransch: ");
        String industry = scanner.nextLine();

        System.out.print("Antal år erfarenhet: ");
        int years = readIntSafe();

        Candidate candidate = new Candidate(first, last, age, industry, years);
        service.addCandidate(candidate);
    }

    private void removeCandidate() {
        System.out.print("Ange förnamnet på kandidaten som ska tas bort: ");
        String name = scanner.nextLine();
        service.removeCandidate(name);
    }

    private void showAll() {
        List<Candidate> all = service.getAll();
        if (all.isEmpty()) {
            System.out.println("Inga kandidater finns ännu.");
        } else {
            all.forEach(System.out::println);
        }
    }

    private void filterMenu() {
        System.out.println("1. Filtrera efter bransch");
        System.out.println("2. Filtrera efter minsta antal år erfarenhet");
        int val = readIntSafe();

        switch (val) {
            case 1 -> {
                System.out.print("Ange bransch: ");
                String industry = scanner.nextLine();
                List<Candidate> result =
                        service.applyFilter(new FilterByIndustry(industry));
                result.forEach(System.out::println);
            }
            case 2 -> {
                System.out.print("Ange minsta antal år erfarenhet: ");
                int years = readIntSafe();
                List<Candidate> result =
                        service.applyFilter(new FilterByMinExperience(years));
                result.forEach(System.out::println);
            }
            default -> System.out.println("Ogiltigt val.");
        }
    }

    private void sortCandidates() {
        List<Candidate> sorted = service.applyFilter(new SortByFirstName());
        sorted.forEach(System.out::println);
    }

    private void showSummaries() {
        List<String> summaries = service.getSummaries();
        summaries.forEach(System.out::println);
    }
}

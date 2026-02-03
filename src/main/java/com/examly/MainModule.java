package com.examly;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.examly.entity.Pet;
import com.examly.service.PetService;
import com.examly.service.PetServiceImpl;
import com.examly.util.DBConnectionUtil;

public class MainModule {

    public static void main(String[] args) {
        PetService petService = new PetServiceImpl();

        // Startup connectivity check (matches sample output style)
        try (Connection connection = DBConnectionUtil.getConnection()) {
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            System.out.println("Reason: " + e.getMessage());
            System.out.println("Please verify MySQL is running and the DB/table exist.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("Enter your choice: ");
            String choiceInput = scanner.nextLine().trim();

            int choice;
            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addPet(scanner, petService);
                    break;
                case 2:
                    updatePet(scanner, petService);
                    break;
                case 3:
                    deletePet(scanner, petService);
                    break;
                case 4:
                    viewAllPets(petService);
                    break;
                case 5:
                    searchByName(scanner, petService);
                    break;
                case 6:
                    filterByType(scanner, petService);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("Pet Adoption Management System");
        System.out.println("1. Add Pet");
        System.out.println("2. Update Pet");
        System.out.println("3. Delete Pet");
        System.out.println("4. View All Pets");
        System.out.println("5. Search Pet by Name");
        System.out.println("6. Filter Pets by Type");
        System.out.println("7. Exit");
        System.out.println();
    }

    private static void addPet(Scanner scanner, PetService petService) {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Type (Dog/Cat/etc): ");
        String type = scanner.nextLine();

        System.out.print("Breed: ");
        String breed = scanner.nextLine();

        int age = readInt(scanner, "Age: ");

        boolean adopted = readBoolean(scanner, "Adopted (true/false): ");

        Pet pet = new Pet(name, type, breed, age, adopted);
        String message = petService.addPet(pet);
        System.out.println(message);
    }

    private static void updatePet(Scanner scanner, PetService petService) {
        int petid = readInt(scanner, "Enter Pet ID to update: ");

        Pet existing = petService.getPetById(petid);
        if (existing == null) {
            System.out.println("Pet not found.");
            return;
        }

        System.out.println("New name (enter to skip): ");
        String name = scanner.nextLine();

        System.out.println("New type (enter to skip): ");
        String type = scanner.nextLine();

        System.out.println("New breed (enter to skip): ");
        String breed = scanner.nextLine();

        System.out.print("New age (enter to skip): ");
        String ageInput = scanner.nextLine().trim();

        System.out.println("New adopted status (true/false): ");
        String adoptedInput = scanner.nextLine().trim();

        if (!name.trim().isEmpty()) {
            existing.setName(name);
        }
        if (!type.trim().isEmpty()) {
            existing.setType(type);
        }
        if (!breed.trim().isEmpty()) {
            existing.setBreed(breed);
        }
        if (!ageInput.isEmpty()) {
            try {
                int newAge = Integer.parseInt(ageInput);
                existing.setAge(newAge);
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Keeping existing age.");
            }
        }
        if (!adoptedInput.isEmpty()) {
            Boolean adopted = parseBooleanStrict(adoptedInput);
            if (adopted == null) {
                System.out.println("Invalid adopted status. Keeping existing status.");
            } else {
                existing.setAdopted(adopted);
            }
        }

        String message = petService.updatePet(existing);
        System.out.println(message);
    }

    private static void deletePet(Scanner scanner, PetService petService) {
        int petid = readInt(scanner, "Enter Pet ID to delete: ");
        String message = petService.deletePet(petid);
        System.out.println(message);
    }

    private static void viewAllPets(PetService petService) {
        List<Pet> pets = petService.getAllPets();
        if (pets.isEmpty()) {
            System.out.println("No pets available.");
            return;
        }

        for (Pet pet : pets) {
            System.out.println(pet);
            System.out.println();
        }
    }

    private static void searchByName(Scanner scanner, PetService petService) {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();

        List<Pet> pets = petService.searchByName(name);
        if (pets.isEmpty()) {
            System.out.println("No pets found with that name.");
            return;
        }

        for (Pet pet : pets) {
            System.out.println(pet);
            System.out.println();
        }
    }

    private static void filterByType(Scanner scanner, PetService petService) {
        System.out.print("Enter pet type to filter (Dog, Cat, etc): ");
        String type = scanner.nextLine();

        List<Pet> pets = petService.filterByType(type);
        if (pets.isEmpty()) {
            System.out.println("No pets of type: " + type);
            return;
        }

        for (Pet pet : pets) {
            System.out.println(pet);
            System.out.println();
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static boolean readBoolean(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            Boolean value = parseBooleanStrict(input);
            if (value != null) {
                return value;
            }
            System.out.println("Please enter true or false.");
        }
    }

    private static Boolean parseBooleanStrict(String input) {
        if (input == null) {
            return null;
        }
        String normalized = input.trim().toLowerCase();
        if ("true".equals(normalized)) {
            return true;
        }
        if ("false".equals(normalized)) {
            return false;
        }
        return null;
    }
}

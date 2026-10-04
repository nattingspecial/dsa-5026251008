import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        solveProblem1();
        solveProblem2();
        solveProblem3();
    }

    // Problem 1: List implementation for Playlist Management
    private static void solveProblem1() {
        List<String> playlist = new ArrayList<>();
        File file = new File("src/lw03/prelab/playlists.txt");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(" ", 2);
                String command = parts[0];

                if (command.equals("ADD")) {
                    playlist.add(parts[1]);
                } else if (command.equals("INSERT")) {
                    String[] insertArgs = parts[1].split(" ", 2);
                    int index = Integer.parseInt(insertArgs[0]);
                    String song = insertArgs[1];
                    playlist.add(index, song);
                } else if (command.equals("REMOVE")) {
                    String song = parts[1];
                    playlist.remove(song);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File playlists.txt tidak ditemukan.");
            return;
        }

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    // Problem 2: Set implementation for Workshop Participants
    private static void solveProblem2() {
        Set<String> uniqueParticipants = new LinkedHashSet<>();
        int duplicateCount = 0;
        File file = new File("src/lw03/prelab/participants.txt");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String name = scanner.nextLine().trim();
                if (name.isEmpty()) continue;

                if (!uniqueParticipants.add(name)) {
                    duplicateCount++;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File participants.txt tidak ditemukan.");
            return;
        }

        System.out.println("\n===== Problem 2 =====");
        System.out.println("Unique participants: " + uniqueParticipants.size());
        int rank = 1;
        for (String name : uniqueParticipants) {
            System.out.println(rank + ". " + name);
            rank++;
        }
        System.out.println("Duplicate registrations: " + duplicateCount);
    }

    // Problem 3: Map implementation for Product Inventory
    private static void solveProblem3() {
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;
        File file = new File("src/lw03/prelab/inventory.txt");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(" ");
                String type = parts[0];
                String product = parts[1];
                int quantity = Integer.parseInt(parts[2]);

                if (type.equals("ADD")) {
                    inventory.put(product, inventory.getOrDefault(product, 0) + quantity);
                } else if (type.equals("SELL")) {
                    if (inventory.containsKey(product) && inventory.get(product) >= quantity) {
                        inventory.put(product, inventory.get(product) - quantity);
                    } else {
                        failedSales++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File inventory.txt tidak ditemukan.");
            return;
        }

        System.out.println("\n===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }
}
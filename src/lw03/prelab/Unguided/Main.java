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
       bacaRegis();
       cekRegis();
       Summarize();
    }

// Expected output for the program:
//     ===== Event Check-In Results =====
// S103: Checked in
// S101: Checked in
// S103: Rejected (already checked in)
// S888: Rejected (not registered)
// S102: Checked in
// S105: Checked in
// S101: Rejected (already checked in)
// S777: Rejected (not registered)
// ===== Final Event Summary =====
// Registered students: 5
// Successful check-ins: 4
// Absent students: 1
// Rejected attempts: 4

    private static void bacaRegis() {
        Set<String> registeredStudents = new LinkedHashSet<>();
        File file = new File("src/lw03/prelab/registered.txt");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    registeredStudents.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File registered.txt tidak ditemukan.");
            return;
        }

        System.out.println("===== Event Check-In Results =====");
        for (String studentId : registeredStudents) {
            System.out.println(studentId + ": Registered");
        }
        
    }
}

    private static void cekRegis() {
        Set<String> registeredStudents = new LinkedHashSet<>();
        Set<String> checkedInStudents = new LinkedHashSet<>();
        int rejectedAttempts = 0;
        File file = new File("src/lw03/prelab/registered.txt");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    registeredStudents.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File registered.txt tidak ditemukan.");
            return;
        }

        File checkInFile = new File("src/lw03/prelab/checkin.txt");
        try (Scanner scanner = new Scanner(checkInFile)) {
            while (scanner.hasNextLine()) {
                String studentId = scanner.nextLine().trim();
                if (!studentId.isEmpty()) {
                    if (registeredStudents.contains(studentId)) {
                        if (checkedInStudents.contains(studentId)) {
                            System.out.println(studentId + ": Rejected (already checked in)");
                            rejectedAttempts++;
                        } else {
                            System.out.println(studentId + ": Checked in");
                            checkedInStudents.add(studentId);
                        }
                    } else {
                        System.out.println(studentId + ": Rejected (not registered)");
                        rejectedAttempts++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File checkin.txt tidak ditemukan.");
            return;
        }

        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registeredStudents.size());
        System.out.println("Successful check-ins: " + checkedInStudents.size());
        System.out.println("Absent students: " + (registeredStudents.size() - checkedInStudents.size()));
        System.out.println("Rejected attempts: " + rejectedAttempts);
    }
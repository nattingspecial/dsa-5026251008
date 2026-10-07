
public class Main {
    private static final java.util.Set<String> registeredStudents = new java.util.HashSet<String>();
    private static final java.util.Set<String> checkedInStudents =  new java.util.HashSet<String>();
    private static int rejectedAttempts;

    public static void main(String[] args) {
        bacaRegis();    
        System.out.println("===== Event Check-In Results =====");
        cekRegis();
        Summarize();
    }

    private static void bacaRegis() {
        try {
            java.util.Scanner scanner =
                    new java.util.Scanner(new java.io.File("registrations.txt"));
            while (scanner.hasNextLine()) {
                String studentId = scanner.nextLine().trim();
                if (!studentId.isEmpty()) {
                    registeredStudents.add(studentId);
                }
            }
            scanner.close();
        } catch (Exception exception) {
            System.err.println("Could not read registrations.txt: " + exception.getMessage());
        }
    }

    private static void cekRegis() {
        try {
            java.util.Scanner scanner =
                    new java.util.Scanner(new java.io.File("checkins.txt"));
            while (scanner.hasNextLine()) {
                String studentId = scanner.nextLine().trim();
                if (studentId.isEmpty()) {
                    continue;
                }

                if (!registeredStudents.contains(studentId)) {
                    rejectedAttempts++;
                    System.out.println(studentId + ": Rejected (not registered)");
                } else if (checkedInStudents.contains(studentId)) {
                    rejectedAttempts++;
                    System.out.println(studentId + ": Rejected (already checked in)");
                } else {
                    checkedInStudents.add(studentId);
                    System.out.println(studentId + ": Checked in");
                }
            }
            scanner.close();
        } catch (Exception exception) {
            System.err.println("Could not read checkins.txt: " + exception.getMessage());
        }

    }

    private static void Summarize() {
        int registeredCount = registeredStudents.size();
        int successfulCheckIns = checkedInStudents.size();
        int absentStudents = registeredCount - successfulCheckIns;

        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registeredCount);
        System.out.println("Successful check-ins: " + successfulCheckIns);
        System.out.println("Absent students: " + absentStudents);
        System.out.println("Rejected attempts: " + rejectedAttempts);
    }
}



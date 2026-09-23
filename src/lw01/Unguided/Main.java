package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<WashService> jobs = new ArrayList<>();
        List<Integer> units = new ArrayList<>();
        
        try {
            File file = new File("src/lw01/Unguided/Washes.txt");
            Scanner scanner = new Scanner(file);
            int recordCount = scanner.nextInt();
            
            for (int record = 0; record < recordCount; record++) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();
                int unitCount = scanner.nextInt();

                if (type.equalsIgnoreCase("CAR")) {
                    jobs.add(new CarWash(id, days));
                    units.add(unitCount);
                } else if (type.equalsIgnoreCase("MOTORCYCLE")) {
                    jobs.add(new MotorcycleWash(id, days));
                    units.add(unitCount);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File Washes.txt tidak ditemukan.");
            return;
        }

        
        for (int index = 0; index < jobs.size(); index++) {
            WashService job = jobs.get(index);
            System.out.println(job.getId() + " | " + job.label() + " | "
                    + job.calculateCharge(units.get(index)));
        }
    }
}
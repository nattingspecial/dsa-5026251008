import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SortBotWarehouse {
    public static void main(String[] args) {
        try {
            Scanner fileScanner = new Scanner(new File("parcels.txt"));
            
            if (!fileScanner.hasNextInt()) {
                fileScanner.close();
                return;
            }
            int n = fileScanner.nextInt();
            
            int[] weights = new int[n];
            for (int i = 0; i < n; i++) {
                weights[i] = fileScanner.nextInt();
            }
            
            int m = fileScanner.nextInt();
            int[] targets = new int[m];
            for (int i = 0; i < m; i++) {
                targets[i] = fileScanner.nextInt();
            }
            fileScanner.close();
            
            int[] shelfAfter2 = weights.clone();
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < n - 1 - i; j++) {
                    if (shelfAfter2[j] < shelfAfter2[j+1]) {
                        int temp = shelfAfter2[j];
                        shelfAfter2[j] = shelfAfter2[j+1];
                        shelfAfter2[j+1] = temp;
                    }
                }
            }
            
            int[] fullySorted = shelfAfter2.clone();
            for (int i = 2; i < n - 1; i++) {
                for (int j = 0; j < n - 1 - i; j++) {
                    if (fullySorted[j] < fullySorted[j+1]) {
                        int temp = fullySorted[j];
                        fullySorted[j] = fullySorted[j+1];
                        fullySorted[j+1] = temp;
                    }
                }
            }
            
            System.out.println("==== After 2 Passes ====");
            for (int i = 0; i < n; i++) {
                System.out.print(shelfAfter2[i] + (i == n - 1 ? "" : " "));
            }
            System.out.println();
            
            System.out.println("==== Fully Sorted ====");
            for (int i = 0; i < n; i++) {
                System.out.print(fullySorted[i] + (i == n - 1 ? "" : " "));
            }
            System.out.println();
            

            System.out.println("==== Search Results ====");
            for (int target : targets) {
                int low = 0;
                int high = n - 1;
                int steps = 0;
                int foundIndex = -1;
                
                while (low <= high) {
                    steps++;
                    int mid = (low + high) / 2;
                    if (fullySorted[mid] == target) {
                        foundIndex = mid;
                        break;
                    } else if (fullySorted[mid] < target) {

                        high = mid - 1;
                    } else {
                        
                        low = mid + 1;
                    }
                }
                
                if (foundIndex != -1) {
                    System.out.println(target + ": FOUND at index " + foundIndex + " (steps: " + steps + ")");
                } else {
                    System.out.println(target + ": NOT FOUND (steps: " + steps + ")");
                }
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("File parcels.txt not found.");
        }
    }
}
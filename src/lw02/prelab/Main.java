// import java.io.File;
// import java.io.FileNotFoundException;
// import java.util.LinkedList;
// import java.util.Queue;
// import java.util.Scanner;
// import java.util.Stack;

// public class Main {
//     public static void main(String[] args) {
//         // 1. LinkedList untuk menyimpan semua transaksi dari file
//         LinkedList<String[]> transactions = new LinkedList<>();
        
//         // 2. LinkedList untuk menyimpan data nasabah [nama, saldo]
//         LinkedList<String[]> customers = new LinkedList<>();

//         // Membaca data dari file transactions.txt
//         try {
//             File file = new File("transactions.txt");
//             Scanner scanner = new Scanner(file);

//             while (scanner.hasNextLine()) {
//                 String line = scanner.nextLine().trim();
//                 if (line.isEmpty()) continue;

//                 String[] parts = line.split("\\s+");
//                 String name = parts[0];
//                 String type = parts[1];
//                 String amount = parts[2];

//                 // Simpan transaksi
//                 transactions.add(new String[]{name, type, amount});

//                 // Cek apakah nasabah sudah terdaftar
//                 boolean exists = false;
//                 for (String[] c : customers) {
//                     if (c[0].equals(name)) {
//                         exists = true;
//                         break;
//                     }
//                 }

//                 // Tambahkan nasabah baru jika belum ada (saldo awal 0)
//                 if (!exists) {
//                     customers.add(new String[]{name, "0"});
//                 }
//             }
//             scanner.close();
//         } catch (FileNotFoundException e) {
//             System.out.println("File transactions.txt tidak ditemukan!");
//             return;
//         }

//         // 3. Pindahkan transaksi dari LinkedList ke Queue (FIFO)
//         Queue<String[]> transactionQueue = new LinkedList<>();
//         for (String[] t : transactions) {
//             transactionQueue.add(t);
//         }

//         // 4. Stack untuk menyimpan transaksi WITHDRAW yang gagal (LIFO)
//         Stack<String[]> failedTransactions = new Stack<>();

//         // Proses transaksi dalam Queue
//         while (!transactionQueue.isEmpty()) {
//             String[] t = transactionQueue.poll();
//             String name = t[0];
//             String type = t[1];
//             int amount = Integer.parseInt(t[2]);

//             // Cari data nasabah yang bersangkutan
//             String[] customer = null;
//             for (String[] c : customers) {
//                 if (c[0].equals(name)) {
//                     customer = c;
//                     break;
//                 }
//             }

//             if (customer != null) {
//                 int currentBalance = Integer.parseInt(customer[1]);

//                 if (type.equals("DEPOSIT")) {
//                     currentBalance += amount;
//                     customer[1] = String.valueOf(currentBalance);
//                 } else if (type.equals("WITHDRAW")) {
//                     if (amount > currentBalance) {
//                         // Transaksi gagal, simpan ke Stack
//                         failedTransactions.push(t);
//                     } else {
//                         currentBalance -= amount;
//                         customer[1] = String.valueOf(currentBalance);
//                     }
//                 }
//             }
//         }

//         // 5. Cetak Output
//         System.out.println("=== Final Balances ===");
//         for (String[] c : customers) {
//             System.out.println(c[0] + ": " + c[1]);
//         }

//         System.out.println("=== Failed Transactions ===");
//         while (!failedTransactions.isEmpty()) {
//             String[] ft = failedTransactions.pop();
//             System.out.println(ft[0] + " " + ft[1] + " " + ft[2]);
//         }
//     }
// }
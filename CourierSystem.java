import java.util.Scanner;

public class CourierSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Courier[] couriers = new Courier[5];
        int count = 0;

        while (true) {
            try {
                System.out.print("\nEnter Courier ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Sender Name: ");
                String sender = sc.nextLine();

                System.out.print("Enter Receiver Name: ");
                String receiver = sc.nextLine();

                System.out.print("Enter Weight (kg): ");
                double weight = sc.nextDouble();
                sc.nextLine();

                System.out.print("Enter Transport Mode (TRAIN/CAR/FLIGHT/SHIP): ");
                String mode = sc.nextLine();

                couriers[count] = new Courier(id, sender, receiver, weight, mode);
                count++;

            } catch (InvalidTransportException | InvalidWeightException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Courier storage is full! Cannot add more.");
                break;
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e);
            }

            System.out.print("Do you want to add another courier? (yes/no): ");
            String choice = sc.nextLine();
            if (!choice.equalsIgnoreCase("yes")) break;
        }

        // Update statuses
        for (int i = 0; i < count; i++) {
            try {
                System.out.print("Update status for Courier ID " + (i + 1) + " (BOOKED/IN_TRANSIT/DELIVERED): ");
                String newStatus = sc.nextLine();
                couriers[i].updateStatus(newStatus);
            } catch (InvalidStatusException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("\n--- Courier Details ---");
        for (int i = 0; i < count; i++) {
            couriers[i].displayDetails();
        }
        sc.close();
    }
}

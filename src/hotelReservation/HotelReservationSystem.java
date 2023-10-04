package hotelReservation;
import java.util.Scanner;

public class HotelReservationSystem {

    static Scanner sc= new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Welcome to Hotel Reservation System:\n");
        System.out.println("***********************************");
        ChooseOptions();
    }

    public static void ChooseOptions() {

        while (true) {
            displayMainMenu();
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    CreateAccount newAccount = new CreateAccount();      // Object Creation
                    newAccount.createAccount();                         //Calling the method using object
                    break;

                case 2:
                    System.out.println("start Reservation");
                    StartReservation sr = new StartReservation();
                    sr.StartUserReservation();
                    break;

                case 3:
                    System.out.println("cancel Reservation");
                    break;

                case 4:
                    System.out.println("display All Reservations");
                    break;

                case 5:
                    System.out.println("print Total Bill");
                    break;

                case 6:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    public static void displayMainMenu() {

        System.out.println("Choose an option:");
        System.out.println("1. Create an account");
        System.out.println("2. Start your reservation");
        System.out.println("3. Cancel your reservation");
        System.out.println("4. Display all my reservations");
        System.out.println("5. Print my total bill");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }






}


package hotelReservation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class HotelReservationSystem {


    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Welcome to Hotel Reservation System:\n");


        System.out.println("Choose an option:");
        System.out.println("1. Create an account");
        System.out.println("2. Start your reservation");
        System.out.println("3. Cancel your reservation");
        System.out.println("4. Display all my reservations");
        System.out.println("5. Print my total bill");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");

        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                createAccount();
                break;

            case 2:
                System.out.println("start Reservation");
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
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void createAccount() {
        createUserAccountFolder();
        System.out.println("Your account has been created successfully.");


    }

    private static void createUserAccountFolder() {

        String userEmail;
        while (true) {
            System.out.print("Enter your email: ");
            userEmail = sc.nextLine();

            if (isValidEmail(userEmail)) {
                break; // Exit the loop if the email is valid
            } else {
                System.out.println("Invalid email format. Please enter a valid email.");
            }
        }
        int phoneNumber;
        while (true) {
            try {
                System.out.print("Enter your phone number ( 10 digits) : ");
                phoneNumber = sc.nextInt();
                sc.nextLine();

                if (isValidPhoneNumber(phoneNumber)) {
                    break;
                } else {
                    System.out.println("Invalid phone number. Please enter a 10-digit number.");
                }
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Invalid input. Please enter a valid phone number.");
            }
        }
        System.out.print("Enter your location: " +"\n");
        String location = sc.nextLine();
        long randomNum = (long) (Math.random()*Math.pow(10,10));
        String folderName = Long.toString(randomNum);
        File folder = new File(folderName);

        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Folder created: " + folder.getAbsolutePath()+"\n");
                createAndWriteFile(folderName, userEmail, phoneNumber, location);
            } else {
                System.out.println("Failed to create folder.");
            }
        } else {
            System.out.println("Folder with the same name already exists.");
        }
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(int phoneNumber) {
        String phoneNumberString = Integer.toString(phoneNumber);
        return phoneNumberString.length() == 10;
    }

    public static void createAndWriteFile(String folderName, String userEmail, int phoneNumber, String location) {
        String accountNumber = "acc_" + folderName;
        File file = new File(folderName + File.separator + accountNumber + ".txt");

        try (FileWriter writer = new FileWriter(file)) {


            // Write user data to the file
            writer.write("User Name: " + userEmail + "\n");
            writer.write("Email: " + phoneNumber + "\n");
            writer.write("Phone Number: " + location + "\n");

            System.out.println("User data saved to file: " + file.getAbsolutePath()+"\n");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

    }

}

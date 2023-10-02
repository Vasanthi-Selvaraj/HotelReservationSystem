package hotelReservation;

import com.google.gson.Gson;
import model.User;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccount {

    public void createAccount() {
        createUserAccountDirectory();
        System.out.println("Your account has been created successfully.");


    }

    public void createUserAccountDirectory() {
        Scanner sc= new Scanner(System.in);
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
        File file = new File(folderName + File.separator + accountNumber + ".json");

        User user = new User(userEmail, phoneNumber, location);

        try (FileWriter writer = new FileWriter(file)) {

            Gson gson = new Gson();

            String json = gson.toJson(user);
            // Write user data to the file

            writer.write(json);

            System.out.println("User data saved to sfile: " + file.getAbsolutePath()+"\n");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

    }

}

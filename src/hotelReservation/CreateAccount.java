package hotelReservation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.User;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
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
        List<Long> reservation= new ArrayList<>();
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
        sc.close();
        long randomNum = (long) (Math.random()*Math.pow(10,10));
        String folderName = Long.toString(randomNum);
        File folder = new File(folderName);

        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Folder created: " + folder.getAbsolutePath()+"\n");
                createAndWriteFile(folderName, userEmail, phoneNumber, location, reservation);
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



    public static void createAndWriteFile(String folderName, String userEmail, int phoneNumber, String location,
                                          List<Long> reservation) {
        String accountNumber = "acc_" + folderName;
        File file = new File(folderName + File.separator + accountNumber + ".json");

        User user = new User(userEmail, phoneNumber, location, reservation);
        // Read existing user data from the file if it exists
        User existingUser = readUserFromFile(file);

        // Initialize reservation list with existing reservations or create a new list
        List<Long> updatedReservation = (existingUser != null) ? existingUser.getReservation() : new ArrayList<>();

        // Add the new reservation file name to the list
        updatedReservation.add(System.currentTimeMillis()); // You can use a unique identifier here

        // Update the user object with the new reservation list
        //User user = new User(userEmail, phoneNumber, location, updatedReservation);


        try (FileWriter writer = new FileWriter(file)) {

            Gson gson = new GsonBuilder()
                    .serializeNulls() // Include fields with null values
                    .create();

            String json = gson.toJson(user);
            // Write user data to the file

            writer.write(json);

            System.out.println("User data saved to file: " + file.getAbsolutePath()+"\n");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

    }

    private static User readUserFromFile(File file) {

        try (FileReader reader = new FileReader(file)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, User.class);
        } catch (IOException e) {
            // Handle the exception or return null if the file doesn't exist
            return null;
        }
    }
    }


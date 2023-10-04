package hotelReservation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Reservation;
import model.User;

import java.io.*;
import java.util.Scanner;

import static hotelReservation.HotelReservationSystem.ChooseOptions;

    public class StartReservation {

        static Scanner sc = new Scanner(System.in);
        String userInput;
        long accountNumber;

        public void StartUserReservation() {

            System.out.println("Do you have an account? (yes/no)");

            userInput = getUserInput();

            CreateUserReservation();

        }


        private String getUserInput() {
            return sc.nextLine();
        }

        private int getUserIntInput(){
            return sc.nextInt();
        }

        private long generateRandomReservationNumber() {


            long reservationNumber = (long) (Math.random()*Math.pow(10,10));
            return reservationNumber;
        }

        private void CreateUserReservation() {

            if (userInput != null && userInput.equalsIgnoreCase("yes")) {


                    System.out.println("Please enter your account number:");
                    accountNumber = sc.nextLong();

                boolean AccountExists = AccountChecker(accountNumber);

                if (AccountExists) {
                    System.out.println("Choose your preference:");
                    System.out.println("1. Hotel");
                    System.out.println("2. Villa");
                    System.out.println("3. Apartment");
                    int preference = getUserIntInput();

                    // Gather reservation details
                    System.out.println("How many members?");
                    int numberOfMembers = getUserIntInput();

                    System.out.println("How many days?");
                    int numberOfDays = getUserIntInput();
                    sc.nextLine();

                    System.out.println("Any preference for food (veg/non-veg)?");
                    String foodPreference = getUserInput();

                    System.out.println("Any special arrangements?");
                    String specialArrangements = getUserInput();


                    writeReservationToFile(accountNumber, preference, numberOfMembers, numberOfDays, foodPreference, specialArrangements);

                    // Update the user's account file with the reservation number
                    // You'll need to implement logic to update the user's account.
                } else {
                    System.out.println("Looks like You don't have an account yet. please Create your account first");
                    ChooseOptions();
                }
            } else {
                assert userInput != null;
                if (userInput.equalsIgnoreCase("no")) {

                    System.out.println("You don't have an account. Please select account creation first.");
                    ChooseOptions();
                } else {
                    System.out.println("Invalid answer. Please enter 'yes' or 'no'");
                    getUserInput();
                }
            }
        }
        private void writeReservationToFile(long accountNumber, int preference, int numberOfMembers, int numberOfDays,
                                            String foodPreference, String specialArrangements) {


            Reservation reservation = new Reservation(preference, numberOfMembers, numberOfDays, foodPreference, specialArrangements);

            String accountDirectoryPath = "C://Users//vasan//IdeaProjects//HotelReservationSystem//" + accountNumber;

            long reservationNumber = generateRandomReservationNumber();

            String fileName = accountDirectoryPath + "/rs_" + reservationNumber + ".json";

            try (FileWriter writer = new FileWriter(fileName)) {

                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                String json = gson.toJson(reservation);
                // Write user data to the file

                writer.write(json);

                addReservationToAccount(accountNumber, reservationNumber);

                System.out.println("You're all set! Your reservation is confirmed.");


            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }



        }



        private boolean AccountChecker(long accountNumber) {

            String accountDirectoryPath = "C://Users//vasan//IdeaProjects//HotelReservationSystem//" + accountNumber ;

            File folder = new File(accountDirectoryPath);
            return folder.exists() && folder.isDirectory();

        }

        public static void addReservationToAccount(long accountNumber, long reservationNumber) {
            // Path to the account file
            String accountFilePath = "C://Users//vasan//IdeaProjects//HotelReservationSystem//" + accountNumber + "//acc_" + accountNumber + ".json";

            try (FileReader fileReader = new FileReader(accountFilePath);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {

                // Read the existing user data from the account file
                Gson gson = new Gson();
                User user = gson.fromJson(bufferedReader, User.class);

                if (user != null) {
                    // Add the reservation number to the reservation array
                    user.getReservation().add(reservationNumber);

                    // Write the updated user data back to the account file
                    try (FileWriter fileWriter = new FileWriter(accountFilePath);
                         BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

                        Gson gsonWrite = new GsonBuilder().setPrettyPrinting().create();
                        String json = gsonWrite.toJson(user);
                        bufferedWriter.write(json);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }





/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Path;
/**
 *
 * @author Student
 */
public class Main {
      public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("======================================");
            System.out.println("       REGISTRATION AND LOGIN");
            System.out.println("======================================");

            System.out.print("Enter your first name: ");
            String firstName = scanner.nextLine();

            System.out.print("Enter your last name: ");
            String lastName = scanner.nextLine();

            System.out.print("Enter your username: ");
            String username = scanner.nextLine();

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            System.out.print("Enter your cellphone number (+27XXXXXXXXX): ");
            String cellphone = scanner.nextLine();

            Login user = new Login(
                    firstName, lastName, username,
                    password, cellphone);

            System.out.println();
            System.out.println(user.registerUser());

            // Only allow login when registration is valid.
            if (!user.checkUserName()
                    || !user.checkPasswordComplexity()
                    || !user.checkCellPhoneNumber()) {
                return;
            }

            System.out.println("\n========== LOGIN ==========");

            System.out.print("Enter your username: ");
            String enteredUsername = scanner.nextLine();

            System.out.print("Enter your password: ");
            String enteredPassword = scanner.nextLine();

            if (!user.loginUser(enteredUsername, enteredPassword)) {
                System.out.println(
                        "Username or password incorrect, please try again.");
                return;
            }

            System.out.println("\nWelcome to QuickChat.");

            System.out.print("How many messages would you like to send? ");
            int requestedMessages = readMessageCount(scanner);

            List<Message> sentMessages = new ArrayList<>();
            int sentCount = 0;
            boolean quit = false;

            // Keep displaying the menu until the user chooses Quit.
            while (!quit) {

                System.out.println("\n========== QUICKCHAT ==========");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Choose an option: ");

                String option = scanner.nextLine().trim();

                switch (option) {

                    case "1":

                        if (sentCount >= requestedMessages) {
                            System.out.println(
                                    "You have sent the requested number of messages.");
                            break;
                        }

                        while (sentCount < requestedMessages) {

                            System.out.print(
                                    "Enter recipient cellphone number (+27XXXXXXXXX): ");
                            String recipient = scanner.nextLine().trim();

                            if (!Message.checkRecipientCell(recipient)) {
                                System.out.println(
                                        "Cell phone number is incorrectly formatted "
                                        + "or does not contain an international code. "
                                        + "Please correct the number and try again.");
                                continue;
                            }

                            System.out.print(
                                    "Enter your message (maximum 250 characters): ");
                            String text = scanner.nextLine();

                            if (!Message.checkMessageLength(text)) {
                                System.out.println(
                                        "Please enter a message of less than 250 characters.");
                                continue;
                            }

                            Message message = new Message(
                                    sentCount, recipient, text);

                            System.out.println("\nMessage ID: "
                                    + message.getMessageId());

                            System.out.println("Message Hash: "
                                    + message.getMessageHash());

                            System.out.println("Recipient: "
                                    + message.getRecipient());

                            System.out.println("Message: "
                                    + message.getMessageText());

                            System.out.println("\n1) Send Message");
                            System.out.println("0) Disregard Message");
                            System.out.println("2) Store Message to send later");
                            System.out.print("Choose an option: ");

                            String action = scanner.nextLine().trim();

                            if ("1".equals(action)) {

                                System.out.println(message.sendMessage("1"));
                                sentMessages.add(message);
                                sentCount++;

                            } else if ("0".equals(action)) {

                                System.out.println(message.sendMessage("0"));

                            } else if ("2".equals(action)) {

                                message.storeAsJson(
                                        Path.of("stored_message.json"));
                                System.out.println(message.sendMessage("2"));

                            } else {
                                System.out.println(
                                        "Invalid option. Message was not sent.");
                            }

                            if (sentCount >= requestedMessages) {
                                break;
                            }

                            System.out.print(
                                    "Would you like to continue sending messages? (y/n): ");

                            String answer = scanner.nextLine().trim();

                            if (!answer.equalsIgnoreCase("y")) {
                                break;
                            }
                        }

                        break;

                    case "2":
                        // This feature is still in development.
                        System.out.println("Coming Soon.");
                        break;

                    case "3":
                        quit = true;
                        break;

                    default:
                        System.out.println(
                                "Invalid option. Please select 1, 2, or 3.");
                }
            }

            System.out.println("\nTotal messages sent: " + sentCount);
            System.out.println("Goodbye.");
        }
    }

    // Read a valid number of messages from the user.
    private static int readMessageCount(Scanner scanner) {

        while (true) {

            String input = scanner.nextLine().trim();

            try {
                int number = Integer.parseInt(input);

                if (number >= 0) {
                    return number;
                }

            } catch (NumberFormatException e) {
                // Display an error below if the input is not an integer.
            }

            System.out.print(
                    "Please enter a whole number (0 or greater): ");
        }
    }
}
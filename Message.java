/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.nio.file.Path;
import java.util.Random;
/**
 *
 * @author Student
 */
public class Message {
     private static final Random RANDOM = new Random();

    private final String messageId;
    private final int messageNumber;
    private final String recipient;
    private final String messageText;
    private final String messageHash;

    private static int totalMessagesSent = 0;

    // Constructor
    public Message(int messageNumber, String recipient,
                   String messageText) {

        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;

        this.messageId = generateMessageId();

        this.messageHash = createMessageHash(
                messageId, messageNumber, messageText);
    }

    // Generate a random ten-digit message ID
    private String generateMessageId() {

        long id = 1_000_000_000L
                + (long) (RANDOM.nextDouble() * 9_000_000_000L);

        return String.valueOf(id);
    }

    // Check that the message does not exceed 250 characters
    public static boolean checkMessageLength(String message) {

        return message != null && message.length() <= 250;
    }

    // Check recipient cellphone number
    // South African international format: +27 followed by 9 digits
    public static boolean checkRecipientCell(String recipient) {

        return recipient != null
                && recipient.matches("^\\+27[0-9]{9}$");
    }

    // Generate the message hash
    // Format: first two ID digits:number:two first and last words
    // Example: 00:0:HITHANKS
    public static String createMessageHash(String messageId,
                                           int messageNumber,
                                           String messageText) {

        if (messageId == null || messageText == null
                || messageText.trim().isEmpty()) {
            return "";
        }

        String[] words = messageText.trim().split("\\s+");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        String firstTwoDigits = messageId.length() >= 2
                ? messageId.substring(0, 2)
                : messageId;

        return (firstTwoDigits + ":" + messageNumber + ":"
                + firstWord + lastWord).toUpperCase();
    }

    // Return message ID
    public String getMessageId() {
        return messageId;
    }

    // Return message number
    public int getMessageNumber() {
        return messageNumber;
    }

    // Return recipient
    public String getRecipient() {
        return recipient;
    }

    // Return message text
    public String getMessageText() {
        return messageText;
    }

    // Return message hash
    public String getMessageHash() {
        return messageHash;
    }

    // Return total messages successfully sent
    public static int getTotalMessagesSent() {
        return totalMessagesSent;
    }

    // Send, discard or store a message
    public String sendMessage(String choice) {

        if (choice == null) {
            return "Invalid option.";
        }

        switch (choice) {

            case "1" -> {
                totalMessagesSent++;
                return "Message successfully sent.";
             }

            case "0" -> {
                return "Press 0 to delete the message.";
             }

            case "2" -> {
                return storeMessage()
                        ? "Message successfully stored."
                        : "Message could not be stored.";
             }

            default -> {
                return "Invalid option.";
             }
        }
    }

    private boolean storeMessage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void storeAsJson(Path of) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}


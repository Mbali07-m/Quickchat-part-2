/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class MesssageTest {
    
       @Test
    public void testMessageLengthOf250CharactersIsValid() {
        assertTrue(
                Message.checkMessageLength("a".repeat(250)));
    }

    @Test
    public void testMessageOver250CharactersIsInvalid() {
        assertFalse(
                Message.checkMessageLength("a".repeat(251)));
    }

    @Test
    public void testCorrectRecipientCellphoneNumber() {
        assertTrue(
                Message.checkRecipientCell("+27718693002"));
    }

    @Test
    public void testIncorrectRecipientCellphoneNumber() {
        assertFalse(
                Message.checkRecipientCell("0718693002"));
    }

    @Test
    public void testMessageHashIsCorrect() {
        assertEquals(
                "00:0:HITHANKS",
                Message.createMessageHash(
                        "0012345678", 0, "Hi thanks"));
    }

    @Test
    public void testMessageIdHasTenDigits() {
        Message message = new Message(
                0, "+27718693002", "Hi there");

        assertTrue(
                message.getMessageId().matches("[0-9]{10}"));
    }

    @Test
    public void testSendMessageOption() {
        Message message = new Message(
                0, "+27718693002", "Hi there");

        assertEquals(
                "Message successfully sent.",
                message.sendMessage("1"));
    }

    @Test
    public void testDisregardMessageOption() {
        Message message = new Message(
                0, "+27718693002", "Hi there");

        assertEquals(
                "Press 0 to delete the message.",
                message.sendMessage("0"));
    }

    @Test
    public void testStoreMessageOption() {
        Message message = new Message(
                0, "+27718693002", "Hi there");

        assertEquals(
                "Message successfully stored.",
                message.sendMessage("2"));
    }


}
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
public class LoginTest {
    
 
    @Test
    public void testUsernameCorrectlyFormatted() {

        Login login = new Login(
                "Aisha",
                "Mokoena",
                "a_24",
                "Sun#Rise8!",
                "+27821234567"
        );

        assertEquals(
                "Welcome Aisha Mokoena, it is great to see you again.",
                login.returnLoginStatus(
                        "a_24",
                        "Sun#Rise8!"
                )
        );
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {

        Login login = new Login(
                "Aisha",
                "Mokoena",
                "aisha123",
                "Sun#Rise8!",
                "+27821234567"
        );

        assertEquals(
                "Username is not correctly formatted; please ensure "
                + "that your username contains an underscore and is "
                + "no more than five characters in length.",
                login.registerUser()
        );
    }

    @Test
    public void testUsernameCorrectlyFormattedBoolean() {

        Login login = new Login(
                "Aisha",
                "Mokoena",
                "a_24",
                "Sun#Rise8!",
                "+27821234567"
        );

        assertTrue(login.checkUserName());
    }

    @Test
    public void testUsernameIncorrectlyFormattedBoolean() {

        Login login = new Login(
                "Aisha",
                "Mokoena",
                "aisha123",
                "Sun#Rise8!",
                "+27821234567"
        );

        assertFalse(login.checkUserName());
    }


    // =====================================================
    // PASSWORD TESTS
    // =====================================================

    @Test
    public void testPasswordMeetsComplexityRequirements() {

        Login login = new Login(
                "Aisha",
                "Mokoena",
                "a_24",
                "Sun#Rise8!",
                "+27821234567"
        );

        assertEquals(
                "Registration successful.",
                login.registerUser()
        );
    }

    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {

        Login login = new Login(
                "Aisha",
                "Mokoena",
                "a_24",
                "password",
                "+27821234567"
        );

        assertEquals(
                "Password is not correctly formatted; please ensure "
                + "that the password contains at least eight characters, "
                + "a capital letter, a number, and a special character.",
                login.registerUser()
        );
    }

    @Test
    public void testPasswordComplexityTrue() {

        Login login = new Login(
                "Aisha",
                "Mokoena",
                "a_24",
                "Sun#Rise8!",
                "+27821234567"
        );

        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testPasswordComplexityFalse() {

        Login login = new Login(
                "Aisha",
                "Mokoena",
                "a_24",
                "password",
                "+27821234567"
        );

        assertFalse(login.checkPasswordComplexity());
    }


   
    @Test
    public void testCellPhoneCorrectlyFormatted() {

        Login login = new Login(
                "Aisha",
                "Mokoena",
                "a_24",
                "Sun#Rise8!",
                "+27821234567"
        );

        assertEquals(
                "Registration successful.",
                login.registerUser()
        );
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {

        Login login = new Login(
                "Aisha",
                "Mokoena",
                "a_24",
                "Sun#Rise8!",
                "0821234567"
        );

        assertEquals(
                "Cell number is incorrectly formatted or does not "
                + "contain an international code; please correct the "
                + "number and try again.",
                login.registerUser()
        );
    }

    @Test
    public void testCellPhoneCorrectlyFormattedBoolean() {

        Login login = new Login(
                "Aisha",
                "Mokoena",
                "a_24",
                "Sun#Rise8!",
                "+27821234567"
        );

        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneIncorrectlyFormattedBoolean() {

        Login login = new Login(
                "Aisha",
                "Mokoena",
                "a_24",
                "Sun#Rise8!",
                "0821234567"
        );

        assertFalse(login.checkCellPhoneNumber());
    }


    // =====================================================
    // LOGIN TESTS
    // =====================================================

    @Test
    public void testLoginSuccessful() {

        Login login = new Login(
                "Aisha",
                "Mokoena",
                "a_24",
                "Sun#Rise8!",
                "+27821234567"
        );

        assertTrue(
                login.loginUser(
                        "a_24",
                        "Sun#Rise8!"
                )
        );
    }

    @Test
    public void testLoginFailed() {

        Login login = new Login(
                "Aisha",
                "Mokoena",
                "a_24",
                "Sun#Rise8!",
                "+27821234567"
        );

        assertFalse(
                login.loginUser(
                        "wrong",
                        "wrong"
                )
        );
    }
}
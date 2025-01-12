package vet.system1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddPatientPageTest {

    @Test
    public void testValidPhoneNumber() {
        Doctor doc = new Doctor(1, "Test Doctor", "General", "1234567890", "test@medvet.ro", "password");
        AddPatientPage page = new AddPatientPage(doc);
        assertTrue(page.isValidPhoneNumber("0733415120"), "Valid phone number should pass");
        assertFalse(page.isValidPhoneNumber("12345"), "Invalid phone number should fail");
    }

    @Test
    public void testValidEmail() {
        Doctor doc = new Doctor(1, "Test Doctor", "General", "1234567890", "test@medvet.ro", "password");
        AddPatientPage page = new AddPatientPage(doc);
        assertTrue(page.isValidEmail("test.email@gmail.com"), "Valid email should pass");
        assertFalse(page.isValidEmail("invalid-email"), "Invalid email should fail");
    }

    @Test
    public void testValidDate() {
        Doctor doc = new Doctor(1, "Test Doctor", "General", "1234567890", "test@medvet.ro", "password");
        AddPatientPage page = new AddPatientPage(doc);
        assertTrue(page.isValidDate("2025-01-15"), "Valid date should pass");
        assertFalse(page.isValidDate("15-01-2025"), "Invalid date should fail");
    }
}

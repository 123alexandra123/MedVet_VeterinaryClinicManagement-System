package vet.system1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PatientInfoTest {

    @Test
    public void testIsValidPhoneNumber() {
        PatientInfo patientInfo = new PatientInfo(1); // Using a dummy patientId

        assertTrue(patientInfo.isValidPhoneNumber("0123456789"), "Valid phone number should pass");
        assertFalse(patientInfo.isValidPhoneNumber("12345"), "Invalid phone number should fail");
        assertFalse(patientInfo.isValidPhoneNumber("abcdefghij"), "Phone number with letters should fail");
    }

    @Test
    public void testIsValidEmail() {
        PatientInfo patientInfo = new PatientInfo(1); // Using a dummy patientId

        assertTrue(patientInfo.isValidEmail("test@example.com"), "Valid email should pass");
        assertFalse(patientInfo.isValidEmail("invalid-email"), "Invalid email should fail");
        assertFalse(patientInfo.isValidEmail("test@.com"), "Email without domain name should fail");
    }

    @Test
    public void testIsValidDate() {
        PatientInfo patientInfo = new PatientInfo(1); // Using a dummy patientId

        assertTrue(patientInfo.isValidDate("2023-12-01"), "Valid date should pass");
        assertFalse(patientInfo.isValidDate("12-01-2023"), "Invalid date format should fail");
        assertFalse(patientInfo.isValidDate("invalid-date"), "Non-date string should fail");
    }
}

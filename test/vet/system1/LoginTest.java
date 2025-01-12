package vet.system1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class LoginTest {

    @Test
    public void testValidEmailFormat() {
        Login login = new Login();
        // Valid email
        assertTrue(login.isValidEmail("andrei.popesu@medvet.ro"), "Valid email format should pass");
        // Invalid emails
        assertFalse(login.isValidEmail("andreipopescu@medvet.ro"), "Invalid email format without dot should fail");
        assertFalse(login.isValidEmail("andrei.popescu@gmail.ro"), "Invalid domain should fail");
        assertFalse(login.isValidEmail("invalid-email"), "Invalid email format should fail");
    }

    @Test
    public void testLoginWithValidCredentials() {
        // Mock valid login data
        String email = "andrei.popescu@medvet.ro"; // Ensure this exists in your database
        String password = "parola123"; // Ensure this matches the one in your database

        db_conn mockDb = new db_conn(); // Connect to the test database
        boolean isAuthenticated = authenticate(mockDb, email, password);

        assertTrue(isAuthenticated, "Valid credentials should successfully authenticate");
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        // Mock invalid login data
        String email = "invalid.doctor@medvet.ro";
        String password = "wrongpassword";

        db_conn mockDb = new db_conn(); // Connect to the test database
        boolean isAuthenticated = authenticate(mockDb, email, password);

        assertFalse(isAuthenticated, "Invalid credentials should fail authentication");
    }

    private boolean authenticate(db_conn db, String email, String password) {
        try {
            String query = "SELECT * FROM Doctori_Users WHERE email = ? AND parola = ?";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            boolean isAuthenticated = rs.next();
            rs.close();
            stmt.close();
            return isAuthenticated;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

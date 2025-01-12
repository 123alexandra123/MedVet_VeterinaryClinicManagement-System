

package vet.system1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * This class tests the Login class.
 * It tests the isValidEmail method and the login functionality.
 */


public class LoginTest {

    @Test
    public void testValidEmailFormat() {
        Login login = new Login();
        assertTrue(login.isValidEmail("andrei.popesu@medvet.ro"), "Valid email format should pass");
        assertFalse(login.isValidEmail("andreipopescu@medvet.ro"), "Invalid email format without dot should fail");
        assertFalse(login.isValidEmail("andrei.popescu@gmail.ro"), "Invalid domain should fail");
        assertFalse(login.isValidEmail("invalid-email"), "Invalid email format should fail");
    }

    @Test
    public void testLoginWithValidCredentials() {

        String email = "andrei.popescu@medvet.ro";
        String password = "parola123";

        db_conn mockDb = new db_conn();
        boolean isAuthenticated = authenticate(mockDb, email, password);

        assertTrue(isAuthenticated, "Valid credentials should successfully authenticate");
    }

    @Test
    public void testLoginWithInvalidCredentials() {

        String email = "invalid.doctor@medvet.ro";
        String password = "wrongpassword";

        db_conn mockDb = new db_conn();
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

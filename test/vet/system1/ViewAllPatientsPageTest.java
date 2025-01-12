

package vet.system1;

import org.junit.jupiter.api.Test;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class that tests the ViewAllPatientsPage class.
 * It tests if the patients are displayed correctly in the table.
 */

public class ViewAllPatientsPageTest {

    @Test
    public void testAllPatientsDisplayedCorrectly() {
        ViewAllPatientsPage page = new ViewAllPatientsPage();


        List<String> expectedPatientNames = new ArrayList<>();
        List<Integer> expectedPatientIds = new ArrayList<>();
        try {
            db_conn conn = new db_conn();
            Connection c = conn.connection;
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_animal, nume_animal FROM Pacienti_Animale");

            while (rs.next()) {
                expectedPatientIds.add(rs.getInt("id_animal"));
                expectedPatientNames.add(rs.getString("nume_animal"));
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            fail("Database query failed: " + e.getMessage());
        }


        DefaultTableModel model = (DefaultTableModel) page.table.getModel();
        List<String> actualPatientNames = new ArrayList<>();
        List<Integer> actualPatientIds = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            actualPatientIds.add((Integer) model.getValueAt(i, 0));
            actualPatientNames.add((String) model.getValueAt(i, 1));
        }


        assertEquals(expectedPatientIds, actualPatientIds, "The IDs of patients should match.");
        assertEquals(expectedPatientNames, actualPatientNames, "The names of patients should match.");
    }
}

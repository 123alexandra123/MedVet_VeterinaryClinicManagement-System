

package vet.system1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.table.DefaultTableModel;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class that tests the SearchPatientPage class.
 * It tests the searchPatientByName and searchPatientById methods.
 */

public class SearchPatientPageTest {

    private SearchPatientPage searchPage;

    @BeforeEach
    public void setUp() {

        searchPage = new SearchPatientPage();
    }

    @Test
    public void testSearchPatientByName() {

        DefaultTableModel model = (DefaultTableModel) searchPage.table.getModel();
        model.setRowCount(0);


        searchPage.searchPatientByName("Max");
        assertTrue(model.getRowCount() > 0, "Should find patients with the name 'Max'");


        searchPage.searchPatientByName("NonExistentName");
        assertEquals(0, model.getRowCount(), "Should not find patients with the name 'NonExistentName'");
    }

    @Test
    public void testSearchPatientById() {

        DefaultTableModel model = (DefaultTableModel) searchPage.table.getModel();
        model.setRowCount(0);


        searchPage.searchPatientById(16);
        assertTrue(model.getRowCount() > 0, "Should find a patient with ID '16'");


        searchPage.searchPatientById(9999);
        assertEquals(0, model.getRowCount(), "Should not find a patient with ID '9999'");
    }
}

package vet.system1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.table.DefaultTableModel;

import static org.junit.jupiter.api.Assertions.*;

public class SearchPatientPageTest {

    private SearchPatientPage searchPage;

    @BeforeEach
    public void setUp() {
        // Initialize SearchPatientPage for testing
        searchPage = new SearchPatientPage();
    }

    @Test
    public void testSearchPatientByName() {
        // Clear the table model before testing
        DefaultTableModel model = (DefaultTableModel) searchPage.table.getModel();
        model.setRowCount(0);

        // Simulate searching for a valid name
        searchPage.searchPatientByName("Max");
        assertTrue(model.getRowCount() > 0, "Should find patients with the name 'Max'");

        // Simulate searching for an invalid name
        searchPage.searchPatientByName("NonExistentName");
        assertEquals(0, model.getRowCount(), "Should not find patients with the name 'NonExistentName'");
    }

    @Test
    public void testSearchPatientById() {
        // Clear the table model before testing
        DefaultTableModel model = (DefaultTableModel) searchPage.table.getModel();
        model.setRowCount(0);

        // Simulate searching for a valid ID
        searchPage.searchPatientById(16);
        assertTrue(model.getRowCount() > 0, "Should find a patient with ID '16'");

        // Simulate searching for an invalid ID
        searchPage.searchPatientById(9999);
        assertEquals(0, model.getRowCount(), "Should not find a patient with ID '9999'");
    }
}

package vet.system1;

import org.junit.jupiter.api.Test;
import javax.swing.table.DefaultTableModel;

import static org.junit.jupiter.api.Assertions.*;

public class ViewMyPatientsPageTest {

    @Test
    public void testViewMyPatientsValidDoctor() {
        // Create a Doctor object representing an existing doctor
        Doctor doctor = new Doctor(1, "Popescu Andrei", "Cardiologie", "0721123456", "andrei.popescu@medvet.ro", "parola123");

        // Instantiate the ViewMyPatientsPage with the doctor
        ViewMyPatientsPage viewPage = new ViewMyPatientsPage(doctor);

        // Retrieve the table model to check the data
        DefaultTableModel model = (DefaultTableModel) viewPage.table.getModel();

        // Check that the correct patients are displayed for the doctor
        assertEquals(1, model.getRowCount(), "Doctor 1 should have one patient");
        assertEquals(14, model.getValueAt(0, 0), "Expected patient ID is 14");
        assertEquals("Max", model.getValueAt(0, 1), "Expected patient name is 'Max'");
    }

    @Test
    public void testViewMyPatientsInvalidDoctor() {
        // Create a Doctor object for a doctor with no patients
        Doctor doctor = new Doctor(6, "Nonexistent Doctor", "None", "0000000000", "nonexistent@medvet.ro", "nopassword");

        // Instantiate the ViewMyPatientsPage with the doctor
        ViewMyPatientsPage viewPage = new ViewMyPatientsPage(doctor);

        // Retrieve the table model to check the data
        DefaultTableModel model = (DefaultTableModel) viewPage.table.getModel();

        // Check that no patients are displayed
        assertEquals(0, model.getRowCount(), "Doctor 6 should have no patients");
    }
}

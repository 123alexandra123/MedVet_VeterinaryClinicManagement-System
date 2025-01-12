package vet.system1;

import org.junit.jupiter.api.Test;
import javax.swing.table.DefaultTableModel;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class that tests the ViewMyPatientsPage class.
 * It tests if the doctor can view his patients correctly.
 *
 */

public class ViewMyPatientsPageTest {

    @Test
    public void testViewMyPatientsValidDoctor() {

        Doctor doctor = new Doctor(1, "Popescu Andrei", "Cardiologie", "0721123456", "andrei.popescu@medvet.ro", "parola123");


        ViewMyPatientsPage viewPage = new ViewMyPatientsPage(doctor);


        DefaultTableModel model = (DefaultTableModel) viewPage.table.getModel();


        assertEquals(1, model.getRowCount(), "Doctor 1 should have one patient");
        assertEquals(14, model.getValueAt(0, 0), "Expected patient ID is 14");
        assertEquals("Max", model.getValueAt(0, 1), "Expected patient name is 'Max'");
    }

    @Test
    public void testViewMyPatientsInvalidDoctor() {

        Doctor doctor = new Doctor(6, "Nonexistent Doctor", "None", "0000000000", "nonexistent@medvet.ro", "nopassword");


        ViewMyPatientsPage viewPage = new ViewMyPatientsPage(doctor);


        DefaultTableModel model = (DefaultTableModel) viewPage.table.getModel();


        assertEquals(0, model.getRowCount(), "Doctor 6 should have no patients");
    }
}

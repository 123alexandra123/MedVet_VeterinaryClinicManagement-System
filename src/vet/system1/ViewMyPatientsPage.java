package vet.system1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * A window to display all patients assigned to a specific doctor.
 */
public class ViewMyPatientsPage extends JFrame {
    JTable table; // Table to display the doctor's patients
    Doctor loggedDoctor; // The doctor currently logged in

    /**
     * Creates the "View My Patients" window.
     * Fetches and displays patients associated with the logged-in doctor.
     *
     * @param doctor The logged-in doctor for whom the patients are displayed.
     */
    public ViewMyPatientsPage(Doctor doctor) {
        this.loggedDoctor = doctor;

        setTitle("View My Patients");
        setSize(800, 600);
        setLocation(370, 200);

        // Set up the table with columns for patient ID and name
        String[] columnNames = {"ID", "Animal Name"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        // Add a mouse click listener to open detailed patient information
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int patientId = (int) table.getValueAt(row, 0);
                new PatientInfo(patientId); // Open the PatientInfo window for the selected patient
            }
        });

        try {
            // Establish database connection
            db_conn conn = new db_conn();
            Connection c = conn.connection;

            // Query to fetch patients assigned to the logged-in doctor
            String query = "SELECT pa.id_animal, pa.nume_animal " +
                    "FROM Pacienti_Animale pa " +
                    "JOIN Fisa_Medicala fm ON pa.id_animal = fm.id_animal " +
                    "WHERE fm.id_doctor = ?";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setInt(1, doctor.getIdDoctor()); // Set the doctor's ID in the query

            // Execute the query and populate the table with results
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pacient pacient = new Pacient(
                        rs.getInt("id_animal"),
                        rs.getString("nume_animal"),
                        null, 0, null, null, null, null, null
                );
                model.addRow(new Object[]{pacient.getIdAnimal(), pacient.getNumeAnimal()});
            }

            // Close resources
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Add the table inside a scrollable pane
        add(new JScrollPane(table));
        setVisible(true);
    }
}

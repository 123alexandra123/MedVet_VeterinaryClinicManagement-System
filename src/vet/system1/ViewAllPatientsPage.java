package vet.system1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * A window to view all patients in the database.
 * Displays a table of all animals with their IDs and names.
 */
public class ViewAllPatientsPage extends JFrame {
    JTable table; // Table to display all patients

    /**
     * Creates the "View All Patients" window.
     * Fetches all patient records from the database and displays them in a table.
     */
    public ViewAllPatientsPage() {
        setTitle("View All Patients");
        setSize(800, 600);
        setLocation(370, 200);

        // Set up table columns
        String[] columnNames = {"ID", "Animal Name"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        // Add click listener to open detailed information about a patient
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int patientId = (int) table.getValueAt(row, 0);
                new PatientInfo(patientId);
            }
        });

        try {
            // Database connection
            db_conn conn = new db_conn();
            Connection c = conn.connection;

            // Fetch all patient records
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_animal, nume_animal FROM Pacienti_Animale");

            // Add rows to the table
            while (rs.next()) {
                Pacient pacient = new Pacient(
                        rs.getInt("id_animal"),
                        rs.getString("nume_animal"),
                        null, 0, null, null, null, null, null
                );
                model.addRow(new Object[]{pacient.getIdAnimal(), pacient.getNumeAnimal()});
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Add table to a scrollable pane and display it
        add(new JScrollPane(table));
        setVisible(true);
    }
}

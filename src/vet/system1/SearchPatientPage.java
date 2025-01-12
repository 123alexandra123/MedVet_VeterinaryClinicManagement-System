package vet.system1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * A window that allows users to search for patient records.
 * Users can search by the animal's name or ID, and view results in a table.
 */
public class SearchPatientPage extends JFrame {
    JTextField searchField; // Input field for search terms
    JTable table; // Table to display search results
    JButton searchByNameBtn, searchByIdBtn; // Buttons to trigger search by name or ID

    /**
     * Creates a window for searching patient records.
     */
    public SearchPatientPage() {
        setTitle("Search Patient");
        setSize(500, 400);
        setLocation(370, 200);
        setLayout(null);

        // Label for the search field
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setBounds(30, 20, 120, 30);
        add(searchLabel);

        // Text field to enter search input
        searchField = new JTextField();
        searchField.setBounds(150, 20, 150, 30);
        add(searchField);

        // Button to search by animal's name
        searchByNameBtn = new JButton("Search by Name");
        searchByNameBtn.setBounds(30, 70, 150, 30);
        searchByNameBtn.addActionListener(e -> searchPatientByName(searchField.getText()));
        add(searchByNameBtn);

        // Button to search by animal's ID
        searchByIdBtn = new JButton("Search by ID");
        searchByIdBtn.setBounds(200, 70, 150, 30);
        searchByIdBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(searchField.getText());
                searchPatientById(id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid ID number.", "Invalid ID", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(searchByIdBtn);

        // Table to display search results
        String[] columnNames = {"ID", "Animal Name"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Prevent editing cells
            }
        };
        table = new JTable(model);

        // Scroll pane to contain the results table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 130, 440, 200);
        add(scrollPane);

        // Open patient info when a row is clicked
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int patientId = (int) table.getValueAt(row, 0);
                new PatientInfo(patientId);
            }
        });

        getContentPane().setBackground(new Color(216, 193, 232)); // Set background color
        setSize(530, 600);
        setLocation(400, 40);
        setLayout(null);
        setVisible(true);
    }

    /**
     * Searches for patients by their name and displays the results in the table.
     *
     * @param name The name or part of the name to search for.
     */
    public void searchPatientByName(String name) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear the table

        try {
            db_conn conn = new db_conn();
            Connection c = conn.connection;

            // SQL query to find patients by name
            String query = "SELECT id_animal, nume_animal FROM Pacienti_Animale WHERE nume_animal LIKE ?";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setString(1, "%" + name + "%");

            ResultSet rs = stmt.executeQuery();
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
    }

    /**
     * Searches for a patient by their ID and displays the result in the table.
     *
     * @param id The ID of the patient to search for.
     */
    public void searchPatientById(int id) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear the table

        try {
            db_conn conn = new db_conn();
            Connection c = conn.connection;

            // SQL query to find a patient by ID
            String query = "SELECT id_animal, nume_animal FROM Pacienti_Animale WHERE id_animal = ?";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
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
    }
}

package vet.system1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SearchPatientPage extends JFrame {
    JTextField nameField, idField;
    JTable table;
    JButton searchByNameBtn, searchByIdBtn;

    public SearchPatientPage() {
        setTitle("Search Patient");
        setSize(500, 400);
        setLocation(370, 200);
        setLayout(null);

        JLabel nameLabel = new JLabel("Search by Name:");
        nameLabel.setBounds(30, 20, 120, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 20, 150, 30);
        add(nameField);

        searchByNameBtn = new JButton("Search by Name");
        searchByNameBtn.setBounds(320, 20, 150, 30);
        searchByNameBtn.addActionListener(e -> searchPatientByName(nameField.getText()));
        add(searchByNameBtn);

        JLabel idLabel = new JLabel("Search by ID:");
        idLabel.setBounds(30, 70, 120, 30);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(150, 70, 150, 30);
        add(idField);

        searchByIdBtn = new JButton("Search by ID");
        searchByIdBtn.setBounds(320, 70, 150, 30);
        searchByIdBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                searchPatientById(id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid ID number.", "Invalid ID", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(searchByIdBtn);

        String[] columnNames = {"ID", "Animal Name"};


        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 130, 440, 200);
        add(scrollPane);


        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int patientId = (int) table.getValueAt(row, 0);
                new PatientInfo(patientId);
            }
        });

        getContentPane().setBackground(new Color(216, 193, 232));
        setSize(530, 600);
        setLocation(400, 40);
        setLayout(null);
        setVisible(true);
    }

    private void searchPatientByName(String name) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            db_conn conn = new db_conn();
            Connection c = conn.connection;

            String query = "SELECT id_animal, nume_animal FROM Pacienti_Animale WHERE nume_animal LIKE ?";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setString(1, "%" + name + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_animal");
                String animalName = rs.getString("nume_animal");
                model.addRow(new Object[]{id, animalName});
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void searchPatientById(int id) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            db_conn conn = new db_conn();
            Connection c = conn.connection;

            String query = "SELECT id_animal, nume_animal FROM Pacienti_Animale WHERE id_animal = ?";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String animalName = rs.getString("nume_animal");
                model.addRow(new Object[]{id, animalName});
            } else {
                JOptionPane.showMessageDialog(this, "No patient found with ID: " + id, "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

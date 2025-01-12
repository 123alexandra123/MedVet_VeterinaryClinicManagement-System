package vet.system1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SearchPatientPage extends JFrame {
    JTextField searchField;
    JTable table;
    JButton searchByNameBtn, searchByIdBtn;

    public SearchPatientPage() {
        setTitle("Search Patient");
        setSize(500, 400);
        setLocation(370, 200);
        setLayout(null);

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setBounds(30, 20, 120, 30);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(150, 20, 150, 30);
        add(searchField);

        searchByNameBtn = new JButton("Search by Name");
        searchByNameBtn.setBounds(30, 70, 150, 30);
        searchByNameBtn.addActionListener(e -> searchPatientByName(searchField.getText()));
        add(searchByNameBtn);

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

    public void searchPatientByName(String name) {
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

    public void searchPatientById(int id) {
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

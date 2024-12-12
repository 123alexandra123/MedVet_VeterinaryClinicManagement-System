package vet.system1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class ViewAllPatientsPage extends JFrame {
    JTable table;

    public ViewAllPatientsPage() {
        setTitle("View All Patients");
        setSize(800, 600);
        setLocation(370, 200);

        String[] columnNames = {"ID", "Animal Name"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int patientId = (int) table.getValueAt(row, 0);
                new PatientInfo(patientId);
            }
        });

        try {
            db_conn conn = new db_conn();
            Statement stmt = conn.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_animal, nume_animal FROM Pacienti_Animale");

            while (rs.next()) {
                int id = rs.getInt("id_animal");
                String name = rs.getString("nume_animal");
                model.addRow(new Object[]{id, name});
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        add(new JScrollPane(table));
        setVisible(true);
    }
}
package vet.system1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class ViewMyPatientsPage extends JFrame {
    JTable table;
    int idDoctor;

    public ViewMyPatientsPage(int idDoctor) {
        this.idDoctor = idDoctor;

        setTitle("View My Patients");
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
            Connection c = conn.connection;

            String query = "SELECT pa.id_animal, pa.nume_animal " +
                    "FROM Pacienti_Animale pa " +
                    "JOIN Fisa_Medicala fm ON pa.id_animal = fm.id_animal " +
                    "WHERE fm.id_doctor = ?";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setInt(1, idDoctor);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_animal");
                String name = rs.getString("nume_animal");
                model.addRow(new Object[]{id, name});
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        add(new JScrollPane(table));
        setVisible(true);
    }
}

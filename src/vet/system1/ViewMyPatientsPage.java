package vet.system1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewMyPatientsPage extends JFrame {
    JTable table;
    Doctor loggedDoctor;

    public ViewMyPatientsPage(Doctor doctor) {
        this.loggedDoctor = doctor;

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

            String query = "SELECT pa.id_animal, pa.nume_animal FROM Pacienti_Animale pa JOIN Fisa_Medicala fm ON pa.id_animal = fm.id_animal WHERE fm.id_doctor = ?";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setInt(1, doctor.getIdDoctor());

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

        add(new JScrollPane(table));
        setVisible(true);
    }
}

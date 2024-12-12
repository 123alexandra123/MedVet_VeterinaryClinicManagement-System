package vet.system1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class PatientInfo extends JFrame {
    int patientId;
    JTextField nameField, genderField, ageField, categoryField, breedField, ownerField, phoneField, emailField;
    JTextArea diagnosticField, treatmentField;
    JTextField appointmentDateField, doctorField;
    JButton editButton, saveButton, deleteButton;
    boolean isEditing = false;
    private JTable patientTable;

    public PatientInfo(int patientId) {
        this.patientId = patientId;
        setTitle("Patient Information");
        setSize(500, 600);
        setLocation(370, 200);
        setLayout(new GridLayout(0, 1));


        nameField = new JTextField();
        genderField = new JTextField();
        ageField = new JTextField();
        categoryField = new JTextField();
        breedField = new JTextField();
        ownerField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        diagnosticField = new JTextArea();
        treatmentField = new JTextArea();
        appointmentDateField = new JTextField();
        doctorField = new JTextField();


        doctorField.setEditable(false);

        add(new JLabel("Animal Name:"));
        add(nameField);
        add(new JLabel("Gender:"));
        add(genderField);
        add(new JLabel("Age:"));
        add(ageField);
        add(new JLabel("Category:"));
        add(categoryField);
        add(new JLabel("Breed:"));
        add(breedField);
        add(new JLabel("Owner:"));
        add(ownerField);
        add(new JLabel("Owner Phone:"));
        add(phoneField);
        add(new JLabel("Owner Email:"));
        add(emailField);
        add(new JLabel("Diagnostic:"));
        add(new JScrollPane(diagnosticField));
        add(new JLabel("Treatment:"));
        add(new JScrollPane(treatmentField));
        add(new JLabel("Appointment Date (YYYY-MM-DD):"));
        add(appointmentDateField);
        add(new JLabel("Doctor:"));
        add(doctorField);

        editButton = new JButton("Edit");
        saveButton = new JButton("Save");
        deleteButton = new JButton("Delete");

        editButton.addActionListener(e -> enableEditing());
        saveButton.addActionListener(e -> savePatient());
        deleteButton.addActionListener(e -> deletePatient());

        add(editButton);
        add(saveButton);
        add(deleteButton);

        displayPatientInfo();
        setFieldsEditable(false);
        saveButton.setEnabled(false);
        setVisible(true);
    }

    private void displayPatientInfo() {
        try {
            db_conn conn = new db_conn();
            PreparedStatement stmt = conn.connection.prepareStatement(
                    "SELECT p.nume_animal, p.gen, p.varsta, p.categorie, p.rasa, p.nume_stapan, p.numar_telefon_stapan, p.email_stapan, " +
                            "f.diagnostic, f.tratament_recomandari, f.data_programare, d.nume_doctor " +
                            "FROM Pacienti_Animale p " +
                            "JOIN Fisa_Medicala f ON p.id_animal = f.id_animal " +
                            "JOIN Doctori_Users d ON f.id_doctor = d.id_doctor " +
                            "WHERE p.id_animal = ?");
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nameField.setText(rs.getString("nume_animal"));
                genderField.setText(rs.getString("gen"));
                ageField.setText(String.valueOf(rs.getInt("varsta")));
                categoryField.setText(rs.getString("categorie"));
                breedField.setText(rs.getString("rasa"));
                ownerField.setText(rs.getString("nume_stapan"));
                phoneField.setText(rs.getString("numar_telefon_stapan"));
                emailField.setText(rs.getString("email_stapan"));
                diagnosticField.setText(rs.getString("diagnostic"));
                treatmentField.setText(rs.getString("tratament_recomandari"));
                appointmentDateField.setText(rs.getDate("data_programare").toString());
                doctorField.setText(rs.getString("nume_doctor"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setFieldsEditable(boolean editable) {
        nameField.setEditable(editable);
        genderField.setEditable(editable);
        ageField.setEditable(editable);
        categoryField.setEditable(editable);
        breedField.setEditable(editable);
        ownerField.setEditable(editable);
        phoneField.setEditable(editable);
        emailField.setEditable(editable);
        diagnosticField.setEditable(editable);
        treatmentField.setEditable(editable);
        appointmentDateField.setEditable(editable);
    }

    private void enableEditing() {
        setFieldsEditable(true);
        saveButton.setEnabled(true);
        editButton.setEnabled(false);
    }

    private void savePatient() {
        try {
            db_conn conn = new db_conn();


            PreparedStatement updateAnimal = conn.connection.prepareStatement(
                    "UPDATE Pacienti_Animale SET nume_animal = ?, gen = ?, varsta = ?, categorie = ?, rasa = ?, nume_stapan = ?, numar_telefon_stapan = ?, email_stapan = ? WHERE id_animal = ?");
            updateAnimal.setString(1, nameField.getText());
            updateAnimal.setString(2, genderField.getText());
            updateAnimal.setInt(3, Integer.parseInt(ageField.getText()));
            updateAnimal.setString(4, categoryField.getText());
            updateAnimal.setString(5, breedField.getText());
            updateAnimal.setString(6, ownerField.getText());
            updateAnimal.setString(7, phoneField.getText());
            updateAnimal.setString(8, emailField.getText());
            updateAnimal.setInt(9, patientId);
            updateAnimal.executeUpdate();


            PreparedStatement updateMedical = conn.connection.prepareStatement(
                    "UPDATE Fisa_Medicala SET diagnostic = ?, tratament_recomandari = ?, data_programare = ? WHERE id_animal = ?");
            updateMedical.setString(1, diagnosticField.getText());
            updateMedical.setString(2, treatmentField.getText());
            updateMedical.setDate(3, Date.valueOf(appointmentDateField.getText()));
            updateMedical.setInt(4, patientId);
            updateMedical.executeUpdate();

            JOptionPane.showMessageDialog(this, "Patient information updated successfully.");
            setFieldsEditable(false);
            saveButton.setEnabled(false);
            editButton.setEnabled(true);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating patient information.");
        }
    }




    private void deletePatient() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this patient?", "Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                db_conn conn = new db_conn();


                PreparedStatement stmtMedicalRecord = conn.connection.prepareStatement("DELETE FROM Fisa_Medicala WHERE id_animal = ?");
                stmtMedicalRecord.setInt(1, patientId);
                stmtMedicalRecord.executeUpdate();


                PreparedStatement stmtAnimal = conn.connection.prepareStatement("DELETE FROM Pacienti_Animale WHERE id_animal = ?");
                stmtAnimal.setInt(1, patientId);
                stmtAnimal.executeUpdate();

                JOptionPane.showMessageDialog(this, "Patient deleted successfully.");



                dispose();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting patient.");
            }
        }
    }

}

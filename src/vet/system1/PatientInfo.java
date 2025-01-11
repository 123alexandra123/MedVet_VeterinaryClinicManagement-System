package vet.system1;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class PatientInfo extends JFrame {
    private int patientId;
    private Pacient pacient;
    private FisaMedicala fisaMedicala;
    private Doctor doctor;

    JTextField nameField, genderField, ageField, categoryField, breedField, ownerField, phoneField, emailField;
    JTextArea diagnosticField, treatmentField;
    JTextField appointmentDateField, doctorField;
    JButton editButton, saveButton, deleteButton;

    public PatientInfo(int patientId) {
        this.patientId = patientId;
        setTitle("Patient Information");
        setSize(500, 600);
        setLocation(370, 200);
        setLayout(new GridLayout(0, 1));

        initializeFields();

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

    private void initializeFields() {
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
    }

    private void displayPatientInfo() {
        try {
            db_conn conn = new db_conn();
            PreparedStatement stmt = conn.connection.prepareStatement(
                    "SELECT p.id_animal, p.nume_animal, p.gen, p.varsta, p.categorie, p.rasa, p.nume_stapan, p.numar_telefon_stapan, p.email_stapan, " +
                            "f.id_fisa, f.diagnostic, f.tratament_recomandari, f.data_programare, " +
                            "d.id_doctor, d.nume_doctor, d.specializare, d.numar_telefon, d.email, d.parola " +
                            "FROM Pacienti_Animale p " +
                            "JOIN Fisa_Medicala f ON p.id_animal = f.id_animal " +
                            "JOIN Doctori_Users d ON f.id_doctor = d.id_doctor " +
                            "WHERE p.id_animal = ?");
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pacient = new Pacient(
                        rs.getInt("id_animal"),
                        rs.getString("nume_animal"),
                        rs.getString("gen"),
                        rs.getInt("varsta"),
                        rs.getString("categorie"),
                        rs.getString("rasa"),
                        rs.getString("nume_stapan"),
                        rs.getString("numar_telefon_stapan"),
                        rs.getString("email_stapan")
                );

                fisaMedicala = new FisaMedicala(
                        rs.getInt("id_fisa"),
                        rs.getInt("id_doctor"),
                        rs.getInt("id_animal"),
                        rs.getString("diagnostic"),
                        rs.getString("tratament_recomandari"),
                        rs.getDate("data_programare").toLocalDate()
                );

                doctor = new Doctor(
                        rs.getInt("id_doctor"),
                        rs.getString("nume_doctor"),
                        rs.getString("specializare"),
                        rs.getString("numar_telefon"),
                        rs.getString("email"),
                        rs.getString("parola")
                );

                populateFields();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void populateFields() {
        nameField.setText(pacient.getNumeAnimal());
        genderField.setText(pacient.getGen());
        ageField.setText(String.valueOf(pacient.getVarsta()));
        categoryField.setText(pacient.getCategorie());
        breedField.setText(pacient.getRasa());
        ownerField.setText(pacient.getNumeStapan());
        phoneField.setText(pacient.getNumarTelefonStapan());
        emailField.setText(pacient.getEmailStapan());
        diagnosticField.setText(fisaMedicala.getDiagnostic());
        treatmentField.setText(fisaMedicala.getTratamentRecomandari());
        appointmentDateField.setText(fisaMedicala.getDataProgramare().toString());
        doctorField.setText(doctor.getNumeDoctor());
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

            String phone = phoneField.getText();
            if (!isValidPhoneNumber(phone)) {
                JOptionPane.showMessageDialog(this, "Phone number must have exactly 10 digits.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String email = emailField.getText();
            if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(this, "Invalid email format. Please use a valid email address.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String date = appointmentDateField.getText();
            if (!isValidDate(date)) {
                JOptionPane.showMessageDialog(this, "Invalid date format. Please use YYYY-MM-DD.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            db_conn conn = new db_conn();

            PreparedStatement updateAnimal = conn.connection.prepareStatement(
                    "UPDATE Pacienti_Animale SET nume_animal = ?, gen = ?, varsta = ?, categorie = ?, rasa = ?, nume_stapan = ?, numar_telefon_stapan = ?, email_stapan = ? WHERE id_animal = ?");
            updateAnimal.setString(1, nameField.getText());
            updateAnimal.setString(2, genderField.getText());
            updateAnimal.setInt(3, Integer.parseInt(ageField.getText()));
            updateAnimal.setString(4, categoryField.getText());
            updateAnimal.setString(5, breedField.getText());
            updateAnimal.setString(6, ownerField.getText());
            updateAnimal.setString(7, phone);
            updateAnimal.setString(8, email);
            updateAnimal.setInt(9, pacient.getIdAnimal());
            updateAnimal.executeUpdate();

            PreparedStatement updateMedical = conn.connection.prepareStatement(
                    "UPDATE Fisa_Medicala SET diagnostic = ?, tratament_recomandari = ?, data_programare = ? WHERE id_fisa = ?");
            updateMedical.setString(1, diagnosticField.getText());
            updateMedical.setString(2, treatmentField.getText());
            updateMedical.setDate(3, Date.valueOf(date));
            updateMedical.setInt(4, fisaMedicala.getIdFisa());
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
                stmtMedicalRecord.setInt(1, pacient.getIdAnimal());
                stmtMedicalRecord.executeUpdate();

                PreparedStatement stmtAnimal = conn.connection.prepareStatement("DELETE FROM Pacienti_Animale WHERE id_animal = ?");
                stmtAnimal.setInt(1, pacient.getIdAnimal());
                stmtAnimal.executeUpdate();

                JOptionPane.showMessageDialog(this, "Patient deleted successfully.");
                dispose();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting patient.");
            }
        }
    }

    private boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\d{10}");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailRegex, email);
    }

    private boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

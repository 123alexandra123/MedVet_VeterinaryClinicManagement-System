package vet.system1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class AddPatientPage extends JFrame implements ActionListener {
    JTextField nameField, genderField, ageField, categoryField, breedField, ownerNameField, ownerPhoneField, ownerEmailField;
    JTextField diagnosticField, treatmentField, appointmentDateField;
    JButton submitBtn, backBtn;
    Doctor loggedDoctor;

    public AddPatientPage(Doctor doctor) {
        this.loggedDoctor = doctor;


        JLabel titleLabel = new JLabel("Add New Patient");
        titleLabel.setBounds(145, 10, 300, 30);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(titleLabel);


        nameField = new JTextField();
        addField("Animal Name:", 60, nameField);

        genderField = new JTextField();
        addField("Gender:", 100, genderField);

        ageField = new JTextField();
        addField("Age:", 140, ageField);

        categoryField = new JTextField();
        addField("Category:", 180, categoryField);

        breedField = new JTextField();
        addField("Breed:", 220, breedField);

        ownerNameField = new JTextField();
        addField("Owner Name:", 260, ownerNameField);

        ownerPhoneField = new JTextField();
        addField("Owner Phone:", 300, ownerPhoneField);

        ownerEmailField = new JTextField();
        addField("Owner Email:", 340, ownerEmailField);

        diagnosticField = new JTextField();
        addField("Diagnostic:", 380, diagnosticField);

        treatmentField = new JTextField();
        addField("Treatment:", 420, treatmentField);

        appointmentDateField = new JTextField();
        addField("Appointment Date (yyyy-mm-dd):", 460, appointmentDateField);


        backBtn = new JButton("Back");
        backBtn.setBounds(290, 700, 100, 30);
        backBtn.addActionListener(this);
        add(backBtn);

        submitBtn = new JButton("Send");
        submitBtn.setBounds(140, 700, 100, 30);
        submitBtn.addActionListener(this);
        add(submitBtn);


        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("vet/system1/icons/fisa.png"));
        Image i1 = icon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon icon1 = new ImageIcon(i1);
        JLabel iconLabel = new JLabel(icon1);
        iconLabel.setBounds(65, 450, 400, 300);
        add(iconLabel);


        getContentPane().setBackground(new Color(216, 193, 232));
        setSize(530, 800);
        setLocation(400, 40);
        setLayout(null);
        setVisible(true);
    }

    private void addField(String label, int y, JTextField field) {
        JLabel jLabel = new JLabel(label);
        jLabel.setBounds(20, y, 300, 30);
        add(jLabel);

        field.setBounds(300, y, 200, 30);
        add(field);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitBtn) {
            try {

                String phone = ownerPhoneField.getText();
                if (!isValidPhoneNumber(phone)) {
                    JOptionPane.showMessageDialog(this, "Phone number must have exactly 10 digits.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String email = ownerEmailField.getText();
                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(this, "Invalid email format. Please use a valid email address.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String appointmentDate = appointmentDateField.getText();
                if (!isValidDate(appointmentDate)) {
                    JOptionPane.showMessageDialog(this, "Invalid date format. Please use YYYY-MM-DD.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                Pacient pacient = new Pacient(
                        0,
                        nameField.getText(),
                        genderField.getText(),
                        Integer.parseInt(ageField.getText()),
                        categoryField.getText(),
                        breedField.getText(),
                        ownerNameField.getText(),
                        phone,
                        email
                );

                FisaMedicala fisaMedicala = new FisaMedicala(
                        0,
                        loggedDoctor.getIdDoctor(),
                        0,
                        diagnosticField.getText(),
                        treatmentField.getText(),
                        LocalDate.parse(appointmentDate)
                );


                db_conn conn = new db_conn();
                Connection c = conn.connection;


                String patientQuery = "INSERT INTO Pacienti_Animale (nume_animal, gen, varsta, categorie, rasa, nume_stapan, numar_telefon_stapan, email_stapan) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement patientStmt = c.prepareStatement(patientQuery, Statement.RETURN_GENERATED_KEYS);
                patientStmt.setString(1, pacient.getNumeAnimal());
                patientStmt.setString(2, pacient.getGen());
                patientStmt.setInt(3, pacient.getVarsta());
                patientStmt.setString(4, pacient.getCategorie());
                patientStmt.setString(5, pacient.getRasa());
                patientStmt.setString(6, pacient.getNumeStapan());
                patientStmt.setString(7, pacient.getNumarTelefonStapan());
                patientStmt.setString(8, pacient.getEmailStapan());
                patientStmt.executeUpdate();

                ResultSet rs = patientStmt.getGeneratedKeys();
                if (rs.next()) {
                    int idPacient = rs.getInt(1);
                    pacient.setIdAnimal(idPacient);
                }


                String medicalQuery = "INSERT INTO Fisa_Medicala (id_doctor, id_animal, diagnostic, tratament_recomandari, data_programare) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement medicalStmt = c.prepareStatement(medicalQuery);
                medicalStmt.setInt(1, fisaMedicala.getIdDoctor());
                medicalStmt.setInt(2, pacient.getIdAnimal());
                medicalStmt.setString(3, fisaMedicala.getDiagnostic());
                medicalStmt.setString(4, fisaMedicala.getTratamentRecomandari());
                medicalStmt.setDate(5, Date.valueOf(fisaMedicala.getDataProgramare()));
                medicalStmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Patient and medical record added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding patient: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == backBtn) {
            this.dispose();
            MainPage.openMainPage(loggedDoctor);
        }
    }

    public boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\d{10}");
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailRegex, email);
    }

    public boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
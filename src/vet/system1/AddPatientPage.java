package vet.system1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddPatientPage extends JFrame implements ActionListener {
    JTextField nameField, genderField, ageField, categoryField, breedField, ownerNameField, ownerPhoneField, ownerEmailField;
    JTextField diagnosticField, treatmentField, appointmentDateField;
    JButton submitBtn, backBtn;
    int idDoctor;

    public AddPatientPage(int idDoctor) {
        this.idDoctor = idDoctor;

        JLabel titleLabel = new JLabel("Add New Patient");
        titleLabel.setBounds(145, 10, 300, 30);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(titleLabel);


        nameField = new JTextField();
        nameField.setBounds(300, 60, 200, 30);
        add(nameField);
        addField("Animal Name: ", 60);

        genderField = new JTextField();
        genderField.setBounds(300, 100, 200, 30);
        add(genderField);
        addField("Gender: ", 100);

        ageField = new JTextField();
        ageField.setBounds(300, 140, 200, 30);
        add(ageField);
        addField("Age: ", 140);

        categoryField = new JTextField();
        categoryField.setBounds(300, 180, 200, 30);
        add(categoryField);
        addField("Category: ", 180);

        breedField = new JTextField();
        breedField.setBounds(300, 220, 200, 30);
        add(breedField);
        addField("Breed: ", 220);

        ownerNameField = new JTextField();
        ownerNameField.setBounds(300, 260, 200, 30);
        add(ownerNameField);
        addField("Owner Name: ", 260);

        ownerPhoneField = new JTextField();
        ownerPhoneField.setBounds(300, 300, 200, 30);
        add(ownerPhoneField);
        addField("Owner Phone: ", 300);

        ownerEmailField = new JTextField();
        ownerEmailField.setBounds(300, 340, 200, 30);
        add(ownerEmailField);
        addField("Owner E-mail: ", 340);


        diagnosticField = new JTextField();
        diagnosticField.setBounds(300, 380, 200, 30);
        add(diagnosticField);
        addField("Diagnostic: ", 380);

        treatmentField = new JTextField();
        treatmentField.setBounds(300, 420, 200, 30);
        add(treatmentField);
        addField("Treatment/Recommendations: ", 420);

        appointmentDateField = new JTextField();
        appointmentDateField.setBounds(300, 460, 200, 30);
        add(appointmentDateField);
        addField("Schedule Date (year-mounth-day): ", 460);


        backBtn = new JButton("Back");
        backBtn.setBounds(290, 700, 100, 30);
        backBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
        backBtn.addActionListener(this);
        add(backBtn);


        submitBtn = new JButton("Send");
        submitBtn.setBounds(140, 700, 100, 30);
        submitBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
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

    private void addField(String label, int y) {
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        jLabel.setBounds(20, y, 300, 30);
        jLabel.setForeground(Color.DARK_GRAY);
        add(jLabel);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitBtn) {
            try {
                db_conn conn = new db_conn();
                Connection c = conn.connection;


                String patientQuery = "INSERT INTO Pacienti_Animale (nume_animal, gen, varsta, categorie, rasa, nume_stapan, numar_telefon_stapan, email_stapan) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement patientStmt = c.prepareStatement(patientQuery);
                patientStmt.setString(1, nameField.getText());
                patientStmt.setString(2, genderField.getText());
                patientStmt.setInt(3, Integer.parseInt(ageField.getText()));
                patientStmt.setString(4, categoryField.getText());
                patientStmt.setString(5, breedField.getText());
                patientStmt.setString(6, ownerNameField.getText());
                patientStmt.setString(7, ownerPhoneField.getText());
                patientStmt.setString(8, ownerEmailField.getText());


                patientStmt.executeUpdate();


                String medicalRecordQuery = "INSERT INTO Fisa_Medicala (id_doctor, id_animal, diagnostic, tratament_recomandari, data_programare) VALUES (?, LAST_INSERT_ID(), ?, ?, ?)";
                PreparedStatement medicalStmt = c.prepareStatement(medicalRecordQuery);
                medicalStmt.setInt(1, idDoctor);
                medicalStmt.setString(2, diagnosticField.getText());
                medicalStmt.setString(3, treatmentField.getText());
                medicalStmt.setString(4, appointmentDateField.getText());

                medicalStmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Pacient și fișă medicală adăugate cu succes!", "Succes", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Eroare la adăugare pacient: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == backBtn) {
            this.dispose();
            MainPage.openMainPage(idDoctor);
        }
    }
}

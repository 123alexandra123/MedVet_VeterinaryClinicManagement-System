package vet.system1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JTextField UserField, EmailField;
    JPasswordField Password;
    JButton b1, b2;

    public Login() {
        JLabel welLabel = new JLabel("Welcome to the MedVet System Management App");
        welLabel.setBounds(50, 10, 1000, 40);
        welLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        welLabel.setForeground(Color.BLACK);
        add(welLabel);

        JLabel logLabel = new JLabel("Login");
        logLabel.setBounds(180, 80, 1000, 40);
        logLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        logLabel.setForeground(Color.DARK_GRAY);
        add(logLabel);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(70, 160, 100, 30);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        nameLabel.setForeground(Color.DARK_GRAY);
        add(nameLabel);

        JLabel emailLabel = new JLabel("E-mail");
        emailLabel.setBounds(70, 240, 100, 30);
        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        emailLabel.setForeground(Color.DARK_GRAY);
        add(emailLabel);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(70, 320, 100, 30);
        passLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        passLabel.setForeground(Color.DARK_GRAY);
        add(passLabel);

        UserField = new JTextField();
        UserField.setBounds(200, 160, 200, 30);
        UserField.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(UserField);

        EmailField = new JTextField();
        EmailField.setBounds(200, 240, 200, 30);
        EmailField.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(EmailField);

        Password = new JPasswordField();
        Password.setBounds(200, 320, 200, 30);
        Password.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(Password);

        b1 = new JButton("Login");
        b1.setBounds(270, 390, 120, 30);
        b1.setFont(new Font("Tahoma", Font.BOLD, 15));
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(80, 390, 120, 30);
        b2.setFont(new Font("Tahoma", Font.BOLD, 15));
        b2.addActionListener(this);
        add(b2);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("vet/system1/icons/veterinary.png"));
        Image i1 = icon.getImage().getScaledInstance(500, 450, Image.SCALE_DEFAULT);
        ImageIcon icon1 = new ImageIcon(i1);
        JLabel iconLabel = new JLabel(icon1);
        iconLabel.setBounds(500, 90, 400, 300);
        add(iconLabel);

        getContentPane().setBackground(new Color(216, 193, 232));
        setSize(1000, 500);
        setLocation(370, 200);
        setLayout(null);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                String user = UserField.getText();
                String email = EmailField.getText();
                String pass = new String(Password.getPassword());

                if (user.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter name, email, and password", "Login Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                db_conn conn = new db_conn();
                Connection c = conn.connection;

                String query = "SELECT id_doctor, nume_doctor FROM Doctori_Users WHERE nume_doctor = ? AND email = ? AND parola = ?";
                PreparedStatement stmt = c.prepareStatement(query);
                stmt.setString(1, user);
                stmt.setString(2, email);
                stmt.setString(3, pass);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    int idDoctor = rs.getInt("id_doctor");
                    String doctorName = rs.getString("nume_doctor");
                    JOptionPane.showMessageDialog(this, "Welcome, Dr. " + doctorName, "Login Successful", JOptionPane.INFORMATION_MESSAGE);

                    setVisible(false);
                    MainPage.openMainPage(idDoctor);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid name, email, or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }

                rs.close();
                stmt.close();
                c.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database connection error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == b2) {
            dispose();
        }
    }
}

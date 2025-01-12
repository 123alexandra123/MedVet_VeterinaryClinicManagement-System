package vet.system1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;

public class Login extends JFrame implements ActionListener {
    JTextField EmailField;
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

        JLabel emailLabel = new JLabel("E-mail");
        emailLabel.setBounds(70, 160, 100, 30);
        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        emailLabel.setForeground(Color.DARK_GRAY);
        add(emailLabel);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(70, 240, 100, 30);
        passLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        passLabel.setForeground(Color.DARK_GRAY);
        add(passLabel);

        EmailField = new JTextField();
        EmailField.setBounds(200, 160, 200, 30);
        EmailField.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(EmailField);

        Password = new JPasswordField();
        Password.setBounds(200, 240, 200, 30);
        Password.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(Password);

        b1 = new JButton("Login");
        b1.setBounds(270, 310, 120, 30);
        b1.setFont(new Font("Tahoma", Font.BOLD, 15));
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(80, 310, 120, 30);
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
                String email = EmailField.getText();
                String pass = new String(Password.getPassword());


                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(this, "Invalid email format. Please use the format nume.prenume@medvet.ro.", "Login Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (email.isEmpty() || pass.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter email and password", "Login Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                db_conn conn = new db_conn();
                Connection c = conn.connection;

                String query = "SELECT id_doctor, nume_doctor, specializare, numar_telefon, email, parola FROM Doctori_Users WHERE email = ? AND parola = ?";
                PreparedStatement stmt = c.prepareStatement(query);
                stmt.setString(1, email);
                stmt.setString(2, pass);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Doctor loggedDoctor = new Doctor(
                            rs.getInt("id_doctor"),
                            rs.getString("nume_doctor"),
                            rs.getString("specializare"),
                            rs.getString("numar_telefon"),
                            rs.getString("email"),
                            rs.getString("parola")
                    );

                    setVisible(false);
                    MainPage.openMainPage(loggedDoctor);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid email or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
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


    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z]+\\.[a-zA-Z]+@medvet\\.ro$";
        return Pattern.matches(emailRegex, email);
    }
}

package vet.system1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents the main page of the application.
 * Provides navigation options for different operations like adding patients,
 * searching patients, and viewing patient records.
 */

public class MainPage extends JFrame implements ActionListener {
    JButton addPatientBtn, searchPatientBtn, viewAllPatientsBtn, viewMyPatientsBtn;
    Doctor loggedDoctor;

    /**
     * Constructs the main page for the application.
     *
     * @param doctor The currently logged-in doctor.
     */
    public MainPage(Doctor doctor) {
        this.loggedDoctor = doctor;

        JLabel titleLabel = new JLabel("MedVet System Management - Main Page");
        titleLabel.setBounds(80, 10, 1000, 40);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        titleLabel.setForeground(Color.BLACK);
        add(titleLabel);

        JLabel welcomeLabel = new JLabel("Welcome, Dr. " + doctor.getNumeDoctor() + " (" + doctor.getSpecializare() + ")");
        welcomeLabel.setBounds(90, 60, 500, 30);
        welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(welcomeLabel);

        addPatientBtn = new JButton("Add Patient");
        addPatientBtn.setBounds(90, 100, 200, 40);
        addPatientBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
        addPatientBtn.addActionListener(this);
        add(addPatientBtn);

        searchPatientBtn = new JButton("Search Patient");
        searchPatientBtn.setBounds(90, 180, 200, 40);
        searchPatientBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
        searchPatientBtn.addActionListener(this);
        add(searchPatientBtn);

        viewAllPatientsBtn = new JButton("View All Patients");
        viewAllPatientsBtn.setBounds(90, 260, 200, 40);
        viewAllPatientsBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
        viewAllPatientsBtn.addActionListener(this);
        add(viewAllPatientsBtn);

        viewMyPatientsBtn = new JButton("View My Patients");
        viewMyPatientsBtn.setBounds(90, 340, 200, 40);
        viewMyPatientsBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
        viewMyPatientsBtn.addActionListener(this);
        add(viewMyPatientsBtn);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("vet/system1/icons/clinica.png"));
        Image i1 = icon.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon icon1 = new ImageIcon(i1);
        JLabel iconLabel = new JLabel(icon1);
        iconLabel.setBounds(450, 90, 400, 300);
        add(iconLabel);

        getContentPane().setBackground(new Color(216, 193, 232));
        setSize(1000, 500);
        setLocation(370, 200);
        setLayout(null);
        setVisible(true);
    }

    /**
     * Handles button click events for navigation.
     *
     * @param e The action event triggered by a button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPatientBtn) {
            new AddPatientPage(loggedDoctor); // Open the Add Patient Page
        } else if (e.getSource() == searchPatientBtn) {
            new SearchPatientPage(); // Open the Search Patient Page
        } else if (e.getSource() == viewAllPatientsBtn) {
            new ViewAllPatientsPage(); // Open the View All Patients Page
        } else if (e.getSource() == viewMyPatientsBtn) {
            new ViewMyPatientsPage(loggedDoctor); // Open the View My Patients Page
        }
    }

    /**
     * Opens the main page of the application.
     *
     * @param doctor The currently logged-in doctor.
     */
    public static void openMainPage(Doctor doctor) {
        MainPage mainPage = new MainPage(doctor);
        mainPage.setVisible(true);
    }
}

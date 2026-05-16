package ui;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginFrame extends JFrame {

    JTextField usernameField;

    JPasswordField passwordField;

    JButton loginBtn;

    public LoginFrame() {

        setTitle("Railway Reservation Login");

        setSize(500,420);

        setLocationRelativeTo(null);

        setLayout(null);

        getContentPane().setBackground(
                new Color(240,248,255)
        );

        // HEADER PANEL

        JPanel header = new JPanel();

        header.setLayout(null);

        header.setBackground(
                new Color(0,51,102)
        );

        header.setBounds(0,0,500,90);

        add(header);

        JLabel heading =
                new JLabel("INDIAN RAILWAYS");

        heading.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        heading.setForeground(Color.WHITE);

        heading.setBounds(110,20,300,40);

        header.add(heading);

        // LOGIN TITLE

        JLabel title =
                new JLabel("User Login");

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        title.setForeground(
                new Color(0,51,102)
        );

        title.setBounds(170,120,200,40);

        add(title);

        // USERNAME

        JLabel userLabel =
                new JLabel("Username");

        userLabel.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        userLabel.setBounds(60,200,100,30);

        add(userLabel);

        usernameField = new JTextField();

        usernameField.setBounds(180,200,220,35);

        add(usernameField);

        // PASSWORD

        JLabel passLabel =
                new JLabel("Password");

        passLabel.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        passLabel.setBounds(60,260,100,30);

        add(passLabel);

        passwordField = new JPasswordField();

        passwordField.setBounds(180,260,220,35);

        add(passwordField);

        // LOGIN BUTTON

        loginBtn = new JButton("Login");

        loginBtn.setBounds(170,330,150,45);

        loginBtn.setBackground(
                new Color(0,51,102)
        );

        loginBtn.setForeground(Color.WHITE);

        loginBtn.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        add(loginBtn);

        loginBtn.addActionListener(e -> loginUser());

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
    }

    void loginUser() {

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(

                    "SELECT * FROM users WHERE username=? AND password=?"

            );

            ps.setString(
                    1,
                    usernameField.getText()
            );

            ps.setString(
                    2,
                    passwordField.getText()
            );

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                JOptionPane.showMessageDialog(

                        this,

                        "Login Successful"

                );

                dispose();

                new DashboardFrame(
                        usernameField.getText()
                );

            } else {

                JOptionPane.showMessageDialog(

                        this,

                        "Invalid Username or Password"

                );
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}
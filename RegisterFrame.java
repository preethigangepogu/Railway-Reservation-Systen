package ui;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterFrame extends JFrame {

    JTextField usernameField;

    JPasswordField passwordField;

    JButton registerBtn;

    public RegisterFrame() {

        setTitle("Railway Registration");

        setSize(500,430);

        setLocationRelativeTo(null);

        setLayout(null);

        getContentPane().setBackground(
                new Color(240,248,255)
        );

        // HEADER

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

        heading.setBounds(100,20,300,40);

        header.add(heading);

        // TITLE

        JLabel title =
                new JLabel("Create Account");

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        title.setForeground(
                new Color(0,51,102)
        );

        title.setBounds(145,120,250,40);

        add(title);

        // USERNAME

        JLabel user =
                new JLabel("Username");

        user.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        user.setBounds(60,210,100,30);

        add(user);

        usernameField = new JTextField();

        usernameField.setBounds(180,210,220,35);

        add(usernameField);

        // PASSWORD

        JLabel pass =
                new JLabel("Password");

        pass.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        pass.setBounds(60,270,100,30);

        add(pass);

        passwordField = new JPasswordField();

        passwordField.setBounds(180,270,220,35);

        add(passwordField);

        // REGISTER BUTTON

        registerBtn =
                new JButton("Register");

        registerBtn.setBounds(170,340,150,45);

        registerBtn.setBackground(
                new Color(255,140,0)
        );

        registerBtn.setForeground(Color.WHITE);

        registerBtn.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        add(registerBtn);

        registerBtn.addActionListener(e -> registerUser());

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
    }

    void registerUser() {

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(

                    "INSERT INTO users(username,password) VALUES(?,?)"

            );

            ps.setString(
                    1,
                    usernameField.getText()
            );

            ps.setString(
                    2,
                    passwordField.getText()
            );

            ps.executeUpdate();

            JOptionPane.showMessageDialog(

                    this,

                    "Registration Successful"

            );

            dispose();

            new LoginFrame();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}
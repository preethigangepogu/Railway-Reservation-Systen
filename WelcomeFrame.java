package ui;

import java.awt.*;
import javax.swing.*;

public class WelcomeFrame extends JFrame {

    public WelcomeFrame() {

        setTitle("Railway Reservation System");

        setSize(750,550);

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

        header.setBounds(0,0,750,110);

        add(header);

        JLabel heading =
                new JLabel(
                "INDIAN RAILWAY RESERVATION SYSTEM"
        );

        heading.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        heading.setForeground(Color.WHITE);

        heading.setBounds(80,30,600,40);

        header.add(heading);

        // SUBTITLE

        JLabel sub =
                new JLabel(
                "Travel Smart • Travel Safe"
        );

        sub.setFont(
                new Font("Arial", Font.PLAIN, 20)
        );

        sub.setForeground(
                new Color(0,51,102)
        );

        sub.setBounds(240,160,320,40);

        add(sub);

        // LOGIN BUTTON

        JButton loginBtn =
                new JButton("Login");

        loginBtn.setBounds(270,260,200,50);

        loginBtn.setBackground(
                new Color(59,130,246)
        );

        loginBtn.setForeground(Color.WHITE);

        loginBtn.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        add(loginBtn);

        // REGISTER BUTTON

        JButton registerBtn =
                new JButton("Register");

        registerBtn.setBounds(270,340,200,50);

        registerBtn.setBackground(
                new Color(255,140,0)
        );

        registerBtn.setForeground(Color.WHITE);

        registerBtn.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        add(registerBtn);

        // FOOTER

        JLabel footer =
                new JLabel(
                "Developed Using Java Swing & MySQL"
        );

        footer.setFont(
                new Font("Arial", Font.ITALIC, 14)
        );

        footer.setForeground(Color.GRAY);

        footer.setBounds(240,460,300,30);

        add(footer);

        // BUTTON ACTIONS

        loginBtn.addActionListener(e -> {

            dispose();

            new LoginFrame();

        });

        registerBtn.addActionListener(e -> {

            dispose();

            new RegisterFrame();

        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
    }
}
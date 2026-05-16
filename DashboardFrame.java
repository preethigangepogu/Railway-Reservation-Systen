package ui;

import java.awt.*;
import javax.swing.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame(String username) {

        setTitle("Indian Railway Dashboard");

        setSize(800,650);

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

        header.setBounds(0,0,800,100);

        add(header);

        JLabel heading = new JLabel(
                "INDIAN RAILWAY RESERVATION SYSTEM"
        );

        heading.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        heading.setForeground(Color.WHITE);

        heading.setBounds(130,25,550,40);

        header.add(heading);

        // WELCOME

        JLabel title = new JLabel(
                "Welcome, " + username
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        title.setForeground(
                new Color(0,51,102)
        );

        title.setBounds(270,130,300,40);

        add(title);

        // BOOK BUTTON

        JButton bookBtn =
                new JButton("Book Ticket");

        bookBtn.setBounds(280,210,220,50);

        bookBtn.setBackground(
                new Color(59,130,246)
        );

        bookBtn.setForeground(Color.WHITE);

        bookBtn.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        add(bookBtn);

        // CANCEL BUTTON

        JButton cancelBtn =
                new JButton("Cancel Ticket");

        cancelBtn.setBounds(280,290,220,50);

        cancelBtn.setBackground(
                new Color(239,68,68)
        );

        cancelBtn.setForeground(Color.WHITE);

        cancelBtn.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        add(cancelBtn);

        // AVAILABILITY BUTTON

        JButton availabilityBtn =
                new JButton("Check Availability");

        availabilityBtn.setBounds(280,370,220,50);

        availabilityBtn.setBackground(
                new Color(16,185,129)
        );

        availabilityBtn.setForeground(Color.WHITE);

        availabilityBtn.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        add(availabilityBtn);

        // LOGOUT BUTTON

        JButton logoutBtn =
                new JButton("Logout");

        logoutBtn.setBounds(280,450,220,50);

        logoutBtn.setBackground(Color.BLACK);

        logoutBtn.setForeground(Color.WHITE);

        logoutBtn.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        add(logoutBtn);

        // ACTIONS

        bookBtn.addActionListener(e -> {

            new BookingFrame(username);

        });

        cancelBtn.addActionListener(e -> {

            new CancelTicketFrame();

        });

        availabilityBtn.addActionListener(e -> {

            new AvailabilityFrame();

        });

        logoutBtn.addActionListener(e -> {

            dispose();

            new LoginFrame();

        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
    }
}
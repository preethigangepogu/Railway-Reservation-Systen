package ui;

import java.awt.*;
import javax.swing.*;

public class SplashScreen extends JFrame {

    public SplashScreen() {

        setTitle("Indian Railways");

        setSize(750,450);

        setLocationRelativeTo(null);

        setLayout(null);

        getContentPane().setBackground(
                new Color(0,51,102)
        );

        // TITLE

        JLabel title =
                new JLabel("INDIAN RAILWAYS");

        title.setFont(
                new Font("Arial", Font.BOLD, 40)
        );

        title.setForeground(Color.WHITE);

        title.setBounds(180,90,450,50);

        add(title);

        // SUBTITLE

        JLabel sub =
                new JLabel("Railway Reservation System");

        sub.setFont(
                new Font("Arial", Font.PLAIN, 24)
        );

        sub.setForeground(Color.ORANGE);

        sub.setBounds(190,160,400,40);

        add(sub);

        // FOOTER

        JLabel footer =
                new JLabel("Travel Smart • Travel Safe");

        footer.setFont(
                new Font("Arial", Font.ITALIC, 16)
        );

        footer.setForeground(Color.LIGHT_GRAY);

        footer.setBounds(250,220,250,30);

        add(footer);

        // PROGRESS BAR

        JProgressBar bar =
                new JProgressBar();

        bar.setBounds(170,310,400,28);

        bar.setStringPainted(true);

        add(bar);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);

        try {

            for(int i=0;i<=100;i++) {

                Thread.sleep(25);

                bar.setValue(i);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        dispose();

        new WelcomeFrame();
    }
}
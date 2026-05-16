package ui;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AvailabilityFrame extends JFrame {

    JComboBox<String> trainBox;

    JTextArea resultArea;

    JButton checkBtn;

    public AvailabilityFrame() {

        setTitle("Check Availability");

        setSize(550,450);

        setLocationRelativeTo(null);

        setLayout(null);

        getContentPane().setBackground(new Color(245,245,245));

        JLabel title = new JLabel("Train Availability");

        title.setFont(new Font("Arial", Font.BOLD, 24));

        title.setBounds(160,30,300,40);

        add(title);

        JLabel trainLabel =
                new JLabel("Select Train");

        trainLabel.setBounds(50,120,120,30);

        add(trainLabel);

        trainBox = new JComboBox<>();

        trainBox.setBounds(180,120,250,30);

        add(trainBox);

        loadTrains();

        checkBtn = new JButton("Check");

        checkBtn.setBounds(200,190,120,40);

        checkBtn.setBackground(new Color(0,102,204));

        checkBtn.setForeground(Color.WHITE);

        add(checkBtn);

        resultArea = new JTextArea();

        resultArea.setEditable(false);

        JScrollPane pane =
                new JScrollPane(resultArea);

        pane.setBounds(90,260,380,120);

        add(pane);

        checkBtn.addActionListener(e -> checkAvailability());

        setVisible(true);
    }

    void loadTrains() {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(

                    "SELECT train_name FROM trains"

            );

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                trainBox.addItem(
                        rs.getString("train_name")
                );
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    void checkAvailability() {

        try {

            Connection con = DBConnection.getConnection();

            String train =
                    trainBox.getSelectedItem().toString();

            PreparedStatement ps =
                    con.prepareStatement(

                    "SELECT * FROM trains WHERE train_name=?"

            );

            ps.setString(1, train);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                resultArea.setText(

                        "Train Name: "
                                + rs.getString("train_name")

                        + "\n\nSource: "
                                + rs.getString("source_station")

                        + "\n\nDestination: "
                                + rs.getString("destination_station")

                        + "\n\nAvailable Seats: "
                                + rs.getInt("available_seats")

                        + "\n\nFare: ₹"
                                + rs.getDouble("fare")

                );
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}
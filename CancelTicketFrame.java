package ui;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CancelTicketFrame extends JFrame {

    JTextField pnrField;

    JButton cancelBtn;

    public CancelTicketFrame() {

        setTitle("Cancel Ticket");

        setSize(500,350);

        setLocationRelativeTo(null);

        setLayout(null);

        getContentPane().setBackground(
                new Color(240,248,255)
        );

        JLabel title =
                new JLabel("Cancel Railway Ticket");

        title.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        title.setForeground(
                new Color(0,51,102)
        );

        title.setBounds(110,40,320,40);

        add(title);

        JLabel pnrLabel =
                new JLabel("Enter PNR Number");

        pnrLabel.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        pnrLabel.setBounds(50,130,160,30);

        add(pnrLabel);

        pnrField = new JTextField();

        pnrField.setBounds(220,130,180,35);

        add(pnrField);

        cancelBtn =
                new JButton("Cancel Ticket");

        cancelBtn.setBounds(160,220,170,45);

        cancelBtn.setBackground(
                new Color(220,38,38)
        );

        cancelBtn.setForeground(Color.WHITE);

        cancelBtn.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        add(cancelBtn);

        cancelBtn.addActionListener(e -> cancelTicket());

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
    }

    void cancelTicket() {

        try {

            Connection con =
                    DBConnection.getConnection();

            String pnr =
                    pnrField.getText();

            // CONFIRMATION

            int confirm =
                    JOptionPane.showConfirmDialog(

                    this,

                    "Are you sure you want to cancel ticket?",

                    "Confirm Cancellation",

                    JOptionPane.YES_NO_OPTION
            );

            if(confirm != JOptionPane.YES_OPTION) {

                return;
            }

            // FIND BOOKING

            PreparedStatement findBooking =
                    con.prepareStatement(

                    "SELECT * FROM bookings WHERE pnr_number=?"

            );

            findBooking.setString(1, pnr);

            ResultSet rs =
                    findBooking.executeQuery();

            if(rs.next()) {

                String train =
                        rs.getString("train_name");

                int seats =
                        rs.getInt("seat_number");

                // GET CURRENT SEATS

                PreparedStatement trainQuery =
                        con.prepareStatement(

                        "SELECT available_seats FROM trains WHERE train_name=?"

                );

                trainQuery.setString(1, train);

                ResultSet trainRs =
                        trainQuery.executeQuery();

                if(trainRs.next()) {

                    int currentSeats =
                            trainRs.getInt("available_seats");

                    int updatedSeats =
                            currentSeats + seats;

                    // RESTORE SEATS

                    PreparedStatement updateSeats =
                            con.prepareStatement(

                            "UPDATE trains SET available_seats=? WHERE train_name=?"

                    );

                    updateSeats.setInt(1, updatedSeats);

                    updateSeats.setString(2, train);

                    updateSeats.executeUpdate();
                }

                // DELETE BOOKING

                PreparedStatement deleteBooking =
                        con.prepareStatement(

                        "DELETE FROM bookings WHERE pnr_number=?"

                );

                deleteBooking.setString(1, pnr);

                deleteBooking.executeUpdate();

                JOptionPane.showMessageDialog(

                        this,

                        "Ticket Cancelled Successfully"

                );

                pnrField.setText("");

            } else {

                JOptionPane.showMessageDialog(

                        this,

                        "PNR Not Found"

                );
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}
package ui;
import com.toedter.calendar.JDateChooser;
import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Random;

public class BookingFrame extends JFrame {

    JComboBox<String> trainBox;
    JComboBox<String> genderBox;
    JComboBox<String> coachBox;

    JComboBox<String> sourceBox;
    JComboBox<String> destinationBox;

    JTextField seatsField;
    JTextField passengerField;
    JTextField ageField;
    JDateChooser dateChooser;

    JButton bookBtn;

    String username;

    public BookingFrame(String username) {

        this.username = username;

        setTitle("Ticket Booking");

        setSize(550,780);

        setLocationRelativeTo(null);

        setLayout(null);

        getContentPane().setBackground(
                new Color(240,248,255)
        );

        JLabel title =
                new JLabel("Railway Ticket Booking");

        title.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        title.setForeground(
                new Color(0,51,102)
        );

        title.setBounds(120,30,350,40);

        add(title);

        // SOURCE

        JLabel sourceLabel =
                new JLabel("Source");

        sourceLabel.setBounds(50,100,120,30);

        add(sourceLabel);

        sourceBox = new JComboBox<>();

        sourceBox.addItem("Hyderabad");

        sourceBox.addItem("Chennai");

        sourceBox.addItem("Mumbai");

        sourceBox.setBounds(200,100,220,30);

        add(sourceBox);

        // DESTINATION

        JLabel destLabel =
                new JLabel("Destination");

        destLabel.setBounds(50,160,120,30);

        add(destLabel);

        destinationBox = new JComboBox<>();

        destinationBox.addItem("Delhi");

        destinationBox.addItem("Bangalore");

        destinationBox.addItem("Kolkata");

        destinationBox.setBounds(200,160,220,30);

        add(destinationBox);

        // TRAIN

        JLabel trainLabel =
                new JLabel("Select Train");

        trainLabel.setBounds(50,220,120,30);

        add(trainLabel);

        trainBox = new JComboBox<>();

        trainBox.setBounds(200,220,220,30);

        add(trainBox);

        loadTrains();

        // JOURNEY DATE
        JLabel dateLabel =
        new JLabel("Journey Date");

dateLabel.setBounds(50,280,120,30);

add(dateLabel);

dateChooser = new JDateChooser();

dateChooser.setBounds(200,280,220,35);

dateChooser.setDateFormatString(
        "dd-MM-yyyy"
);

add(dateChooser);
        

        // SEATS

        JLabel seatsLabel =
                new JLabel("Seats");

        seatsLabel.setBounds(50,340,120,30);

        add(seatsLabel);

        seatsField = new JTextField();

        seatsField.setBounds(200,340,220,30);

        add(seatsField);

        // PASSENGER

        JLabel passengerLabel =
                new JLabel("Passenger Name");

        passengerLabel.setBounds(50,400,120,30);

        add(passengerLabel);

        passengerField = new JTextField();

        passengerField.setBounds(200,400,220,30);

        add(passengerField);

        // AGE

        JLabel ageLabel =
                new JLabel("Age");

        ageLabel.setBounds(50,460,120,30);

        add(ageLabel);

        ageField = new JTextField();

        ageField.setBounds(200,460,220,30);

        add(ageField);

        // GENDER

        JLabel genderLabel =
                new JLabel("Gender");

        genderLabel.setBounds(50,520,120,30);

        add(genderLabel);

        genderBox = new JComboBox<>();

        genderBox.addItem("Male");

        genderBox.addItem("Female");

        genderBox.addItem("Other");

        genderBox.setBounds(200,520,220,30);

        add(genderBox);

        // COACH TYPE

        JLabel coachLabel =
                new JLabel("Coach Type");

        coachLabel.setBounds(50,580,120,30);

        add(coachLabel);

        coachBox = new JComboBox<>();

        coachBox.addItem("Sleeper");

        coachBox.addItem("AC 3 Tier");

        coachBox.addItem("AC 2 Tier");

        coachBox.addItem("First Class");

        coachBox.addItem("General");

        coachBox.setBounds(200,580,220,30);

        add(coachBox);

        // BOOK BUTTON

        bookBtn = new JButton("Book Ticket");

        bookBtn.setBounds(180,660,160,45);

        bookBtn.setBackground(
                new Color(0,51,102)
        );

        bookBtn.setForeground(Color.WHITE);

        add(bookBtn);

        bookBtn.addActionListener(e -> bookTicket());

        setVisible(true);
    }

    void loadTrains() {

        try {

            Connection con =
                    DBConnection.getConnection();

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(

                    "SELECT train_name FROM trains"

            );

            while(rs.next()) {

                trainBox.addItem(

                        rs.getString("train_name")
                );
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    String generateSeat(String coach) {

        int num =
                new Random().nextInt(60) + 1;

        if(coach.equals("Sleeper")) {

            return "S1-" + num;
        }

        else if(coach.equals("AC 3 Tier")) {

            return "B2-" + num;
        }

        else if(coach.equals("AC 2 Tier")) {

            return "A1-" + num;
        }

        else if(coach.equals("First Class")) {

            return "H1-" + num;
        }

        return "GN-" + num;
    }

    void bookTicket() {

        try {

            Connection con =
                    DBConnection.getConnection();

            String train =
                    trainBox.getSelectedItem().toString();

            int seats =
                    Integer.parseInt(
                            seatsField.getText()
                    );

            PreparedStatement ps =
                    con.prepareStatement(

                    "SELECT * FROM trains WHERE train_name=?"

            );

            ps.setString(1, train);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                int available =
                        rs.getInt("available_seats");

                double fare =
                        rs.getDouble("fare");

                String coach =
                        coachBox.getSelectedItem().toString();

                // FARE MULTIPLIER

                if(coach.equals("AC 3 Tier")) {

                    fare = fare * 1.5;
                }

                else if(coach.equals("AC 2 Tier")) {

                    fare = fare * 2;
                }

                else if(coach.equals("First Class")) {

                    fare = fare * 3;
                }

                if(seats > available) {

                    JOptionPane.showMessageDialog(

                            this,

                            "Seats Not Available"

                    );

                    return;
                }

                int newSeats =
                        available - seats;

                PreparedStatement update =
                        con.prepareStatement(

                        "UPDATE trains SET available_seats=? WHERE train_name=?"

                );

                update.setInt(1, newSeats);

                update.setString(2, train);

                update.executeUpdate();

                String pnr =
                        "PNR" + new Random().nextInt(999999);

                String seatNo =
                        generateSeat(coach);

                // SAVE BOOKING

                PreparedStatement bookingInsert =
                        con.prepareStatement(

                        "INSERT INTO bookings " +

                        "(pnr_number, username, train_name, " +

                        "passenger_name, seat_number, age, gender, " +

                        "coach_type, journey_date, seat_code) " +

                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"

                );

                bookingInsert.setString(1, pnr);

                bookingInsert.setString(2, username);

                bookingInsert.setString(3, train);

                bookingInsert.setString(4,
                        passengerField.getText());

                bookingInsert.setInt(5, seats);

                bookingInsert.setInt(6,
                        Integer.parseInt(
                                ageField.getText()
                        ));

                bookingInsert.setString(7,
                        genderBox.getSelectedItem().toString());

                bookingInsert.setString(8, coach);

                bookingInsert.setString(

        9,

        ((JTextField)
        dateChooser.getDateEditor()
        .getUiComponent()).getText()

);

                bookingInsert.setString(10,
                        seatNo);

                bookingInsert.executeUpdate();

                JOptionPane.showMessageDialog(

                        this,

                        "Booking Successful\n\n" +

                        "PNR: " + pnr +

                        "\nTrain: " + train +

                        "\nCoach: " + coach +

                        "\nSeat Code: " + seatNo +

                        "\nJourney Date: " +
                        ((JTextField)
dateChooser.getDateEditor()
.getUiComponent()).getText() +

                        "\nSeats: " + seats +

                        "\nTotal Fare: ₹" +
                        (fare * seats)

                );
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}
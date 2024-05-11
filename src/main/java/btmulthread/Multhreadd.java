/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package btmulthread;

/**
 *
 * @author TAN TAI
 */
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;



    
public class Multhreadd extends JFrame {
    private JLabel clockLabel;
    private JTextField timezoneTextField;
    private JButton startButton;

    public Multhreadd() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Clock App");
        setSize(300, 200);
        setLayout(new FlowLayout());

        clockLabel = new JLabel();
        timezoneTextField = new JTextField(10);
        startButton = new JButton("Start");

        add(clockLabel);
        add(timezoneTextField);
        add(startButton);

        startButton.addActionListener(e -> startClock());

        setVisible(true);
    }

    private void startClock() {
        String timezone = timezoneTextField.getText();
        ClockThread clockThread = new ClockThread(timezone);
        clockThread.start();
    }

    private class ClockThread extends Thread {
        private String timezone;

        public ClockThread(String timezone) {
            this.timezone = timezone;
        }

        @Override
        public void run() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (true) {
                Date currentTime = new Date();
                dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
                String formattedTime = dateFormat.format(currentTime);
                SwingUtilities.invokeLater(() -> clockLabel.setText(formattedTime));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Multhreadd());
    }
}

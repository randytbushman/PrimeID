package assignment2;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;


/**
 * This class allows for the creation of a GUI that calculates the number of primes from a given start and end value.
 * The number of primes calculated is updated in real time and displays at the bottom of the screen. While by no means
 * gorgeous, this GUI demonstrates how to properly use parallel streams with cancellation.
 * @author Randolph Bushman
 * @version 11.18.2021
 */
public class FactorizerGUI
{
    private AtomicInteger tallyCount = new AtomicInteger();     // Number of primes found during computation
    private boolean cancelled = true;                           // If the GUI is canceled
    private JButton submit = new JButton("Submit");         // Submit button for computation
    private JButton cancel = new JButton("Cancel");         // Cancel computation
    private JTextField start = new JTextField();                // The starting value text field
    private JTextField end = new JTextField();                  // The ending value text field
    private JLabel tallyLabel = new JLabel();                   // The label that displays the current prime tally
    private JLabel errorLabel = new JLabel();                   // Error message that appears when input is incorrect


    public FactorizerGUI() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        // Set widget states
        start.setPreferredSize(new Dimension(200, 30));
        end.setPreferredSize(new Dimension(200, 30));
        start.setToolTipText("Start must be greater than or equal to 2.");
        end.setToolTipText("End must be greater than start.");
        start.setText("2");
        end.setText("100");
        submit.addActionListener(e -> runPrimeFinder());
        cancel.addActionListener(e -> cancelled = true);
        errorLabel.setForeground(Color.RED);

        // Set border and layout settings
        panel.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
        panel.setLayout(new GridLayout(0,2));

        // Add widgets to panel
        panel.add(new JLabel("Start:"));
        panel.add(start);
        panel.add(new JLabel("End:"));
        panel.add(end);
        panel.add(submit);
        panel.add(cancel);
        panel.add(tallyLabel);
        panel.add(errorLabel);

        // Prepare frame and set visible
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * This function computes all the primes between the specified start and end values and displays on the current
     * number on the GUI in real time. This method uses concurrent practices and can be cancelled.
     */
    public void runPrimeFinder() {

        int s = Integer.parseInt(start.getText());
        int e = Integer.parseInt(end.getText());

        if (s >= e) {
            errorLabel.setText("End cannot be greater than or equal to start.");
            return;
        }

        errorLabel.setText("");
        submit.setEnabled(false);
        cancelled = false;
        tallyCount.set(0);


        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                long start = System.nanoTime();
                IntStream.range(s, e).unordered().parallel().takeWhile(i -> !guiCancelled()).filter(PrimeFinder::isPrime).forEach(p -> incrementTally());
                System.out.println("Time elapsed: " + (System.nanoTime() - start)/100000 + "ms");
                submit.setEnabled(true);
                return null;
            }
        };
        worker.execute();
    }

    /**
     * This method returns the current state of the calculation, whether it was canceled.
     * @return if the current computation is cancelled
     */
    public boolean guiCancelled() {
        return cancelled;
    }

    /**
     * This method increments the tally and updates the label on the GUI.
     */
    private void incrementTally() {
        tallyCount.incrementAndGet();
        tallyLabel.setText("Primes: " + tallyCount.toString());
    }
}

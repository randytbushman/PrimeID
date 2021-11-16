package assignment1;

import javax.swing.*;
import java.awt.*;

public class FactorizerGUI
{
    private int primeTally = 0;
    private JButton submit;
    private JButton cancel;
    private JTextField start;
    private JTextField end;
    private JTextField threadCount;
    JLabel tally;
    private String defaultTallyLabel = "Primes computed: ";


    public FactorizerGUI() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        // Widget instantiation
        start = new JTextField();
        end = new JTextField();
        submit = new JButton("Submit");
        cancel = new JButton("Cancel");
        threadCount = new JTextField();
        tally = new JLabel(defaultTallyLabel);

        // Set widget states
        start.setPreferredSize(new Dimension(200, 30));
        end.setPreferredSize(new Dimension(200, 30));
        start.setToolTipText("Start must be greater than or equal to 2.");
        end.setToolTipText("End must be greater than start.");
        start.setText("2");
        end.setText("100");
        submit.addActionListener(e -> runPrimeThing());
        cancel.addActionListener(e -> cancelPrimeThing());
        threadCount.setText(String.valueOf(Runtime.getRuntime().availableProcessors() + 1));

        // Set border and layout settings
        panel.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
        panel.setLayout(new GridLayout(0,2));

        // Add widgets to panel
        panel.add(new JLabel("Start:"));
        panel.add(start);
        panel.add(new JLabel("End:"));
        panel.add(end);
        panel.add(new JLabel("Thread Count:"));
        panel.add(threadCount);
        panel.add(submit);
        panel.add(cancel);
        panel.add(tally);

        // Prepare frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void runPrimeThing() {
        submit.setEnabled(false);
        primeTally = 0;
        System.out.println(Integer.parseInt(start.getText()));
        tally.setText(defaultTallyLabel + ++primeTally);

        System.out.println(Integer.parseInt(end.getText()));
        submit.setEnabled(true);
    }

    public void cancelPrimeThing() {
        System.out.println("Canceling");
    }
}

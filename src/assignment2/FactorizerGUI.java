package assignment2;

import javax.swing.*;
import java.awt.*;
import java.util.stream.IntStream;

public class FactorizerGUI
{
    private int primeTally = 0;
    private JButton submit;
    private JButton cancel;
    private JTextField start;
    private JTextField end;
    private SwingWorker<Void, Void> worker;


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
        panel.add(tally);

        // Prepare frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void runPrimeThing() {

        submit.setEnabled(false);

        int s = Integer.parseInt(start.getText());
        int e = Integer.parseInt(end.getText());
        worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                IntStream.range(s, e).parallel().filter(PrimeFinder::isPrime).count();
                return null;
            }

            @Override
            protected void done() {
                try {
                    submit.setEnabled(true);
                } catch (Exception e) {
                }
            }
        };
        worker.execute();

        //tally.setText(defaultTallyLabel + IntStream.range(s, e).parallel().filter(PrimeFinder::isPrime).count());
        //System.out.println("Time elapsed: " + (System.nanoTime() - start) + "ns");
        //submit.setEnabled(true);
    }

    public void cancelPrimeThing() {
        worker.cancel(true);
        submit.setEnabled(true);
    }
}

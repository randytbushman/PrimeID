package assignment2;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class FactorizerGUI
{

    private AtomicInteger primeTally;

    private boolean canSubmit;
    private boolean cancelled;
    private JButton submit;
    private JButton cancel;
    private JTextField start;
    private JTextField end;
    private SwingWorker<Void, Void> worker;


    JLabel tally;



    public FactorizerGUI() {
        primeTally = new AtomicInteger();
        cancelled = false;
        canSubmit = true;
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        // Widget instantiation
        start = new JTextField();
        end = new JTextField();
        submit = new JButton("Submit");
        cancel = new JButton("Cancel");
        tally = new JLabel();

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
        cancelled = false;
        primeTally.set(0);
        int s = Integer.parseInt(start.getText());
        int e = Integer.parseInt(end.getText());
        worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                canSubmit = false;
                IntStream.range(s, e).parallel().filter(PrimeFinder::isPrime).takeWhile(i -> !guiCancelled()).forEach(p -> updateTally());
                canSubmit = true;
                return null;
            }

            @Override
            protected void done() {
                try {
                    while(!canSubmit)
                        continue;
                    submit.setEnabled(true);

                } catch (Exception e) {
                    System.err.println("asdf");
                }
            }
        };
        worker.execute();

    }

    public boolean guiCancelled() {
        return cancelled;
    }

    public void updateTally() {
        primeTally.incrementAndGet();
        tally.setText("Primes: " + primeTally.toString());
    }


    public void cancelPrimeThing() {
        cancelled = true;
        worker.cancel(true);
        submit.setEnabled(true);
        cancelled = false;
    }
}

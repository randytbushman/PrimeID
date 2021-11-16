package assignment1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FactorizerGUI
{
    private int primeTally = 0;
    private JTextField start;
    private JTextField end;


    public FactorizerGUI() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        start = new JTextField();
        end = new JTextField();
        JButton submit = new JButton("Submit");
        JButton cancel = new JButton("Cancel");
        JLabel tally = new JLabel("Primes computed: ");


        start.setPreferredSize(new Dimension(100, 30));
        end.setPreferredSize(new Dimension(100, 30));
        submit.addActionListener(e -> runPrimeThing());
        cancel.addActionListener(e -> cancelPrimeThing());

        panel.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
        panel.setLayout(new GridLayout(0,2));

        panel.add(new JLabel("Start:"));
        panel.add(start);
        panel.add(new JLabel("End:"));
        panel.add(end);
        panel.add(submit);
        panel.add(cancel);
        panel.add(tally);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void runPrimeThing() {
        System.out.println(Integer.parseInt(start.getText()));
        System.out.println(Integer.parseInt(end.getText()));
    }

    public void cancelPrimeThing() {
        System.out.println("Canceling");
    }
}

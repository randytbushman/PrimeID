package assignment1;

import javax.swing.*;
import java.awt.*;

public class FactorizerGUI
{
    public FactorizerGUI() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();


        panel.setBorder(BorderFactory.createEmptyBorder(500,500,500,500));
        panel.setLayout(new GridLayout());

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }
}

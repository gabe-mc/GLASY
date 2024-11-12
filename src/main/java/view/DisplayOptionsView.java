package view;

import javax.swing.*;
import java.awt.*;

public class DisplayOptionsView extends GeneralView {

    public DisplayOptionsView() {
        // Add in the continue button
        JButton startButton = new JButton("Continue");
        startButton.setFont(getMontserratFont());
        startButton.setForeground(new Color(82, 121, 111));
        startButton.setBackground(new Color(202, 210, 197));
        startButton.setBounds((getBackgroundImage().getIconWidth() - 200) / 2 - 50, 600, 300, 40);
        getBackgroundLabel().add(startButton);
    }

    public static void main(String[] args) {
        DisplayOptionsView displayOptionsView = new DisplayOptionsView();
        displayOptionsView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        displayOptionsView.setVisible(true);
        displayOptionsView.setResizable(false);
    }
}

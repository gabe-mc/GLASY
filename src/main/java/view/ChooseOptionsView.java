package view;

import javax.swing.*;
import java.awt.*;

public class ChooseOptionsView extends GeneralView {

    public ChooseOptionsView() {
        // Add in the continue button
        JButton startButton = new JButton("Continue");
        startButton.setFont(getMontserratFont());
        startButton.setForeground(new Color(82, 121, 111));
        startButton.setBackground(new Color(202, 210, 197));
        startButton.setBounds((getBackgroundImage().getIconWidth() - 200) / 2 - 50, 600, 300, 40);
        getBackgroundLabel().add(startButton);
    }

    public static void main(String[] args) {
        ChooseOptionsView chooseOptionsView = new ChooseOptionsView();
        chooseOptionsView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chooseOptionsView.setVisible(true);
        chooseOptionsView.setResizable(false);
    }
}

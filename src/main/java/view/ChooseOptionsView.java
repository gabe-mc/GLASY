package view;

import javax.swing.*;
import java.awt.*;

public class ChooseOptionsView extends GeneralView {
    JPanel panel;

    public ChooseOptionsView() {

        // Add in the instruction text at the top
        JLabel instructionsLabel = new JLabel("Please choose your desired preferences below, and we will plan" +
                " your day for you.", JLabel.CENTER);
        instructionsLabel.setFont(getMontserratBoldItalicFont());
        instructionsLabel.setForeground(new Color(47, 62, 70));
        instructionsLabel.setBounds(0, 40, getBackgroundImage().getIconWidth(), 30);
        getBackgroundLabel().add(instructionsLabel);

        // Everything below this comment will be the input a user can give
        JLabel radiusLabel = new JLabel("Radius (km):", JLabel.LEFT);
        radiusLabel.setFont(getMontserratFont());
        radiusLabel.setForeground(new Color(47, 62, 70));
        radiusLabel.setBounds(95, 100, getBackgroundImage().getIconWidth(), 30);
        getBackgroundLabel().add(radiusLabel);

        JPanel radiusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JSlider radiusSlider = new JSlider(0, 50, 25);
        radiusSlider.setMajorTickSpacing(10);
        radiusSlider.setMinorTickSpacing(1);
        radiusSlider.setPaintTicks(true);
        radiusSlider.setPaintLabels(true);
        radiusLabel.setBounds(150, 100, 300, 40);
        getBackgroundLabel().add(radiusSlider);

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
        chooseOptionsView.getMainFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chooseOptionsView.getMainFrame().setVisible(true);
        chooseOptionsView.getMainFrame().setResizable(false);
    }
}

package view;

import entity.CommonLocationData;

import javax.swing.*;
import java.awt.*;

public class DisplayResultsView {
    private ImageIcon bkgImage;
    private JLabel backgroundLabel;
    private JFrame mainFrame;

    public DisplayResultsView() {
        LoadFonts loadFonts = new LoadFonts();

        // Initialize the image
        this.bkgImage = new ImageIcon("src/main/java/view/images/BackgroundImage2.png");

        // Make the frame
        this.mainFrame = new JFrame();

        // Set the background image
        this.backgroundLabel = new JLabel(this.bkgImage);

        this.mainFrame.add(this.backgroundLabel, BorderLayout.CENTER);
        this.mainFrame.setSize(this.bkgImage.getIconWidth() + 10, this.bkgImage.getIconHeight() + 30);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - bkgImage.getIconWidth()) / 2;
        int y = (screenSize.height - bkgImage.getIconHeight()) / 2;
        this.mainFrame.setLocation(x, y);

        // Results Checkboxes
        JPanel resultsCheckboxes = new JPanel();
        //CommonLocationData[] = {new CommonLocationData("1","2","3","4","5","6","7"),new CommonLocationData("1","2","3","4","5","6","7")};
        String[] possibleLocations = {"a","b","c","d","e","f","g","h","i","j","k","sdfs","ghgh","aaaa","mellow","okayd","sk"};
        for(String possibleLocation : possibleLocations) {
            JCheckBox checkBox = new JCheckBox(possibleLocation);
            resultsCheckboxes.add(checkBox);
        }
        JScrollPane scrollCheckboxes = new JScrollPane(resultsCheckboxes);
        this.backgroundLabel.add(scrollCheckboxes);

        // Add in the continue button
        JButton startButton = new JButton("Continue");
        startButton.setFont(loadFonts.montserratFont);
        startButton.setForeground(new Color(82, 121, 111));
        startButton.setBackground(new Color(202, 210, 197));
        startButton.setBounds((this.bkgImage.getIconWidth() - 200) / 2 - 50, 600, 300, 40);
        this.backgroundLabel.add(startButton);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setVisible(true);
        this.mainFrame.setResizable(false);

        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setVisible(true);
        this.mainFrame.setResizable(false);
    }

    public static void main(String[] args) {
        DisplayResultsView displayResultsView = new DisplayResultsView();
    }
}

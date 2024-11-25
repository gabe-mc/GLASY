package view;

import entity.CommonLocationData;

import javax.swing.*;
import java.awt.*;

public class DisplayResultsView extends JFrame {
    private ImageIcon bkgImage;
    private JLabel backgroundLabel;

    public DisplayResultsView() {
        LoadFonts loadFonts = new LoadFonts();

        // Initialize the image
        this.bkgImage = new ImageIcon("src/main/java/view/images/BackgroundImage2.png");

        // Set the background image
        this.backgroundLabel = new JLabel(this.bkgImage);

        add(this.backgroundLabel, BorderLayout.CENTER);
        setSize(this.bkgImage.getIconWidth() + 10, this.bkgImage.getIconHeight() + 30);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - bkgImage.getIconWidth()) / 2;
        int y = (screenSize.height - bkgImage.getIconHeight()) / 2;
        setLocation(x, y);

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        DisplayResultsView displayResultsView = new DisplayResultsView();
    }
}

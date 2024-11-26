package view;

import interface_adapter.choose_options.ChooseOptionsState;
import interface_adapter.display_options.DisplayOptionsController;
import interface_adapter.display_options.DisplayOptionsState;
import interface_adapter.display_options.DisplayOptionsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DisplayOptionsView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "display options";

    private ImageIcon bkgImage;
    private JLabel backgroundLabel;
    private final DisplayOptionsViewModel displayOptionsViewModel;
    private DisplayOptionsController displayOptionsController;
    public DisplayOptionsView(DisplayOptionsViewModel displayOptionsViewModel) {
        this.displayOptionsViewModel = displayOptionsViewModel;
        this.displayOptionsViewModel.addPropertyChangeListener(this);
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
        startButton.setBounds(this.bkgImage.getIconWidth() - 300, 600, 200, 40);
        this.backgroundLabel.add(startButton);

        startButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(startButton)) {
                            displayOptionsController.execute();
                        }
                    }
                }
        );

        // Add in the back button
        JButton backButton = new JButton("Back");
        backButton.setFont(loadFonts.montserratFont);
        backButton.setForeground(new Color(82, 121, 111));
        backButton.setBackground(new Color(202, 210, 197));
        backButton.setBounds(90, 600, 140, 40);
        this.backgroundLabel.add(backButton);

//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
//        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final DisplayOptionsState state = (DisplayOptionsState) evt.getNewValue();
        }

    }
    public String getViewName() { return viewName; }
}

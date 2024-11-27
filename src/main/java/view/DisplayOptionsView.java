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
import java.util.ArrayList;
import java.util.List;

public class DisplayOptionsView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "display options";
    final DisplayOptionsState displayOptionsState;

    private ImageIcon bkgImage;
    private JLabel backgroundLabel;
    private final DisplayOptionsViewModel displayOptionsViewModel;
    private DisplayOptionsController displayOptionsController;
    public DisplayOptionsView(DisplayOptionsViewModel displayOptionsViewModel) {
        this.displayOptionsViewModel = displayOptionsViewModel;
        this.displayOptionsViewModel.addPropertyChangeListener(this);
        LoadFonts loadFonts = new LoadFonts();

        displayOptionsState = displayOptionsViewModel.getState();

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
        List<String> possibleLocations = new ArrayList<>();
        possibleLocations.add("Hello");
        possibleLocations.add("My name");
        possibleLocations.add("Please help me");
//        {"a","b","c","d","e","f","g","h","i","j","k","sdfs"};
        System.out.println(possibleLocations);
        for (String possibleLocation : possibleLocations) {
            JCheckBox checkBox = new JCheckBox(possibleLocation);
            resultsCheckboxes.add(checkBox);
        }
        JScrollPane scrollCheckboxes = new JScrollPane(resultsCheckboxes);
        resultsCheckboxes.setFont(loadFonts.montserratFontSmall);
        scrollCheckboxes.setBounds(50, 50, this.bkgImage.getIconWidth() - 100, this.bkgImage.getIconHeight() - 200);
        resultsCheckboxes.setLayout(new BoxLayout(resultsCheckboxes, BoxLayout.Y_AXIS));
        this.backgroundLabel.add(scrollCheckboxes);

        // Add in the continue button
        JButton startButton = new JButton("Continue");
        startButton.setFont(loadFonts.montserratFont);
        startButton.setForeground(new Color(82, 121, 111));
        startButton.setBackground(new Color(202, 210, 197));
        startButton.setBounds((this.bkgImage.getIconWidth() - 200) / 2 - 50, 600, 300, 40);
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

//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//        setResizable(false);
    }

    /**
     * The previous view will pass the list of location objects into the DisplayOptionsState
     * The propertyChange function should call the Google Maps API
     *
     */

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

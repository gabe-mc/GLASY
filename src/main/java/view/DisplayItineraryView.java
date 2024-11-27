package view;

import interface_adapter.display_itinerary_view.DisplayItineraryController;
import interface_adapter.display_itinerary_view.DisplayItineraryState;
import interface_adapter.display_itinerary_view.DisplayItineraryViewModel;
import interface_adapter.splash_screen_view.SplashScreenController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DisplayItineraryView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "display results";
    private ImageIcon bkgImage;
    private JLabel backgroundLabel;
    private final DisplayItineraryViewModel displayItineraryViewModel;
    private DisplayItineraryController displayItineraryController;

    public DisplayItineraryView(DisplayItineraryViewModel displayItineraryViewModel) {
        this.displayItineraryViewModel = displayItineraryViewModel;
        this.displayItineraryViewModel.addPropertyChangeListener(this);
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

        // Add in the back button
        JButton backButton = new JButton("Back");
        backButton.setFont(loadFonts.montserratFont);
        backButton.setForeground(new Color(82, 121, 111));
        backButton.setBackground(new Color(202, 210, 197));
        backButton.setBounds(90, 600, 140, 40);
        this.backgroundLabel.add(backButton);

        // Add in the Save button
        JButton saveButton = new JButton("Save");
        saveButton.setFont(loadFonts.montserratFont);
        saveButton.setForeground(new Color(82, 121, 111));
        saveButton.setBackground(new Color(202, 210, 197));
        saveButton.setBounds(this.bkgImage.getIconWidth() - 240, 600, 140, 40);
        this.backgroundLabel.add(saveButton);

        saveButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(saveButton)) {
                            displayItineraryController.execute();
                        }
                    }
                }
        );

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")){
            final DisplayItineraryState state = (DisplayItineraryState) evt.getNewValue();
        }
    }
    public String getViewName() {
        return viewName;
    }

    public void setDisplayItineraryController(DisplayItineraryController displayItineraryController) {
        this.displayItineraryController = displayItineraryController;
    }

}

package view;

import interface_adapter.display_itinerary_view.DisplayItineraryController;
import interface_adapter.display_itinerary_view.DisplayItineraryState;
import interface_adapter.display_itinerary_view.DisplayItineraryViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DisplayItineraryView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "display results";
    private final DisplayItineraryViewModel displayItineraryViewModel;
    private DisplayItineraryController displayItineraryController;

    private ImageIcon bkgImage;
    private JLabel backgroundLabel;

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

        JFXPanel jfxPanel = new JFXPanel();
        add(jfxPanel, BorderLayout.CENTER);

        // Initialize JavaFX components
        Platform.runLater(() -> {
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();

            // Load Google Maps (you will need an API key for this to work)
            String googleMapsUrl = "https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&callback=initMap";
            webEngine.load(googleMapsUrl);

            // Create the JavaFX scene and add the WebView
            jfxPanel.setScene(new Scene(webView));
        });

        // Add in the back button
        JButton backButton = new JButton("Back");
        backButton.setFont(loadFonts.montserratFont);
        backButton.setForeground(new Color(82, 121, 111));
        backButton.setBackground(new Color(202, 210, 197));
        backButton.setBounds(90, 600, 140, 40);
        this.backgroundLabel.add(backButton);

        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(backButton)) {
                            displayItineraryController.switchToPreviousView();
                        }
                    }
                }
        );
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

    public void setDisplayItineraryController(DisplayItineraryController displayItineraryController) {
        this.displayItineraryController = displayItineraryController;
    }

    public String getViewName() {
        return viewName;
    }

}

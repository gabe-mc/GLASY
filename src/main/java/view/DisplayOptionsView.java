package view;

import entity.AttractionData;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DisplayOptionsView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "display options";
    private final DisplayOptionsViewModel displayOptionsViewModel;
    private DisplayOptionsController displayOptionsController = new DisplayOptionsController();

    private ImageIcon bkgImage;
    private JLabel backgroundLabel;

    private final JPanel resultsCheckboxes = new JPanel();
    private final JLabel errorLabel = new JLabel();

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

        JScrollPane scrollCheckboxes = new JScrollPane(resultsCheckboxes);
        scrollCheckboxes.getVerticalScrollBar().setUnitIncrement(16);
        resultsCheckboxes.setFont(loadFonts.montserratFontSmall);
        scrollCheckboxes.setBounds(50, 50, this.bkgImage.getIconWidth() - 100, this.bkgImage.getIconHeight() - 200);
        resultsCheckboxes.setLayout(new BoxLayout(resultsCheckboxes, BoxLayout.Y_AXIS));
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
                            final DisplayOptionsState currentState = displayOptionsViewModel.getState();
                            displayOptionsController.execute(currentState.getCheckedLocationList());
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

        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(backButton)) {
                            displayOptionsController.switchToPreviousView();
                        }
                    }
                }
        );

        // Add in the error label
        errorLabel.setFont(loadFonts.montserratFont);
        errorLabel.setForeground(new Color(82, 121, 111));
        errorLabel.setBounds(90, 650, this.bkgImage.getIconWidth() - 190, 40);
        errorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.backgroundLabel.add(errorLabel);

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

    private String checkBoxBuilder(String name, String type, String address, String price, String rating, String imageUrl) {
        if (Objects.equals(price, "0")){
            price = "";
        } else {
            price = "$".repeat(Integer.parseInt(price)) + " - ";
        }

        rating = rating + "/10 stars";

        final StringBuilder cssStyle = new StringBuilder();
        cssStyle.append("<html>");
        cssStyle.append("<style>");
        cssStyle.append("body { font-family: 'Montserrat', sans-serif; padding: 20px; }");
        cssStyle.append("h1 { color: #52796F; font-size: 14px; }");
        cssStyle.append("h2 { margin-top: 0; margin-bottom: 5px; font-size: 10px; }");
        cssStyle.append("h3 { color: #52796F; margin-bottom: 5px; }");
        cssStyle.append("p { color: #52796F; margin-top: 0; }");
        cssStyle.append(".item { margin-bottom: 20px; border-left: 4px solid #52796F; padding-left: 10px; }");
        cssStyle.append("</style>");

        return  cssStyle +
                "<div class='item'>" +
                        "<img src='" + imageUrl + "' width='120' height='120' />" +  // Image on the left
                        "<div class='text'>" +
                        "<h1>" + name + "</h1>" +
                        "<h2>" + type + " - " + price + rating + "</h2>" +
                        "<p>" + address + "</p>" +
                        "</div>" +
                "</div>" +
                "</html>";
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final DisplayOptionsState state = (DisplayOptionsState) evt.getNewValue();

            resultsCheckboxes.removeAll();
            List<AttractionData> locations = new ArrayList<>();
            List<JCheckBox> checkBoxes = new ArrayList<>();
            for (AttractionData possibleLocation : state.getCheckedLocationList().keySet()) {
                JCheckBox checkBox = new JCheckBox(checkBoxBuilder(
                        possibleLocation.getName(),
                        possibleLocation.getCategories().get(0),
                        possibleLocation.getAddress(),
                        String.valueOf(possibleLocation.getPrice()),
                        String.valueOf(possibleLocation.getRating()),
                        ""
//                        possibleLocation.getPhotoUrl()
                ));

                resultsCheckboxes.add(checkBox);
                locations.add(possibleLocation);
                checkBoxes.add(checkBox);

                // Sample code for checkbox listeners
                checkBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JCheckBox source = (JCheckBox) e.getSource();
                        state.setCheckedLocation(possibleLocation, source.isSelected());
                    }
                });

//                loadImageAsync(checkBox, possibleLocation);
            }
            Timer imageLoadTimer = new Timer(100, new ActionListener() {
                int currentIndex = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    // If all images are loaded, stop the Timer
                    if (currentIndex >= locations.size()) {
                        ((Timer) e.getSource()).stop();
                        System.out.println("Done loading images");
                        return;
                    }

                    // Get the current location and checkbox to load the image
                    AttractionData possibleLocation = locations.get(currentIndex);
                    JCheckBox checkBox = checkBoxes.get(currentIndex);

                    // Load the image for the current location
                    loadImageAsync(checkBox, possibleLocation);

                    // Increment the index to load the next image in the next cycle
                    currentIndex++;
                }
            });

            imageLoadTimer.start();

            errorLabel.setText(state.getErrorText());
        }
    }

    private void loadImageAsync(JCheckBox checkBox, AttractionData possibleLocation) {
        SwingWorker<ImageIcon, Void> worker = new SwingWorker<ImageIcon, Void>() {
            @Override
            protected ImageIcon doInBackground() {
                String imageUrl = possibleLocation.getPhotoUrl();
                if (imageUrl == null || imageUrl.trim().isEmpty()) {
                    return null;
                }
                try {
                    return new ImageIcon(new URL(imageUrl));
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void done() {
                try {
                    ImageIcon image = get();  // Get the loaded image
                    if (image != null) {
                        // Update the checkbox with the loaded image
                        String htmlContent = checkBoxBuilder(
                                possibleLocation.getName(),
                                possibleLocation.getCategories().get(0),
                                possibleLocation.getAddress(),
                                String.valueOf(possibleLocation.getPrice()),
                                String.valueOf(possibleLocation.getRating()),
                                image.getDescription()
                        );
                        checkBox.setText(htmlContent);  // Set the HTML content with the image
                    } else {
                        // If the image failed to load, just update with no image
                        String htmlContent = checkBoxBuilder(
                                possibleLocation.getName(),
                                possibleLocation.getCategories().get(0),
                                possibleLocation.getAddress(),
                                String.valueOf(possibleLocation.getPrice()),
                                String.valueOf(possibleLocation.getRating()),
                                ""  // No image URL
                        );
                        checkBox.setText(htmlContent);  // Set the HTML content without the image
                    }
                } catch (Exception e) {
                    e.printStackTrace();  // Handle exceptions if necessary
                }
            }
        };

        worker.execute();  // Execute the SwingWorker in the background
    }

    public DisplayOptionsController getDisplayOptionsController() {
        return displayOptionsController;
    }

    public String getViewName() { return viewName; }
}

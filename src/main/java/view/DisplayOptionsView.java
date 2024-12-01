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

    public String checkBoxBuilder(String name, String type, String address, String price, String rating, String imageUrl) {
        if (Objects.equals(price, "0")){
            price = "";
        } else {
            price = "$".repeat(Integer.parseInt(price)) + " - ";
        }

        rating = rating + "/10 stars";

        final StringBuilder cssStyle = new StringBuilder();
        cssStyle.append("<html>");
        cssStyle.append("<link href='https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap' rel='stylesheet'>");
        cssStyle.append("<style>");
        cssStyle.append("img { float: left; margin-right: 10px; }");
        cssStyle.append("body { font-family: 'Montserrat', sans-serif; padding: 20px; }");
        cssStyle.append("h1 { color: #52796F; font-size: 14px; }");
        cssStyle.append("h2 { margin-top: 0; margin-bottom: 5px; font-size: 10px; }");
        cssStyle.append("h3 { color: #52796F; margin-bottom: 5px; }");
        cssStyle.append("p { color: #52796F; margin-top: 0; }");
        cssStyle.append(".item { margin-bottom: 20px; border-left: 4px solid #52796F; padding-left: 10px; }");
        cssStyle.append("</style>");

        return  cssStyle +
                "<div style='border-left: 4px solid #52796F; padding-left: 10px;'>" +
                "<div class='parent'>" +
                    "<div class='child'>" +
                    "<img src='" + imageUrl + "' width='120' height='120' style='margin-left: 10px;'/>" +
                    "</div>" +
                    "<div class='child'>" +
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
            for (AttractionData possibleLocation : state.getCheckedLocationList().keySet()) {
                JCheckBox checkBox = new JCheckBox(checkBoxBuilder(
                        possibleLocation.getName(),
                        possibleLocation.getCategories().get(0),
                        possibleLocation.getAddress(),
                        String.valueOf(possibleLocation.getPrice()),
                        String.valueOf(possibleLocation.getRating()),
                        possibleLocation.getPhotoUrl()
                ));

//                JPanel container = new JPanel();
//                container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
//                JPanel panelOne = new JPanel();
//                JPanel panelTwo = new JPanel();
//
//                panelTwo.setLayout(new BorderLayout()); // Set layout for the panel
//
//                String html = "<html>" + "<img src='" + possibleLocation.getPhotoUrl() + "' width='150' height='150'/>" + "</html>";
//
//                // Add the HTML to a JLabel
//                JLabel htmlLabel = new JLabel(html);
//                htmlLabel.setHorizontalAlignment(SwingConstants.CENTER);
//
//                panelOne.add(htmlLabel);
//                panelTwo.add(checkBox);
//
//
//                container.setLayout(new GridLayout(1,2));
//                container.add(panelOne, BorderLayout.WEST);
//                container.add(panelTwo, BorderLayout.CENTER);

                resultsCheckboxes.add(checkBox);

                // Sample code for checkbox listeners
                checkBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JCheckBox source = (JCheckBox) e.getSource();
                        state.setCheckedLocation(possibleLocation, source.isSelected());
                    }
                });
            }

            errorLabel.setText(state.getErrorText());
        }
    }

    public DisplayOptionsController getDisplayOptionsController() {
        return displayOptionsController;
    }

    public String getViewName() { return viewName; }
}

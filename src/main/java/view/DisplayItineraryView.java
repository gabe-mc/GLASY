package view;

import entity.AttractionData;
import interface_adapter.display_itinerary_view.DisplayItineraryController;
import interface_adapter.display_itinerary_view.DisplayItineraryState;
import interface_adapter.display_itinerary_view.DisplayItineraryViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayItineraryView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "display results";
    private final DisplayItineraryViewModel displayItineraryViewModel;
    private DisplayItineraryController displayItineraryController;

    private ImageIcon bkgImage;
    private JLabel backgroundLabel;

    private final JEditorPane itineraryPane = new JEditorPane();

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

        JScrollPane scrollPane = new JScrollPane(itineraryPane);
        scrollPane.setBounds(50, 50, this.bkgImage.getIconWidth() - 100, this.bkgImage.getIconHeight() - 200);
        this.backgroundLabel.add(scrollPane);

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

        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(backButton)) {
                            displayItineraryController.switchToPreviousView();
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

            // Adding in the itinerary
            itineraryPane.setContentType("text/html");
            itineraryPane.setEditable(false);

            List<String[]> items = new ArrayList<String[]>();

            for (AttractionData info : state.getShortestPath()) {
                items.add(new String[]{info.getVisitTime(), info.getName(), info.getAddress()});
            }

            // Generate HTML content
            StringBuilder html = new StringBuilder();
            html.append("<html><head>");
            html.append("<link href='https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap' rel='stylesheet'>");
            html.append("<style>");
            html.append("body { font-family: 'Montserrat', sans-serif; padding: 20px; }");
            html.append("h1 { color: #2F3E46; }");
            html.append("h2 { margin-top: 0; margin-bottom: 5px; }");
            html.append("h3 { color: #52796F; margin-bottom: 5px; }");
            html.append("p { color: #52796F; margin-top: 0; }");
            html.append(".item { margin-bottom: 20px; border-left: 4px solid #3498db; padding-left: 10px; }");
            html.append("</style>");
            html.append("</head><body>");
            html.append("<h1>Your Itinerary</h1>");

            for (String[] item : items) {
                html.append("<div class='item'>");
                html.append("<h3>").append(item[0]).append("</h3>");
                html.append("<h2>").append(item[1]).append("</h2>");
                html.append("<p>").append(item[2]).append("</p>");
                html.append("</div>");
            }

            html.append("</body></html>");

            // Set the HTML content to the editor pane
            itineraryPane.setText(html.toString());
        }
    }
    public String getViewName() {
        return viewName;
    }

    public void setDisplayItineraryController(DisplayItineraryController displayItineraryController) {
        this.displayItineraryController = displayItineraryController;
    }

}

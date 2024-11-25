package view;

import entity.CommonLocationData;
import interface_adapter.display_results_view.DisplayResultsController;
import interface_adapter.display_results_view.DisplayResultsState;
import interface_adapter.display_results_view.DisplayResultsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DisplayResultsView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "display results";
    private ImageIcon bkgImage;
    private JLabel backgroundLabel;
    private final DisplayResultsViewModel displayResultsViewModel;
    private DisplayResultsController displayResultsController;

    public DisplayResultsView(DisplayResultsViewModel displayResultsViewModel) {
        this.displayResultsViewModel = displayResultsViewModel;
        this.displayResultsViewModel.addPropertyChangeListener(this);
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

        // Add in the continue button
        JButton startButton = new JButton("Continue");
        startButton.setFont(loadFonts.montserratFont);
        startButton.setForeground(new Color(82, 121, 111));
        startButton.setBackground(new Color(202, 210, 197));
        startButton.setBounds((this.bkgImage.getIconWidth() - 200) / 2 - 50, 600, 300, 40);
        this.backgroundLabel.add(startButton);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        //setResizable(false);

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);
        //setResizable(false);
        startButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(startButton)) {
                            displayResultsController.execute();
                        }
                    }
                }
        );

    }

//    public static void main(String[] args) {
//        DisplayResultsView displayResultsView = new DisplayResultsView();
//    }
    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")){
            final DisplayResultsState state = (DisplayResultsState) evt.getNewValue();
        }
    }
    public String getViewName() {
        return viewName;
    }

}

package view;

import interface_adapter.splash_screen_view.SplashScreenController;
import interface_adapter.splash_screen_view.SplashScreenState;
import interface_adapter.splash_screen_view.SplashScreenViewModel;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.IOException;
import java.io.File;

public class SplashScreenView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "splash screen";

    private Font koulenFont;
    private Font montserratFont;
    private final SplashScreenViewModel splashScreenViewModel;
    private SplashScreenController splashScreenController;

    public SplashScreenView(SplashScreenViewModel splashScreenViewModel) {
        this.splashScreenViewModel = splashScreenViewModel;
        this.splashScreenViewModel.addPropertyChangeListener(this);
        LoadFonts loadFonts = new LoadFonts();

        // Set the background image
        ImageIcon bkgImage = new ImageIcon("src/main/java/view/images/BackgroundImage.png");
        JLabel splashLabel = new JLabel(bkgImage);

        // Add in the title
        JLabel titleLabel = new JLabel("GLASY", JLabel.CENTER);
        titleLabel.setFont(loadFonts.koulenFont);
        titleLabel.setForeground(new Color(202, 210, 197));
        titleLabel.setBounds(0, 130, bkgImage.getIconWidth(), 280);
        splashLabel.add(titleLabel);

        // Add in the subtitle
        JLabel subtitleLabel = new JLabel("The only trip planner you'll ever use.", JLabel.CENTER);
        subtitleLabel.setFont(loadFonts.montserratFont);
        subtitleLabel.setForeground(new Color(202, 210, 197));
        subtitleLabel.setBounds(0, 400, bkgImage.getIconWidth(), 30);
        splashLabel.add(subtitleLabel);

        // Add in the button
        startButton = new JButton("Click Here to Start");
        startButton.setFont(loadFonts.montserratFont);
        startButton.setForeground(new Color(82, 121, 111));
        startButton.setBackground(new Color(202, 210, 197));
        startButton.setBounds((bkgImage.getIconWidth() - 200) / 2 - 50, 450, 300, 40);
        splashLabel.add(startButton);

        startButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(startButton)) {
                            splashScreenController.execute();
                        }
                    }
                }
        );

        add(splashLabel, BorderLayout.CENTER);
        setSize(bkgImage.getIconWidth() + 10, bkgImage.getIconHeight() + 30);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - bkgImage.getIconWidth()) / 2;
        int y = (screenSize.height - bkgImage.getIconHeight()) / 2;
        setLocation(x, y);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final SplashScreenState state = (SplashScreenState) evt.getNewValue();
        }
    }

    public void setSplashScreenController(SplashScreenController splashScreenController) {
        this.splashScreenController = splashScreenController;
    }

    public String getViewName() { return viewName; }
}

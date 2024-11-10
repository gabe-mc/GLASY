package view;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.FontFormatException;

import java.io.IOException;
import java.io.File;

public class SplashScreenView extends JFrame {
    private Font koulenFont;
    private Font montserratFont;

    public SplashScreenView() {
        ImageIcon bkgImage = new ImageIcon("src/main/java/view/images/BackgroundImage.png");

        // Load and register the Koulen font
        try {
            koulenFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File("src/main/java/view/fonts/Koulen/Koulen-Regular.ttf")).deriveFont(280f);
            montserratFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File("src/main/java/view/fonts/Montserrat/Montserrat-Regular.ttf")).deriveFont(23f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(koulenFont);
            ge.registerFont(montserratFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            koulenFont = new Font("SansSerif", Font.BOLD, 180);
            montserratFont = new Font("SansSerif", Font.BOLD, 180);
        }

        // Set the background image
        JLabel splashLabel = new JLabel(bkgImage);

        // Add in the title
        JLabel titleLabel = new JLabel("GLASY", JLabel.CENTER);
        titleLabel.setFont(koulenFont);
        titleLabel.setForeground(new Color(202, 210, 197));
        titleLabel.setBounds(0, 130, bkgImage.getIconWidth(), 280);
        splashLabel.add(titleLabel);

        // Add in the subtitle
        JLabel subtitleLabel = new JLabel("The only trip planner you'll ever use.", JLabel.CENTER);
        subtitleLabel.setFont(montserratFont);
        subtitleLabel.setForeground(new Color(202, 210, 197));
        subtitleLabel.setBounds(0, 400, bkgImage.getIconWidth(), 30);
        splashLabel.add(subtitleLabel);

        // Add in the button
        JButton startButton = new JButton("Click Here to Start");
        startButton.setFont(montserratFont);
        startButton.setForeground(new Color(82, 121, 111));
        startButton.setBackground(new Color(202, 210, 197));
        startButton.setBounds((bkgImage.getIconWidth() - 200) / 2 - 50, 450, 300, 40);
        splashLabel.add(startButton);

        add(splashLabel, BorderLayout.CENTER);
        setSize(bkgImage.getIconWidth(), bkgImage.getIconHeight());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - bkgImage.getIconWidth()) / 2;
        int y = (screenSize.height - bkgImage.getIconHeight()) / 2;
        setLocation(x, y);
    }

    public static void main(String[] args) {
        SplashScreenView splashScreenView = new SplashScreenView();
        splashScreenView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        splashScreenView.setVisible(true);
        splashScreenView.setResizable(false);
    }
}

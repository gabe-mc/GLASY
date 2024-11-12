package view;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.FontFormatException;

import java.io.IOException;
import java.io.File;

public class GeneralView extends JFrame {
    private Font koulenFont;
    private Font montserratFont;

    public GeneralView() {
        ImageIcon bkgImage = new ImageIcon("src/main/java/view/images/BackgroundImage2.png");

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

        add(splashLabel, BorderLayout.CENTER);
        setSize(bkgImage.getIconWidth() + 10, bkgImage.getIconHeight() + 30);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - bkgImage.getIconWidth()) / 2;
        int y = (screenSize.height - bkgImage.getIconHeight()) / 2;
        setLocation(x, y);
    }

    public Font getKoulenFont() {
        return koulenFont;
    }

    public Font getMontserratFont() {
        return montserratFont;
    }
}

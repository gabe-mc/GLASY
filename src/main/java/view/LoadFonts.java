package view;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.FontFormatException;

import java.io.IOException;
import java.io.File;

public class LoadFonts {
    public Font koulenFont;
    public Font montserratFont;
    public Font montserratFontSmall;
    public Font montserratBoldItalicFont;

    public LoadFonts() {
        // Load and register the Koulen font
        try {
            this.koulenFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File("src/main/java/view/fonts/Koulen/Koulen-Regular.ttf")).deriveFont(280f);
            this.montserratFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File("src/main/java/view/fonts/Montserrat/Montserrat-Regular.ttf")).deriveFont(23f);
            this.montserratFontSmall = Font.createFont(Font.TRUETYPE_FONT,
                    new File("src/main/java/view/fonts/Montserrat/Montserrat-Regular.ttf")).deriveFont(18f);
            this.montserratBoldItalicFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File("src/main/java/view/fonts/Montserrat/Montserrat-SemiBoldItalic.ttf")).deriveFont(23f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(this.koulenFont);
            ge.registerFont(this.montserratFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            this.koulenFont = new Font("SansSerif", Font.BOLD, 180);
            this.montserratFont = new Font("SansSerif", Font.BOLD, 180);
        }
    }
}

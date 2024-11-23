package view;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

public class ChooseOptionsView {
    private ImageIcon bkgImage;
    private JLabel backgroundLabel;
    private JFrame mainFrame;

    public ChooseOptionsView() {
            LoadFonts loadFonts = new LoadFonts();

            // instructions at the top of the page
            JLabel titleLabel = new JLabel("Please choose your desired preferences below, and we will plan your day for you.");
            titleLabel.setOpaque(false);
            titleLabel.setBackground(new Color(0, 0, 0, 0));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            titleLabel.setFont(loadFonts.montserratBoldItalicFont);

            // add location section
            JPanel addLocationPanel = new JPanel();
            addLocationPanel.setOpaque(false);
            addLocationPanel.setBackground(new Color(0, 0, 0, 0));
            addLocationPanel.setLayout(new BoxLayout(addLocationPanel, BoxLayout.Y_AXIS));
            addLocationPanel.setSize(new Dimension(300, 10));

            JLabel addLocationLabel = new JLabel("Please enter your current location:");
            addLocationLabel.setFont(loadFonts.montserratFontSmall);

            //creates place to type (JTextField)
            JTextField helpTextField = new JTextField();
            helpTextField.setFont(loadFonts.montserratFontSmall);

            addLocationPanel.add(addLocationLabel);
            addLocationPanel.add(helpTextField);

            // start time - end time

            JPanel setTimePanel = new JPanel();
            setTimePanel.setLayout(new BoxLayout(setTimePanel, BoxLayout.Y_AXIS));
            setTimePanel.setOpaque(false);
            setTimePanel.setBackground(new Color(0, 0, 0, 0));

            JLabel setTimeLabel = new JLabel("Set your start time and end time");
            setTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            setTimeLabel.setFont(loadFonts.montserratFontSmall);
            setTimePanel.add(setTimeLabel);

            // start time

            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

            SpinnerDateModel model = new SpinnerDateModel();
            JSpinner startTime = new JSpinner(model);
            startTime.setFont(loadFonts.montserratFontSmall);
            JSpinner.DateEditor startTimeEditor = new JSpinner.DateEditor(startTime, "HH:mm");
            startTimeEditor.setFont(loadFonts.montserratFontSmall);

            JLabel startTimeLabel = new JLabel("Start Time: " + dateFormat.format((Date)startTime.getValue()));
            startTimeLabel.setFont(loadFonts.montserratFontSmall);
            startTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            startTime.setEditor(startTimeEditor);
            setTimePanel.add(startTimeLabel);
            setTimePanel.add(startTime);

            startTime.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    startTimeLabel.setText("Start time:" + dateFormat.format((Date)startTime.getValue()));
                }
            });

            //end time

            SpinnerDateModel model1 = new SpinnerDateModel();
            JSpinner endTime = new JSpinner(model1);
            endTime.setFont(loadFonts.montserratFontSmall);
            JSpinner.DateEditor endTimeEditor = new JSpinner.DateEditor(endTime, "HH:mm");
            endTimeEditor.setFont(loadFonts.montserratFontSmall);

            JLabel endTimeLabel = new JLabel("End Time: " + dateFormat.format((Date)endTime.getValue()));
            endTimeLabel.setFont(loadFonts.montserratFontSmall);
            endTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            endTime.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {

                    endTimeLabel.setText("End time:" + dateFormat.format((Date)endTime.getValue()));
                }
            });

            endTime.setEditor(endTimeEditor);
            setTimePanel.add(endTimeLabel);
            setTimePanel.add(endTime);

            //Distance
            JPanel distancePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            distancePanel.setOpaque(false);
            distancePanel.setBackground(new Color(0, 0, 0, 0));
            JSlider distanceSlider = new JSlider(0, 20, 0);
            distanceSlider.setMajorTickSpacing(5);
            distanceSlider.setMinorTickSpacing(1);
            distanceSlider.setPaintTicks(true);
            distanceSlider.setPaintLabels(true);
            distanceSlider.setFont(loadFonts.montserratFontSmall);
            distanceSlider.setOpaque(false);
            distanceSlider.setBackground(new Color(0, 0, 0, 0));

            JLabel distanceLabel = new JLabel("Distance (km): " + distanceSlider.getValue());
            distanceLabel.setFont(loadFonts.montserratFontSmall);

            distanceSlider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    int distanceRequirement = distanceSlider.getValue();
                    distanceLabel.setText("Distance: " + distanceRequirement + " km");
                }
            });

            distancePanel.add(distanceLabel);
            distancePanel.add(distanceSlider);

            //Rating

            JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            ratingPanel.setOpaque(false);
            ratingPanel.setBackground(new Color(0, 0, 0, 0));
            JSlider ratingSlider = new JSlider(0, 10,0);
            ratingSlider.setMajorTickSpacing(2);
            ratingSlider.setMinorTickSpacing(1);
            ratingSlider.setPaintTicks(true);
            ratingSlider.setPaintLabels(true);
            ratingSlider.setOpaque(false);
            ratingSlider.setBackground(new Color(0, 0, 0, 0));

            //changes the labels below to be 1/2 of the actual value (so it could be .5 stars
            Hashtable<Integer, JLabel> starTable = new Hashtable<>();

            JLabel zero = new JLabel("0");
            JLabel first = new JLabel("1");
            JLabel second = new JLabel("2");
            JLabel third = new JLabel("3");
            JLabel fourth = new JLabel("4");
            JLabel fifth = new JLabel("5");
            zero.setFont(loadFonts.montserratFontSmall);
            first.setFont(loadFonts.montserratFontSmall);
            second.setFont(loadFonts.montserratFontSmall);
            third.setFont(loadFonts.montserratFontSmall);
            fourth.setFont(loadFonts.montserratFontSmall);
            fifth.setFont(loadFonts.montserratFontSmall);

            starTable.put(0, zero);
            starTable.put(2, first);
            starTable.put(4, second);
            starTable.put(6, third);
            starTable.put(8, fourth);
            starTable.put(10, fifth);
            ratingSlider.setLabelTable(starTable);

            JLabel ratingLabel = new JLabel(  ratingSlider.getValue()/2 + " Stars ");
            ratingLabel.setFont(loadFonts.montserratFontSmall);

            ratingSlider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    double ratingRequirement = ratingSlider.getValue();
                    ratingLabel.setText(ratingRequirement/2 + " Stars");
                }
            });

            ratingPanel.add(ratingLabel);
            ratingPanel.add(ratingSlider);

            //checkboxes
            JLabel locationTypesLabel = new JLabel("Location types: ");
            locationTypesLabel.setFont(loadFonts.montserratFontSmall);

            JCheckBox resturantCheck = new JCheckBox("Resturant");
            resturantCheck.setFont(loadFonts.montserratFontSmall);
            resturantCheck.setOpaque(false);
            resturantCheck.setBackground(new Color(0, 0, 0, 0));

            JCheckBox attractionCheck = new JCheckBox("Attraction");
            attractionCheck.setFont(loadFonts.montserratFontSmall);
            attractionCheck.setOpaque(false);
            attractionCheck.setBackground(new Color(0, 0, 0, 0));

            JCheckBox shopCheck = new JCheckBox("Shop");
            shopCheck.setFont(loadFonts.montserratFontSmall);
            shopCheck.setOpaque(false);
            shopCheck.setBackground(new Color(0, 0, 0, 0));

            JPanel checkboxPanel = new JPanel();
            checkboxPanel.setOpaque(false);
            checkboxPanel.setBackground(new Color(0, 0, 0, 0));
            checkboxPanel.add(locationTypesLabel);
            checkboxPanel.add(resturantCheck);
            checkboxPanel.add(attractionCheck);
            checkboxPanel.add(shopCheck);

            // Starting to add the components to something the user will actually see

            this.bkgImage = new ImageIcon("src/main/java/view/images/BackgroundImage2.png");

            JPanel optionsPanel = new JPanel();
            optionsPanel.setPreferredSize(new Dimension(this.bkgImage.getIconWidth() - 1000,
                    this.bkgImage.getIconHeight() - 1000));
            optionsPanel.setOpaque(false);
            optionsPanel.setBackground(new Color(0, 0, 0, 0));
            optionsPanel.setLayout(new BoxLayout(optionsPanel,BoxLayout.Y_AXIS));

            optionsPanel.add(titleLabel);
            optionsPanel.add(addLocationPanel);
            optionsPanel.add(setTimePanel);
            optionsPanel.add(distancePanel);
            optionsPanel.add(ratingPanel);
            optionsPanel.add(checkboxPanel);

            optionsPanel.setBounds(
                    90,
                    50,
                    this.bkgImage.getIconWidth() - 180,
                    this.bkgImage.getIconHeight() - 200);

            // Make the frame
            this.mainFrame = new JFrame();

            // Set the background image
            this.backgroundLabel = new JLabel(this.bkgImage);
            this.backgroundLabel.setLayout(null);

            this.backgroundLabel.add(optionsPanel);

            // Add in the continue button
            JButton startButton = new JButton("Continue");
            startButton.setFont(loadFonts.montserratFont);
            startButton.setForeground(new Color(82, 121, 111));
            startButton.setBackground(new Color(202, 210, 197));
            startButton.setBounds((this.bkgImage.getIconWidth() - 200) / 2 - 50, 600, 300, 40);
            this.backgroundLabel.add(startButton);

            this.mainFrame.add(this.backgroundLabel, BorderLayout.CENTER);
            this.mainFrame.setSize(this.bkgImage.getIconWidth() + 10, this.bkgImage.getIconHeight() + 30);

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (screenSize.width - bkgImage.getIconWidth()) / 2;
            int y = (screenSize.height - bkgImage.getIconHeight()) / 2;
            this.mainFrame.setLocation(x, y);

            this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.mainFrame.setVisible(true);
            this.mainFrame.setResizable(false);

    }

    public static void main(String[] args){
        ChooseOptionsView chooseOptionsView = new ChooseOptionsView();
    }
}

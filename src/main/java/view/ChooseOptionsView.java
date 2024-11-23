package view;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

public class ChooseOptionsView {
    private ImageIcon bkgImage;
    private JLabel backgroundLabel;
    private JFrame mainFrame;

    public ChooseOptionsView() {
        {
            LoadFonts loadFonts = new LoadFonts();

            JLabel titleLabel = new JLabel("Please choose your desired preferences below, and we will plan your day for you.");
            titleLabel.setOpaque(false);
            titleLabel.setBackground(new Color(0, 0, 0, 0));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            titleLabel.setFont(loadFonts.montserratFont);

            //add location
            JPanel addLocationPanel = new JPanel();
            addLocationPanel.setOpaque(false);
            addLocationPanel.setBackground(new Color(0, 0, 0, 0));
            addLocationPanel.setLayout(new BoxLayout(addLocationPanel,BoxLayout.Y_AXIS));
            JLabel addLocationLabel = new JLabel("Your current Location:");
            DefaultListModel<String> listModel = new DefaultListModel<>();
            JList<String> aLocLst = new JList<>(listModel);
            String[] locationOptions = {"om","nom","minecraft", "applesauce","sauch", "yummyyum"};
            for (String word : locationOptions) {
                listModel.addElement(word); //add everything over
            }
            //creates place to type (JTextField)
            JTextField helpTextField = new JTextField();
            helpTextField.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent e) {
                    listModel.clear();
                    for(String word : locationOptions) {
                        if( word.toLowerCase().contains(helpTextField.getText())){ //searches for matching
                            listModel.addElement(word); // checkstyled ...
                        }
                    }
                }//changes on release !
            });

            // clicking
            aLocLst.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    String selectedLocation = aLocLst.getSelectedValue(); //clicked location
                    if (selectedLocation != null) {
                        helpTextField.setText(selectedLocation); // changes the text, to the selected location
                    }
                }
            });

            addLocationPanel.add(addLocationLabel);
            addLocationPanel.add(helpTextField);
            addLocationPanel.add(new JScrollPane(aLocLst));

            // start time - end time

            JPanel setTimePanel = new JPanel();
            setTimePanel.setLayout(new BoxLayout(setTimePanel,BoxLayout.Y_AXIS));
            setTimePanel.setOpaque(false);
            setTimePanel.setBackground(new Color(0, 0, 0, 0));

            JLabel setTimeLabel = new JLabel("Set your start time and end time");
            setTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            setTimeLabel.setFont(loadFonts.montserratFont);
            setTimePanel.add(setTimeLabel);

            // start time

            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

            SpinnerDateModel model = new SpinnerDateModel();
            JSpinner startTime = new JSpinner(model);
            JSpinner.DateEditor startTimeEditor = new JSpinner.DateEditor(startTime, "HH:mm");

            JLabel startTimeLabel = new JLabel("End time:" + dateFormat.format((Date)startTime.getValue()));

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
            JSpinner.DateEditor endTimeEditor = new JSpinner.DateEditor(endTime, "HH:mm");
            JLabel endTimeLabel = new JLabel("End time:" + dateFormat.format((Date)endTime.getValue()));

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
            distanceSlider.setFont(loadFonts.montserratFont);

            JLabel distanceLabel = new JLabel("Distance (km): " + distanceSlider.getValue());

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

            //changes the labels below to be 1/2 of the actual value (so it could be .5 stars
            Hashtable<Integer, JLabel> starTable = new Hashtable<>();
            starTable.put(0, new JLabel("0"));
            starTable.put(2, new JLabel("1"));
            starTable.put(4, new JLabel("2"));
            starTable.put(6, new JLabel("3"));
            starTable.put(8, new JLabel("4"));
            starTable.put(10, new JLabel("5"));
            ratingSlider.setLabelTable(starTable);

            JLabel ratingLabel = new JLabel(  ratingSlider.getValue()/2 + " Stars ");

            ratingSlider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    double ratingRequirement = ratingSlider.getValue();
                    ratingLabel.setText(ratingRequirement/2 + " Stars");
                }
            });

            //checkboxes
            JLabel locationTypesLabel = new JLabel("Location types: ");
            JCheckBox resturantCheck = new JCheckBox("Resturant");
            JCheckBox attractionCheck = new JCheckBox("Attraction");
            JCheckBox shopCheck = new JCheckBox("Shop");

            JPanel checkboxPanel = new JPanel();
            checkboxPanel.setOpaque(false);
            checkboxPanel.setBackground(new Color(0, 0, 0, 0));
            checkboxPanel.add(locationTypesLabel);
            checkboxPanel.add(resturantCheck);
            checkboxPanel.add(attractionCheck);
            checkboxPanel.add(shopCheck);

            ratingPanel.add(ratingLabel);
            ratingPanel.add(ratingSlider);

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
            optionsPanel.add(addLocationPanel);
            optionsPanel.add(setTimePanel);
            optionsPanel.add(distancePanel);
            optionsPanel.add(ratingPanel);
            optionsPanel.add(checkboxPanel);

            optionsPanel.setBounds(
                    90,
                    50,
                    this.bkgImage.getIconWidth() - 180,
                    this.bkgImage.getIconHeight() - 230);

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
    }

    public static void main(String[] args){
        ChooseOptionsView chooseOptionsView = new ChooseOptionsView();
    }
}

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

public class ChooseOptionsView extends GeneralView {
    public static void main(String[] args){
        ChooseOptionsView chooseOptionsView = new ChooseOptionsView();
        chooseOptionsView.getMainFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chooseOptionsView.getMainFrame().setVisible(true);
        chooseOptionsView.getMainFrame().setResizable(false);
    }
    public ChooseOptionsView() {
        {
            JLabel titleLabel = new JLabel("Please choose your desired preferences below, and we will plan your day for you");
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            titleLabel.setFont(getMontserratBoldItalicFont());

            JPanel optionsPanel = new JPanel();
            optionsPanel.setLayout(new BoxLayout(optionsPanel,BoxLayout.Y_AXIS));

            //add location
            JPanel addLocationPanel = new JPanel();
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

            JLabel setTimeLabel = new JLabel("Set your start time and end time");
            setTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            setTimeLabel.setFont(getMontserratFont());
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

//          distance

            JPanel distancePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JSlider distanceSlider = new JSlider(0, 20, 0);
            distanceSlider.setMajorTickSpacing(5);
            distanceSlider.setMinorTickSpacing(1);
            distanceSlider.setPaintTicks(true);
            distanceSlider.setPaintLabels(true);
            distanceSlider.setFont(getMontserratFont());

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

            //listeners?

//        boolean resturant = false;
//        boolean attraction = false;
//        boolean shop = false;
//
//        resturantCheck.addItemListener(e -> {
//            if (e.getStateChange() == ItemEvent.SELECTED){
//                resturant = true;
//            }
//        });
            JPanel checkboxPanel = new JPanel();
            checkboxPanel.add(locationTypesLabel);
            checkboxPanel.add(resturantCheck);
            checkboxPanel.add(attractionCheck);
            checkboxPanel.add(shopCheck);

            ratingPanel.add(ratingLabel);
            ratingPanel.add(ratingSlider);

            optionsPanel.add(titleLabel);
            optionsPanel.add(addLocationPanel);
            optionsPanel.add(addLocationPanel);
            optionsPanel.add(setTimePanel);
            optionsPanel.add(distancePanel);
            optionsPanel.add(ratingPanel);
            optionsPanel.add(checkboxPanel);

            getMainFrame().add(optionsPanel);
        }
    }
}

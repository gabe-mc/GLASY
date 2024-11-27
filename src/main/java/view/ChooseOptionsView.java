package view;

import interface_adapter.choose_options.ChooseOptionsController;
import interface_adapter.choose_options.ChooseOptionsState;
import interface_adapter.choose_options.ChooseOptionsViewModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

public class ChooseOptionsView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "choose options";
    private final ChooseOptionsViewModel chooseOptionsViewModel;
    private ChooseOptionsController chooseOptionsController;

    private ImageIcon bkgImage;
    private JLabel backgroundLabel;

    private final JTextField helpTextField = new JTextField();
    private final SpinnerDateModel startModel = new SpinnerDateModel();
    private final JSpinner startTime = new JSpinner(startModel);
    private final SpinnerDateModel endModel = new SpinnerDateModel();
    private final JSpinner endTime = new JSpinner(endModel);
    private final JSlider distanceSlider = new JSlider(1, 20, 10);
    private final JSlider ratingSlider = new JSlider(0, 10,0);
    private final JCheckBox restaurantCheck = new JCheckBox("Restaurant");
    private final JCheckBox attractionCheck = new JCheckBox("Attraction");
    private final JCheckBox shopCheck = new JCheckBox("Shop");
    private final JLabel errorLabel = new JLabel();

    public ChooseOptionsView(ChooseOptionsViewModel chooseOptionsViewModel) {
        this.chooseOptionsViewModel = chooseOptionsViewModel;
        this.chooseOptionsViewModel.addPropertyChangeListener(this);
        LoadFonts loadFonts = new LoadFonts();

        // instructions at the top of the page
        JLabel titleLabel = new JLabel("Please choose your desired preferences below, and we will plan your day for you.");
        titleLabel.setOpaque(false);
        titleLabel.setBackground(new Color(0, 0, 0, 0));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(loadFonts.montserratBoldItalicFont);

        // add location section
        JPanel addressPanel = new JPanel();
        addressPanel.setOpaque(false);
        addressPanel.setBackground(new Color(0, 0, 0, 0));
        addressPanel.setLayout(new BoxLayout(addressPanel, BoxLayout.Y_AXIS));
        addressPanel.setSize(new Dimension(300, 10));

        JLabel addressLabel = new JLabel("Please enter your current location:");
        addressLabel.setFont(loadFonts.montserratFontSmall);

        //creates place to type (JTextField)
        helpTextField.setFont(loadFonts.montserratFontSmall);

        helpTextField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final ChooseOptionsState currentState = chooseOptionsViewModel.getState();
                currentState.setStartingAddress(helpTextField.getText());
                chooseOptionsViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        addressPanel.add(addressLabel);
        addressPanel.add(helpTextField);

        JPanel currentLocationButtonPanel = new JPanel();
        currentLocationButtonPanel.setLayout(new BoxLayout(currentLocationButtonPanel, BoxLayout.X_AXIS));
        currentLocationButtonPanel.setOpaque(false);
        currentLocationButtonPanel.add(Box.createHorizontalGlue());

        JButton currentLocationButton = new JButton("Use Current Location");
        currentLocationButton.setFont(loadFonts.montserratFont);
        currentLocationButton.setForeground(new Color(82, 121, 111));
        currentLocationButton.setBackground(new Color(202, 210, 197));
        currentLocationButtonPanel.add(currentLocationButton);
        addressPanel.add(currentLocationButtonPanel);

        currentLocationButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(currentLocationButton)) {
//                            chooseOptionsController.useCurrentLocation();
                        }
                    }
                }
        );

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

        startTime.setFont(loadFonts.montserratFontSmall);
        JSpinner.DateEditor startTimeEditor = new JSpinner.DateEditor(startTime, "HH:mm");
        startTimeEditor.setFont(loadFonts.montserratFontSmall);

        JLabel startTimeLabel = new JLabel("Start Time: " + dateFormat.format(startTime.getValue()));
        startTimeLabel.setFont(loadFonts.montserratFontSmall);
        startTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        startTime.setEditor(startTimeEditor);
        setTimePanel.add(startTimeLabel);
        setTimePanel.add(startTime);

        //end time

        endTime.setFont(loadFonts.montserratFontSmall);
        JSpinner.DateEditor endTimeEditor = new JSpinner.DateEditor(endTime, "HH:mm");
        endTimeEditor.setFont(loadFonts.montserratFontSmall);

        JLabel endTimeLabel = new JLabel("End Time: " + dateFormat.format(endTime.getValue()));
        endTimeLabel.setFont(loadFonts.montserratFontSmall);
        endTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        endTime.setEditor(endTimeEditor);
        setTimePanel.add(endTimeLabel);
        setTimePanel.add(endTime);

        JSpinner[] spinners = {startTime, endTime};
        for (JSpinner spinner : spinners) {
            spinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    final ChooseOptionsState currentState = chooseOptionsViewModel.getState();
                    JSpinner source = (JSpinner) e.getSource();
                    if (source == startTime) {
                        startTimeLabel.setText("Start time: " + dateFormat.format(startTime.getValue()));
                        currentState.setStartTime((Date) source.getValue());
                    } else if (source == endTime) {
                        endTimeLabel.setText("End time: " + dateFormat.format(endTime.getValue()));
                        currentState.setEndTime((Date) source.getValue());
                    }
                }
            });
        }

        //Distance
        JPanel distancePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        distancePanel.setOpaque(false);
        distancePanel.setBackground(new Color(0, 0, 0, 0));
        distanceSlider.setMajorTickSpacing(1);
        distanceSlider.setMinorTickSpacing(1);
        distanceSlider.setPaintTicks(true);
        distanceSlider.setPaintLabels(true);
        distanceSlider.setFont(loadFonts.montserratFontSmall);
        distanceSlider.setOpaque(false);
        distanceSlider.setBackground(new Color(0, 0, 0, 0));

        Hashtable<Integer, JLabel> distanceTable = new Hashtable<>();
        for (int i = 1; i <= 20; i = i - i % 5 + 5) {
            JLabel label = new JLabel(String.valueOf(i));
            label.setFont(loadFonts.montserratFontSmall);
            distanceTable.put(i, label);
        }

        distanceSlider.setLabelTable(distanceTable);

        JLabel distanceLabel = new JLabel("Distance (km): " + distanceSlider.getValue());
        distanceLabel.setFont(loadFonts.montserratFontSmall);

        distancePanel.add(distanceLabel);
        distancePanel.add(distanceSlider);

        //Rating

        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ratingPanel.setOpaque(false);
        ratingPanel.setBackground(new Color(0, 0, 0, 0));
        ratingSlider.setMajorTickSpacing(2);
        ratingSlider.setMinorTickSpacing(1);
        ratingSlider.setPaintTicks(true);
        ratingSlider.setPaintLabels(true);
        ratingSlider.setOpaque(false);
        ratingSlider.setBackground(new Color(0, 0, 0, 0));

        //changes the labels below to be 1/2 of the actual value (so it could be .5 stars
        Hashtable<Integer, JLabel> starTable = new Hashtable<>();
        for (int i = 0; i <= 5; i++) {
            JLabel label = new JLabel(String.valueOf(i));
            label.setFont(loadFonts.montserratFontSmall);
            starTable.put(i * 2, label);
        }
        ratingSlider.setLabelTable(starTable);

        JLabel ratingLabel = new JLabel( "0.0 Stars");
        ratingLabel.setFont(loadFonts.montserratFontSmall);

        ratingPanel.add(ratingLabel);
        ratingPanel.add(ratingSlider);

        JSlider[] sliders = {distanceSlider, ratingSlider};
        for (JSlider slider : sliders) {
            slider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    final ChooseOptionsState currentState = chooseOptionsViewModel.getState();
                    JSlider source = (JSlider) e.getSource();
                    if (source == distanceSlider) {
                        distanceLabel.setText("Distance: " + source.getValue() + " km");
                        currentState.setMaxDistance(source.getValue());
                    } else if (source == ratingSlider) {
                        ratingLabel.setText(((double) source.getValue())/2 + " Stars");
                        currentState.setMinStars(source.getValue());
                    }
                }
            });
        }

        //checkboxes
        JLabel locationTypesLabel = new JLabel("Location types: ");
        locationTypesLabel.setFont(loadFonts.montserratFontSmall);

        restaurantCheck.setFont(loadFonts.montserratFontSmall);
        restaurantCheck.setOpaque(false);
        restaurantCheck.setBackground(new Color(0, 0, 0, 0));

        attractionCheck.setFont(loadFonts.montserratFontSmall);
        attractionCheck.setOpaque(false);
        attractionCheck.setBackground(new Color(0, 0, 0, 0));

        shopCheck.setFont(loadFonts.montserratFontSmall);
        shopCheck.setOpaque(false);
        shopCheck.setBackground(new Color(0, 0, 0, 0));

        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setOpaque(false);
        checkboxPanel.setBackground(new Color(0, 0, 0, 0));
        checkboxPanel.add(locationTypesLabel);
        checkboxPanel.add(restaurantCheck);
        checkboxPanel.add(attractionCheck);
        checkboxPanel.add(shopCheck);

        JCheckBox[] checkBoxes = {restaurantCheck, attractionCheck, shopCheck};
        for (JCheckBox checkBox : checkBoxes) {
            final ChooseOptionsState currentState = chooseOptionsViewModel.getState();
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JCheckBox source = (JCheckBox) e.getSource();
                    currentState.setPossibleLocationType(source.getText(), source.isSelected());
                }
            });
            currentState.setPossibleLocationType(checkBox.getText(), checkBox.isSelected());
        }

        // Starting to add the components to something the user will actually see

        this.bkgImage = new ImageIcon("src/main/java/view/images/BackgroundImage2.png");

        JPanel optionsPanel = new JPanel();
        optionsPanel.setPreferredSize(new Dimension(this.bkgImage.getIconWidth() - 1000,
                this.bkgImage.getIconHeight() - 1000));
        optionsPanel.setOpaque(false);
        optionsPanel.setBackground(new Color(0, 0, 0, 0));
        optionsPanel.setLayout(new BoxLayout(optionsPanel,BoxLayout.Y_AXIS));

        optionsPanel.add(titleLabel);
        optionsPanel.add(addressPanel);
        optionsPanel.add(setTimePanel);
        optionsPanel.add(distancePanel);
        optionsPanel.add(ratingPanel);
        optionsPanel.add(checkboxPanel);

        optionsPanel.setBounds(
                90,
                50,
                this.bkgImage.getIconWidth() - 180,
                this.bkgImage.getIconHeight() - 200);

        // Set the background image
        this.backgroundLabel = new JLabel(this.bkgImage);
        this.backgroundLabel.setLayout(null);

        this.backgroundLabel.add(optionsPanel);

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
                            final ChooseOptionsState currentState = chooseOptionsViewModel.getState();
                            chooseOptionsController.execute(
                                    currentState.getStartingAddress(),
                                    currentState.getMaxDistance(),
                                    currentState.getMinStars(),
                                    currentState.getStartTime(),
                                    currentState.getEndTime(),
                                    currentState.getPossibleLocationTypes()
                            );
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
                            chooseOptionsController.switchToPreviousView();
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

        add(this.backgroundLabel, BorderLayout.CENTER);
        setSize(this.bkgImage.getIconWidth() + 10, this.bkgImage.getIconHeight() + 30);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - bkgImage.getIconWidth()) / 2;
        int y = (screenSize.height - bkgImage.getIconHeight()) / 2;
        setLocation(x, y);
    }

    @Override
    public void actionPerformed(ActionEvent evt) { System.out.println("Click " + evt.getActionCommand()); }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final ChooseOptionsState state = (ChooseOptionsState) evt.getNewValue();
            setFields(state);
            errorLabel.setText(state.getErrorText());
        }
    }

    private void setFields(ChooseOptionsState state) {
        helpTextField.setText(state.getStartingAddress());
        startTime.setValue(state.getStartTime());
        endTime.setValue(state.getEndTime());
        distanceSlider.setValue(state.getMaxDistance());
        ratingSlider.setValue(state.getMinStars());
        restaurantCheck.setSelected(state.getPossibleLocationTypes().get(restaurantCheck.getText()));
        attractionCheck.setSelected(state.getPossibleLocationTypes().get(attractionCheck.getText()));
        shopCheck.setSelected(state.getPossibleLocationTypes().get(shopCheck.getText()));
    }

    public void setChooseOptionsController(ChooseOptionsController chooseOptionsController) {
        this.chooseOptionsController = chooseOptionsController;
    }

    public String getViewName() { return viewName; }
}

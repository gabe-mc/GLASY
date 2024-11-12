package view;

import javax.swing.*;

public class DisplayOptionsView extends GeneralView {

    public DisplayOptionsView() {

    }

    public static void main(String[] args) {
        DisplayOptionsView displayOptionsView = new DisplayOptionsView();
        displayOptionsView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        displayOptionsView.setVisible(true);
        displayOptionsView.setResizable(false);
    }
}

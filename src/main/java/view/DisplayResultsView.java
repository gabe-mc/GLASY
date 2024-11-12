package view;

import javax.swing.*;

public class DisplayResultsView extends GeneralView {

    public DisplayResultsView() {

    }

    public static void main(String[] args) {
        DisplayResultsView displayResultsView = new DisplayResultsView();
        displayResultsView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        displayResultsView.setVisible(true);
        displayResultsView.setResizable(false);
    }
}

package view;

import javax.swing.*;

public class DisplayResultsView extends GeneralView {

    public DisplayResultsView() {

    }

    public static void main(String[] args) {
        DisplayResultsView displayResultsView = new DisplayResultsView();
        displayResultsView.getMainFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        displayResultsView.getMainFrame().setVisible(true);
        displayResultsView.getMainFrame().setResizable(false);
    }
}

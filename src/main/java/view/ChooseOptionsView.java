package view;

import javax.swing.*;

public class ChooseOptionsView extends GeneralView {

    public ChooseOptionsView() {

    }

    public static void main(String[] args) {
        ChooseOptionsView chooseOptionsView = new ChooseOptionsView();
        chooseOptionsView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chooseOptionsView.setVisible(true);
        chooseOptionsView.setResizable(false);
    }
}

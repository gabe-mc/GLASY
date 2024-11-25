package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_options.ChooseOptionsViewModel;
import interface_adapter.display_options.DisplayOptionsViewModel;
import interface_adapter.display_results_view.DisplayResultsViewModel;
import interface_adapter.splash_screen_view.SplashScreenViewModel;
import view.DisplayResultsView;
import view.SplashScreenView;
import view.ViewManager;


public class Main {
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addSplashScreenView()
                .addChooseOptionsView()
                .addDisplayOptionsView()
                .addStartAppUseCase()
                .build();
        application.pack();
        application.setVisible(true);
        application.setResizable(false);
    }
}

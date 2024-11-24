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
        final JFrame application = new JFrame("text here");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final CardLayout cardLayout = new CardLayout();

        //the various view objects only one view is visible at a time ? possibly not needed
        final JPanel views = new JPanel(cardLayout);
        application.add(views);

        final ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);


        final SplashScreenViewModel splashScreenViewModel = new SplashScreenViewModel();
        final ChooseOptionsViewModel chooseOptionsViewModel = new ChooseOptionsViewModel();
        final DisplayOptionsViewModel displayOptionsViewModel = new DisplayOptionsViewModel();
        final DisplayResultsViewModel displayResultsViewModel = new DisplayResultsViewModel();

        final SplashScreenView splashScreenView = new SplashScreenView(splashScreenViewModel);


        //viewManagerModel.setState(__ view .getViewName())
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}

package app;

import data_access.CurrentLocationProvider;
import data_access.FoursquareLocationProvider;
import data_access.GoogleMapsLocationProvider;
import data_access.UserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.choose_options.ChooseOptionsController;
import interface_adapter.choose_options.ChooseOptionsPresenter;
import interface_adapter.choose_options.ChooseOptionsViewModel;
import interface_adapter.display_options.DisplayOptionsController;
import interface_adapter.display_options.DisplayOptionsPresenter;
import interface_adapter.display_options.DisplayOptionsViewModel;
import interface_adapter.display_results_view.DisplayResultsPresenter;
import interface_adapter.display_results_view.DisplayResultsViewModel;
import interface_adapter.splash_screen_view.SplashScreenController;
import interface_adapter.splash_screen_view.SplashScreenPresenter;
import interface_adapter.splash_screen_view.SplashScreenViewModel;
import use_case.choose_options.ChooseOptionsInputBoundary;
import use_case.choose_options.ChooseOptionsInteractor;
import use_case.choose_options.ChooseOptionsOutputBoundary;
import use_case.compute_time.ComputeTimeInputBoundary;
import use_case.compute_time.ComputeTimeInteractor;
import use_case.compute_time.ComputeTimeOutputBoundary;
import use_case.find_shortest_path.FindShortestPathInputBoundary;
import use_case.find_shortest_path.FindShortestPathInteractor;
import use_case.find_shortest_path.FindShortestPathOutputBoundary;
import use_case.start_app.StartAppInputBoundary;
import use_case.start_app.StartAppInteractor;
import use_case.start_app.StartAppOutputBoundary;
import view.*;


import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final UserDataAccessObject userDataAccessObject = new UserDataAccessObject();
    private final CurrentLocationProvider currentLocationProvider = new CurrentLocationProvider();
    private final GoogleMapsLocationProvider googleMapsLocationProvider = new GoogleMapsLocationProvider();
    private final FoursquareLocationProvider foursquareLocationProvider = new FoursquareLocationProvider();

    private SplashScreenView splashScreenView;
    private SplashScreenViewModel splashScreenViewModel = new SplashScreenViewModel();
    private ChooseOptionsView chooseOptionsView;
    private ChooseOptionsViewModel chooseOptionsViewModel = new ChooseOptionsViewModel();
    private DisplayOptionsView displayOptionsView;
    private DisplayOptionsViewModel displayOptionsViewModel = new DisplayOptionsViewModel();
    private DisplayResultsView displayResultsView;
    private DisplayResultsViewModel displayResultsViewModel = new DisplayResultsViewModel();

    public AppBuilder() { cardPanel.setLayout(cardLayout); }

    public AppBuilder addSplashScreenView() {
        splashScreenViewModel = new SplashScreenViewModel();
        splashScreenView = new SplashScreenView(splashScreenViewModel);
        cardPanel.add(splashScreenView, splashScreenView.getViewName());
        return this;
    }

    public AppBuilder addChooseOptionsView() {
        chooseOptionsViewModel = new ChooseOptionsViewModel();
        chooseOptionsView = new ChooseOptionsView(chooseOptionsViewModel);
        cardPanel.add(chooseOptionsView, chooseOptionsView.getViewName());
        return this;
    }

    public AppBuilder addDisplayOptionsView() {
        displayOptionsViewModel = new DisplayOptionsViewModel();
        displayOptionsView = new DisplayOptionsView(displayOptionsViewModel);
        cardPanel.add(displayOptionsView, displayOptionsView.getViewName());
        return this;
    }

    public AppBuilder addDisplayResultsView() {
        displayResultsViewModel = new DisplayResultsViewModel();
        displayResultsView = new DisplayResultsView(displayResultsViewModel);
        cardPanel.add(displayResultsView, displayResultsView.getViewName());
        return this;
    }

    public AppBuilder addStartAppUseCase() {
        final StartAppOutputBoundary startAppOutputBoundary = new SplashScreenPresenter(viewManagerModel,
                chooseOptionsViewModel);
        final StartAppInputBoundary startAppInteractor = new StartAppInteractor(
                currentLocationProvider, userDataAccessObject, startAppOutputBoundary);

        final SplashScreenController controller = new SplashScreenController(startAppInteractor);
        splashScreenView.setSplashScreenController(controller);
        return this;
    }

    public AppBuilder addChooseOptionsUseCase() {
        final ChooseOptionsPresenter chooseOptionsPresenter = new ChooseOptionsPresenter(viewManagerModel,
                chooseOptionsViewModel, splashScreenViewModel, displayOptionsViewModel);
        final ChooseOptionsInputBoundary chooseOptionsInteractor = new ChooseOptionsInteractor(
                foursquareLocationProvider, userDataAccessObject, chooseOptionsPresenter);
        final UseCurrentLocationInputBoundary useCurrentLocationInteractor = new UseCurrentLocationInteractor(
                userDataAccessObject, chooseOptionsPresenter);

        final ChooseOptionsController controller = new ChooseOptionsController(chooseOptionsInteractor,
                useCurrentLocationInteractor, googleMapsLocationProvider);
        chooseOptionsView.setChooseOptionsController(controller);
        return this;
    }

    public AppBuilder addFindShortestPathUseCase() {
        final DisplayOptionsPresenter displayOptionsPresenter = new DisplayOptionsPresenter(
                viewManagerModel, displayOptionsViewModel, chooseOptionsViewModel, displayResultsViewModel);
        final FindShortestPathInputBoundary findShortestPathInteractor = new FindShortestPathInteractor(
                googleMapsLocationProvider, displayOptionsPresenter);

        final ComputeTimeInputBoundary computeTimeInteractor = new ComputeTimeInteractor(userDataAccessObject,
                displayOptionsPresenter);


        final DisplayOptionsController controller = new DisplayOptionsController(findShortestPathInteractor,
                computeTimeInteractor);
        displayOptionsView.setDisplayOptionsController(controller);
        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("GLASY");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(splashScreenView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
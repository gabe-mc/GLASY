package interface_adapter.choose_options;

import entity.AttractionData;
import interface_adapter.ViewManagerModel;
import interface_adapter.display_options.DisplayOptionsState;
import interface_adapter.display_options.DisplayOptionsViewModel;
import interface_adapter.splash_screen_view.SplashScreenViewModel;
import use_case.choose_options.ChooseOptionsOutputBoundary;
import use_case.choose_options.ChooseOptionsOutputData;
import use_case.use_current_location.UseCurrentLocationOutputBoundary;
import use_case.use_current_location.UseCurrentLocationOutputData;

import java.util.List;
import java.util.Map;

public class ChooseOptionsPresenter implements ChooseOptionsOutputBoundary, UseCurrentLocationOutputBoundary {

    private final ChooseOptionsViewModel chooseOptionsViewModel;
    private final SplashScreenViewModel splashScreenViewModel;
    private final DisplayOptionsViewModel displayOptionsViewModel;
    private final ViewManagerModel viewManagerModel;
    public ChooseOptionsPresenter(ViewManagerModel viewManagerModel,
                                  ChooseOptionsViewModel chooseOptionsViewModel,
                                  SplashScreenViewModel splashScreenViewModel,
                                  DisplayOptionsViewModel displayOptionsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.chooseOptionsViewModel = chooseOptionsViewModel;
        this.splashScreenViewModel = splashScreenViewModel;
        this.displayOptionsViewModel = displayOptionsViewModel;
    }

    @Override
    public void prepareSuccessView(ChooseOptionsOutputData outputData) {
        final DisplayOptionsState displayOptionsState = displayOptionsViewModel.getState();
        displayOptionsState.setCheckedLocationList(outputData.getCheckedLocationList());
        displayOptionsViewModel.setState(displayOptionsState);
        displayOptionsViewModel.firePropertyChanged();

        final ChooseOptionsState chooseOptionsState = chooseOptionsViewModel.getState();
        chooseOptionsState.setErrorText("");
        chooseOptionsViewModel.firePropertyChanged();

        viewManagerModel.setState(displayOptionsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final ChooseOptionsState chooseOptionsState = chooseOptionsViewModel.getState();
        chooseOptionsState.setErrorText(errorMessage);
        chooseOptionsViewModel.firePropertyChanged();
    }

    @Override
    public void switchToPreviousView() {
        viewManagerModel.setState(splashScreenViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void updateCurrentLocation(UseCurrentLocationOutputData outputData) {
        final ChooseOptionsState chooseOptionsState = chooseOptionsViewModel.getState();
        chooseOptionsState.setStartingAddress(outputData.getCurrentLocation());
        chooseOptionsViewModel.firePropertyChanged();
    }
}

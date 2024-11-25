package interface_adapter.splash_screen_view;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_options.ChooseOptionsState;
import interface_adapter.choose_options.ChooseOptionsViewModel;
import use_case.start_app.StartAppOutputBoundary;
import use_case.start_app.StartAppOutputData;

public class SplashScreenPresenter implements StartAppOutputBoundary {
    private final ChooseOptionsViewModel chooseOptionsViewModel;
    private final ViewManagerModel viewManagerModel;

    public SplashScreenPresenter(ViewManagerModel viewManagerModel,
                                 ChooseOptionsViewModel splashScreenViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.chooseOptionsViewModel = splashScreenViewModel;
    }

    @Override
    public void prepareView(StartAppOutputData response) {
        final ChooseOptionsState chooseOptionsState = chooseOptionsViewModel.getState();
        chooseOptionsState.setStartingLocation(response.getLocation());
        this.chooseOptionsViewModel.setState(chooseOptionsState);
        this.chooseOptionsViewModel.firePropertyChanged();

        this.viewManagerModel.setState(chooseOptionsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}

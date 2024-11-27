package interface_adapter.splash_screen_view;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_options.ChooseOptionsViewModel;
import use_case.start_app.StartAppOutputBoundary;

public class SplashScreenPresenter implements StartAppOutputBoundary {
    private final ChooseOptionsViewModel chooseOptionsViewModel;
    private final ViewManagerModel viewManagerModel;

    public SplashScreenPresenter(ViewManagerModel viewManagerModel,
                                 ChooseOptionsViewModel chooseOptionsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.chooseOptionsViewModel = chooseOptionsViewModel;
    }

    @Override
    public void prepareView() {
        viewManagerModel.setState(chooseOptionsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

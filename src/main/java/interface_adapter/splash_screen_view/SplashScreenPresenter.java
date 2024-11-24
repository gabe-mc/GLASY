package interface_adapter.splash_screen_view;

import interface_adapter.ViewManagerModel;

public class SplashScreenPresenter {
    private final SplashScreenViewModel splashScreenViewModel;
    private final ViewManagerModel viewManagerModel;

    public SplashScreenPresenter(ViewManagerModel viewManagerModel,
                             SplashScreenViewModel splashScreenViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.splashScreenViewModel = splashScreenViewModel;
    }
}

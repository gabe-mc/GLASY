package interface_adapter.splashscreenview;

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

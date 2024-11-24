package interface_adapter.splashscreenview;

import interface_adapter.ViewModel;

public class SplashScreenViewModel extends ViewModel<SplashScreenState> {

    public SplashScreenViewModel() {
        super("Splash Screen View");
        setState(new SplashScreenState());
    }
}

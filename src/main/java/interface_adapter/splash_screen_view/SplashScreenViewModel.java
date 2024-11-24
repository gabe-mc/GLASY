package interface_adapter.splash_screen_view;

import interface_adapter.ViewModel;

public class SplashScreenViewModel extends ViewModel<SplashScreenState> {

    public SplashScreenViewModel() {
        super("Splash Screen View");
        setState(new SplashScreenState());
    }
}

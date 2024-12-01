package interface_adapter.splash_screen_view;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Splash Screen View.
 */
public class SplashScreenViewModel extends ViewModel<SplashScreenState> {

    public SplashScreenViewModel() {
        super("splash screen");
        setState(new SplashScreenState());
    }
}

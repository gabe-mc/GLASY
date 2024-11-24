package interface_adapter.displayresultsview;

import interface_adapter.ViewModel;
import interface_adapter.splashscreenview.SplashScreenState;

public class DisplayResultsViewModel extends ViewModel<DisplayResultsState> {

    public DisplayResultsViewModel() {
        super("Splash Screen View");
        setState(new DisplayResultsState());
    }

}

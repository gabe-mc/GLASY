package interface_adapter;

public class SplashScreenViewModel extends ViewModel<SplashScreenState> {

    public SplashScreenViewModel() {
        super("SplashScreen");
        setState(new SplashScreenState());
    }

}

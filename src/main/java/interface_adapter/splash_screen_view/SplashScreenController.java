package interface_adapter.splash_screen_view;

import use_case.start_app.StartAppInputBoundary;

public class SplashScreenController {

    private final StartAppInputBoundary startAppUseCaseInteractor;

    public SplashScreenController(StartAppInputBoundary startAppUseCaseInteractor) {
        this.startAppUseCaseInteractor = startAppUseCaseInteractor;
    }

    public void execute() {
        startAppUseCaseInteractor.execute();
    }
}

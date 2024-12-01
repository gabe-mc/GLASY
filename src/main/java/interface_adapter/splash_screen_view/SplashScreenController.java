package interface_adapter.splash_screen_view;

import use_case.start_app.StartAppInputBoundary;

/**
 * Controller for the Splash Screen View.
 */
public class SplashScreenController {

    private final StartAppInputBoundary startAppUseCaseInteractor;

    public SplashScreenController(StartAppInputBoundary startAppUseCaseInteractor) {
        this.startAppUseCaseInteractor = startAppUseCaseInteractor;
    }

    /**
     * Executes the Start App Use Case.
     */
    public void execute() {
        startAppUseCaseInteractor.execute();
    }
}

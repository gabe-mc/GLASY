package use_case.start_app;

import entity.CommonLocationData;
import entity.User;

/**
 * The Start App Interactor.
 */
public class StartAppInteractor implements StartAppInputBoundary {
    private final StartAppCurrentLocationDataAccessInterface currentLocationDataAccessObject;
    private final StartAppUserDataAccessInterface userDataAccessObject;
    private final StartAppOutputBoundary startAppPresenter;

    public StartAppInteractor(StartAppCurrentLocationDataAccessInterface currentLocationDataAccessObject,
                              StartAppUserDataAccessInterface userDataAccessObject,
                              StartAppOutputBoundary startAppPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.startAppPresenter = startAppPresenter;
        this.currentLocationDataAccessObject = currentLocationDataAccessObject;
    }

    @Override
    public void execute() {
        final CommonLocationData currentLocation = currentLocationDataAccessObject.getLocation();
        userDataAccessObject.setCurrentLocation(currentLocation);
        final StartAppOutputData startAppOutputData = new StartAppOutputData(currentLocation);
        startAppPresenter.prepareView(startAppOutputData);
    }
}

package use_case.start_app;

import entity.CommonLocationData;

/**
 * The Start App Interactor.
 */
public class StartAppInteractor implements StartAppInputBoundary {
    private final StartAppCurrentLocationDataAccessInterface currentLocationProvider;
    private final StartAppUserDataAccessInterface userDataAccessObject;
    private final StartAppOutputBoundary startAppPresenter;

    public StartAppInteractor(StartAppCurrentLocationDataAccessInterface startAppCurrentLocationDataAccessInterface,
                              StartAppUserDataAccessInterface userDataAccessInterface,
                              StartAppOutputBoundary startAppPresenter) {
        this.userDataAccessObject = userDataAccessInterface;
        this.startAppPresenter = startAppPresenter;
        this.currentLocationProvider = startAppCurrentLocationDataAccessInterface;
    }

    @Override
    public void execute() {
        final CommonLocationData currentLocation = currentLocationProvider.getLocation();
        userDataAccessObject.setCurrentLocation(currentLocation);
        final StartAppOutputData startAppOutputData = new StartAppOutputData(currentLocation);
        startAppPresenter.prepareView(startAppOutputData);
    }
}

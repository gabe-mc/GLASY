package use_case.start_app;

import entity.AttractionData;

/**
 * The Start App Interactor.
 */
public class StartAppInteractor implements StartAppInputBoundary {
    private final StartAppCurrentLocationProviderInterface currentLocationProvider;
    private final StartAppUserDataAccessInterface userDataAccessObject;
    private final StartAppOutputBoundary startAppPresenter;

    public StartAppInteractor(StartAppCurrentLocationProviderInterface startAppCurrentLocationProviderInterface,
                              StartAppUserDataAccessInterface userDataAccessInterface,
                              StartAppOutputBoundary startAppPresenter) {
        this.userDataAccessObject = userDataAccessInterface;
        this.startAppPresenter = startAppPresenter;
        this.currentLocationProvider = startAppCurrentLocationProviderInterface;
    }

    @Override
    public void execute() {
        final AttractionData currentLocation = currentLocationProvider.getLocation();
        userDataAccessObject.setCurrentLocation(currentLocation);
        startAppPresenter.prepareView();
    }
}

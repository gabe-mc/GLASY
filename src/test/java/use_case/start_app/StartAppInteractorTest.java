package use_case.start_app;

import data_access.CurrentLocationProvider;
import data_access.UserDataAccessObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StartAppInteractorTest {
    @Test
    void successTest() {
        CurrentLocationProvider currentLocationProvider = new CurrentLocationProvider();
        UserDataAccessObject userDataAccessObject = new UserDataAccessObject();

        StartAppOutputBoundary successPresenter = new StartAppOutputBoundary() {
            @Override
            public void prepareView() {
                assertEquals(currentLocationProvider.getLocation(), userDataAccessObject.getCurrentLocation());
            }
        };

        StartAppInputBoundary interactor = new StartAppInteractor(currentLocationProvider,
                userDataAccessObject, successPresenter);
        interactor.execute();
    }
}

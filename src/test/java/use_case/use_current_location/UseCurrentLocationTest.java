package use_case.use_current_location;

import data_access.CurrentLocationProvider;
import data_access.UserDataAccessObject;
import entity.AttractionData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UseCurrentLocationTest {
    @Test
    void successTest() {
        CurrentLocationProvider currentLocationProvider = new CurrentLocationProvider();
        UserDataAccessObject userDataAccessObject = new UserDataAccessObject();
        AttractionData currentLocation = currentLocationProvider.getLocation();
        userDataAccessObject.setCurrentLocation(currentLocation);

        UseCurrentLocationOutputBoundary successPresenter = new UseCurrentLocationOutputBoundary() {
            @Override
            public void updateCurrentLocation(UseCurrentLocationOutputData outputData) {
                assertEquals(currentLocation.getAddress(), outputData.getCurrentLocation());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        UseCurrentLocationInputBoundary interactor = new UseCurrentLocationInteractor(userDataAccessObject,
                successPresenter);
        interactor.useCurrentLocation();
    }
}

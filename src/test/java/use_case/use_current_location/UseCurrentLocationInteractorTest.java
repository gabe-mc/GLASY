package use_case.use_current_location;

import entity.CommonLocationData;
import entity.LocationData;
import org.junit.jupiter.api.Test;

import javax.xml.stream.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UseCurrentLocationInteractorTest {

    @Test
    public void successTest(){
        final UseCurrentLocationUserDataAccessInterface userDataAccessObject = new UseCurrentLocationUserDataAccessInterface() {
            @Override
            public LocationData getCurrentLocation() {
                return null;
            }
        };
        final UseCurrentLocationOutputBoundary chooseOptionsPresenter;
        LocationData currentLocation = userDataAccessObject.getCurrentLocation();
        UseCurrentLocationOutputBoundary outputBoundary = new UseCurrentLocationOutputBoundary() {
            @Override
            public void updateCurrentLocation(UseCurrentLocationOutputData outputData) {
                assertEquals(currentLocation, outputData.getCurrentLocation());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("UseCurrentLocation failed");
            }
        };
    }
}

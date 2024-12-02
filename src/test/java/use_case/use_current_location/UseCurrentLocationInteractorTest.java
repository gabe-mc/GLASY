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
//        CommonLocationData currentLocation = new CommonLocationData();
//        currentLocation.setAddress("197 Yonge Street");
//        currentLocation.setCountry("Canada");
//        currentLocation.setLongitude(1.0);
//        currentLocation.setLatitude(2.0);

        LocationData currentLocation = userDataAccessObject.getCurrentLocation();
        UseCurrentLocationOutputBoundary outputBoundary = new UseCurrentLocationOutputBoundary() {
            @Override
            public void updateCurrentLocation(UseCurrentLocationOutputData outputData) {
                assertEquals(currentLocation.);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("UseCurrentLocation failed");
            }
        };
    }
}

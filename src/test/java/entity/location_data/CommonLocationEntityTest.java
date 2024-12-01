package entity.location_data;

import entity.CommonLocationData;
import entity.LocationData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommonLocationEntityTest {

    @Test
    void SuccessTest() {

        LocationData testLocation = new CommonLocationData();

        testLocation.setLatitude(1.0);
        testLocation.setLongitude(2.0);
        testLocation.setAddress("197 Yonge Street");
        testLocation.setCountry("Canada");
        testLocation.setLocality("ON");
        testLocation.setPostcode("M5S 1K9");
        testLocation.setRegion("Northwest");

        Assertions.assertEquals(testLocation.getLatitude(), 1.0);
        Assertions.assertEquals(testLocation.getLongitude(), 2.0);
        Assertions.assertEquals(testLocation.getAddress(), "197 Yonge Street");
        Assertions.assertEquals(testLocation.getCountry(), "Canada");
        Assertions.assertEquals(testLocation.getLocality(), "ON");
        Assertions.assertEquals(testLocation.getPostcode(), "M5S 1K9");
        Assertions.assertEquals(testLocation.getRegion(), "Northwest");

    }
}

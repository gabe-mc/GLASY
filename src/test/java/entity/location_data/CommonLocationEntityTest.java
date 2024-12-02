package entity.location_data;

import entity.CommonLocationData;
import entity.LocationData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class CommonLocationEntityTest {

    @Test
    void successTest() {
        LocationData testLocation = new CommonLocationData();

        testLocation.setLatitude(1.0);
        testLocation.setLongitude(2.0);
        testLocation.setAddress("197 Yonge Street");
        testLocation.setCountry("Canada");
        testLocation.setLocality("ON");
        testLocation.setPostcode("M5S 1K9");
        testLocation.setRegion("Northwest");

        assertEquals(1.0, testLocation.getLatitude());
        assertEquals(2.0, testLocation.getLongitude());
        assertEquals("197 Yonge Street", testLocation.getAddress());
        assertEquals("Canada", testLocation.getCountry());
        assertEquals("ON", testLocation.getLocality());
        assertEquals("M5S 1K9", testLocation.getPostcode());
        assertEquals("Northwest", testLocation.getRegion());

    }
}

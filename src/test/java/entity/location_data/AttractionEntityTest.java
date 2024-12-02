package entity.location_data;

import entity.AttractionData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class AttractionEntityTest {

    @Test
    void successTest() {
        AttractionData testAttraction = new AttractionData();

        testAttraction.setLatitude(1.0);
        testAttraction.setLongitude(2.0);
        testAttraction.setAddress("197 Yonge Street");
        testAttraction.setCountry("Canada");
        testAttraction.setLocality("ON");
        testAttraction.setPostcode("M5S 1K9");
        testAttraction.setRegion("Northwest");
        ArrayList<Double> hours = new ArrayList<>();
        hours.add(0.700);
        hours.add(0.2300);
        testAttraction.setName("County Pool");

        assertEquals(1.0, testAttraction.getLatitude());
        assertEquals(2.0, testAttraction.getLongitude());
        assertEquals("197 Yonge Street", testAttraction.getAddress());
        assertEquals("Canada", testAttraction.getCountry());
        assertEquals("ON", testAttraction.getLocality());
        assertEquals("M5S 1K9", testAttraction.getPostcode());
        assertEquals("Northwest", testAttraction.getRegion());
        assertEquals("County Pool", testAttraction.getName());
    }

}

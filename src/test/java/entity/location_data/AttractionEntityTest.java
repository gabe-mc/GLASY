package entity.location_data;

import entity.AttractionData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AttractionEntityTest {

    @Test
    void SuccessTest() {

        AttractionData testAttraction = new AttractionData();

        testAttraction.setLatitude(1.0);
        testAttraction.setLongitude(2.0);
        testAttraction.setAddress("197 Yonge Street");
        testAttraction.setCountry("United States");
        testAttraction.setLocality("ON");
        testAttraction.setPostcode("M5S 1K9");
        testAttraction.setRegion("Northwest");
        ArrayList<Double> hours = new ArrayList<>();
        hours.add(0.700);
        hours.add(0.2300);
        testAttraction.setHoursOfOperation(hours);
        testAttraction.setName("County Pool");

        Assertions.assertEquals(testAttraction.getLatitude(), 1.0);
        Assertions.assertEquals(testAttraction.getLongitude(), 2.0);
        Assertions.assertEquals(testAttraction.getAddress(), "197 Yonge Street");
        Assertions.assertEquals(testAttraction.getCountry(), "United States");
        Assertions.assertEquals(testAttraction.getLocality(), "ON");
        Assertions.assertEquals(testAttraction.getPostcode(), "M5S 1K9");
        Assertions.assertEquals(testAttraction.getRegion(), "Northwest");
        Assertions.assertEquals(testAttraction.getHoursOfOperation(), hours);
        Assertions.assertEquals(testAttraction.getName(), "County Pool");

    }

}

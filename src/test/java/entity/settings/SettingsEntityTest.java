package entity.settings;

import entity.AttractionData;
import entity.Settings;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class SettingsEntityTest {

    @Test
    void successTest() {
        AttractionData attraction = new AttractionData();
        attraction.setAddress("197 Yonge Street");
        attraction.setTravelTime(12);
        attraction.setName("Pool");
        attraction.setCountry("Canada");
        Map<String, Boolean> possibleLocationTypes = new HashMap<>();
        possibleLocationTypes.put("Restaurant", true);
        possibleLocationTypes.put("Attraction", false);
        possibleLocationTypes.put("Shop", true);

        int maxDistance = 8;
        double minStars = 9.0; // From 0.0 to 10.0
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(Calendar.HOUR_OF_DAY, 12);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND, 0);
        startCalendar.set(Calendar.MILLISECOND, 0);
        Date startTime = startCalendar.getTime();
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(Calendar.HOUR_OF_DAY, 12);
        endCalendar.set(Calendar.MINUTE, 0);
        endCalendar.set(Calendar.SECOND, 0);
        endCalendar.set(Calendar.MILLISECOND, 0);
        Date endTime = endCalendar.getTime();
        Settings settings = new Settings(attraction, maxDistance, minStars, startTime, endTime, possibleLocationTypes);

        assertEquals(attraction, settings.getLocation());
        assertEquals(maxDistance, settings.getMaxDistance());
        assertEquals(minStars, settings.getMinStars());
        assertEquals(startTime, settings.getStartTime());
        assertEquals(endTime, settings.getEndTime());
        assertEquals(possibleLocationTypes, settings.getPossibleLocationTypes());
    }

}

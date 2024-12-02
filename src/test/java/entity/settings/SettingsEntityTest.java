package entity.settings;

import entity.AttractionData;
import entity.Settings;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SettingsEntityTest {
    @Test
    public void successTest() {
        AttractionData attractionData = new AttractionData();
        attractionData.setName("test");
        int maxDistance = 10;
        int minStarts = 2;
        Date startDate = new Date();
        Date endDate = new Date();
        Map<String, Boolean> map = new HashMap<>();
        map.put("test", true);
        Settings settings = new Settings(attractionData, maxDistance, minStarts, startDate, endDate, map);

        Assertions.assertEquals(settings.getMaxDistance(), maxDistance);
        Assertions.assertEquals(settings.getLocation(), attractionData);
        Assertions.assertEquals(settings.getEndTime(), endDate);
        Assertions.assertEquals(settings.getStartTime(), startDate);
        Assertions.assertEquals(settings.getPossibleLocationTypes(), map);

    }
}

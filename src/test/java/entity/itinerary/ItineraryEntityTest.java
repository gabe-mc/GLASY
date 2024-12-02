package entity.itinerary;

import entity.CommonLocationData;
import entity.Itinerary;
import entity.LocationData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

class ItineraryEntityTest {

    @Test
    void successTest() {

        Itinerary itinerary = new Itinerary();

        LocalDateTime dateTime = LocalDateTime.now();
        itinerary.setStartTime(dateTime.minusDays(1));
        itinerary.setEndTime(dateTime);

        ArrayList<LocationData> locationDataList = new ArrayList<>();
        CommonLocationData commonLocationData = new CommonLocationData();
        commonLocationData.setLatitude(51.55);
        locationDataList.add(commonLocationData);
        itinerary.setEvents(locationDataList);

        ArrayList<LocalDateTime> dateTimeList = new ArrayList<>();
        dateTimeList.add(dateTime.plusDays(1));
        itinerary.setTravelTimeBetween(dateTimeList);

        assertEquals(dateTime, itinerary.getEndTime());
        assertEquals(dateTime.minusDays(1), itinerary.getStartTime());
        assertEquals(dateTimeList, itinerary.getTravelTimeBetween());
        assertEquals(locationDataList, itinerary.getEvents());

    }

}

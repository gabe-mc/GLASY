package entity.itinerary;

import entity.CommonLocationData;
import entity.Itinerary;
import entity.LocationData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ItineraryEntityTest {

    @Test
    void SuccessTest() {

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

        Assertions.assertEquals(itinerary.getEndTime(), dateTime);
        Assertions.assertEquals(itinerary.getStartTime(), dateTime.minusDays(1));
        Assertions.assertEquals(itinerary.getTravelTimeBetween(), dateTimeList);
        Assertions.assertEquals(itinerary.getEvents(), locationDataList);

    }

}

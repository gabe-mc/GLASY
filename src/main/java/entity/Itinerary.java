package entity;

import java.time.LocalDate;
import java.util.List;
import java.time.LocalDateTime; // Import the LocalDateTime class

public class Itinerary {
    private List<LocationData> events;
    private List<LocalDateTime> travelTimeBetween;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Itinerary () {}

    public List<LocationData> getEvents() {
        return events;
    }

    public void setEvents(List<LocationData> events) {
        this.events = events;
    }

    public List<LocalDateTime> getTravelTimeBetween() {
        return travelTimeBetween;
    }

    public void setTravelTimeBetween(List<LocalDateTime> travelTimeBetween) {
        this.travelTimeBetween = travelTimeBetween;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}

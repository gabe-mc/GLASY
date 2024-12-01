package entity;

import java.util.Date;
import java.util.Map;

public class Settings {
    private AttractionData location;
    private int maxDistance;
    private double minStars; // From 0.0 to 10.0
    private Date startTime;
    private Date endTime;
    private Map<String, Boolean> possibleLocationTypes;

    public Settings(AttractionData location, int maxDistance, double minStars, Date startTime,
                                  Date endTime, Map<String, Boolean> possibleLocationTypes) {
        this.location = location;
        this.maxDistance = maxDistance;
        this.minStars = minStars;
        this.startTime = startTime;
        this.endTime = endTime;
        this.possibleLocationTypes = possibleLocationTypes;
    }

    public AttractionData getLocation() {
        return location;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public double getMinStars() {
        return minStars;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Map<String, Boolean> getPossibleLocationTypes() {
        return possibleLocationTypes;
    }
}

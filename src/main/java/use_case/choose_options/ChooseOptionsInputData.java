package use_case.choose_options;

import entity.CommonLocationData;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The input data for the Find Shortest Path Use Case.
 */
public class ChooseOptionsInputData {
    private final String startingAddress;
    private final int maxDistance;
    private final double minStars;
    private final Date startTime;
    private final Date endTime;
    private final Map<String, Boolean> possibleLocationTypes;

    public ChooseOptionsInputData(String startingAddress, int maxDistance, double minStars, Date startTime,
                                  Date endTime, Map<String, Boolean> possibleLocationTypes) {
        this.startingAddress = startingAddress;
        this.maxDistance = maxDistance;
        this.minStars = minStars;
        this.startTime = startTime;
        this.endTime = endTime;
        this.possibleLocationTypes = possibleLocationTypes;
    }

    public String getStartingAddress() {
        return startingAddress;
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

package interface_adapter.choose_options;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChooseOptionsState {
    private String startingAddress;
    private int maxDistance = 0;
    private double minStars = 0.0;
    private Date startTime = new Date();
    private Date endTime = new Date();
    private final Map<String, Boolean> possibleLocationTypes = new HashMap<>();

    public String getStartingAddress() {
        return startingAddress;
    }
    public int getMaxDistance() {
        return maxDistance;
    }
    public double getMinStars() {
        return minStars;
    }
    public Map<String, Boolean> getPossibleLocationTypes() { return possibleLocationTypes; }
    public Date getStartTime() { return startTime; }
    public Date getEndTime() { return endTime; }

    public void setStartingAddress(String startingAddress) {
        this.startingAddress = startingAddress;
    }
    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }
    public void setMinStars(double minStars) {
        this.minStars = minStars;
    }
    public void setPossibleLocationTypes(String possibleLocationType, Boolean checked) {
        this.possibleLocationTypes.put(possibleLocationType, checked);
    }
    public void setStartTime(Date startTime) { this.startTime = startTime; }
    public void setEndTime(Date endTime) { this.endTime = endTime; }
}

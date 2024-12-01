package interface_adapter.choose_options;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The state for the Choose Options View Model.
 */
public class ChooseOptionsState {
    private String startingAddress = "";
    private int maxDistance = 10;
    private int minStars = 0;
    private Date startTime = new Date();
    private Date endTime = new Date();
    private Map<String, Boolean> possibleLocationTypes = new HashMap<>();
    private String errorText;

    public String getStartingAddress() {
        return startingAddress;
    }
    public int getMaxDistance() {
        return maxDistance;
    }
    public int getMinStars() {
        return minStars;
    }
    public Map<String, Boolean> getPossibleLocationTypes() { return possibleLocationTypes; }
    public Date getStartTime() { return startTime; }
    public Date getEndTime() { return endTime; }
    public String getErrorText() {
        return errorText;
    }
    public void setStartingAddress(String startingAddress) {
        this.startingAddress = startingAddress;
    }
    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }
    public void setMinStars(int minStars) {
        this.minStars = minStars;
    }
    public void setPossibleLocationType(String possibleLocationType, Boolean checked) {
        this.possibleLocationTypes.put(possibleLocationType, checked);
    }
    public void setStartTime(Date startTime) { this.startTime = startTime; }
    public void setEndTime(Date endTime) { this.endTime = endTime; }
    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }
}

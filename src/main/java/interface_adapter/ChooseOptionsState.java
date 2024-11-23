package interface_adapter;

import entity.CommonLocationData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChooseOptionsState {
    private CommonLocationData startingLocation = new CommonLocationData();
    private double maxDistance = 0.0;
    private double minStars = 0.0;
    private SimpleDateFormat startTime = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat endTime = new SimpleDateFormat("HH:mm");
    private List<String> possibleLocationTypes = new ArrayList<String>();

    public CommonLocationData getStartingLocation() {
        return startingLocation;
    }
    public double getMaxDistance() {
        return maxDistance;
    }
    public double getMinStars() {
        return minStars;
    }
    public List<String> getPossibleLocationTypes() {
        return possibleLocationTypes;
    }

    public void setStartingLocation(CommonLocationData startingLocation) {
        this.startingLocation = startingLocation;
    }
    public void setMaxDistance(double maxDistance) {
        this.maxDistance = maxDistance;
    }
    public void setMinStars(double minStars) {
        this.minStars = minStars;
    }
    public void setPossibleLocationTypes(List<String> possibleLocationTypes) {
        this.possibleLocationTypes = possibleLocationTypes;
    }



}

package use_case.set_user_info;

import entity.AttractionData;
import entity.CommonLocationData;

/**
 * The input data for the Find Shortest Path Use Case.
 */
public class SetUserInfoInputData {
    private final double timeAvailable;
    private final CommonLocationData userLocation;
    private final String preferredAttraction;

    public SetUserInfoInputData(double timeAvailable, CommonLocationData userLocation,
                                String preferredAttraction) {
        this.timeAvailable = timeAvailable;
        this.userLocation = userLocation;
        this.preferredAttraction = preferredAttraction;
    }

    public double getUserTimeAvailable() {return timeAvailable;}

    public CommonLocationData getUserLocation() {return userLocation;}

    public String getUserPreferredAttraction() {return preferredAttraction;}
}

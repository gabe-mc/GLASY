package use_case.use_current_location;

public class UseCurrentLocationOutputData {
    private String currentLocation;

    public UseCurrentLocationOutputData(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }
}
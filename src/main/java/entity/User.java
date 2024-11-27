package entity;

/**
 * The representation of a user in our program, mostly containing user's preferences.
 */
public class User {

    private CommonLocationData currentLocation;
    private Settings settings;

    public CommonLocationData getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(CommonLocationData currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}

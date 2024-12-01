package entity;

import java.awt.*;

/**
 * The representation of a user in our program, mostly containing user's preferences.
 */
public class User {

    private AttractionData currentLocation;
    private AttractionData startingLocation;
    private Settings settings;
    private Image mapImage;

    public AttractionData getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(AttractionData currentLocation) {
        this.currentLocation = currentLocation;
    }

    public AttractionData getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(AttractionData startingLocation) {
        this.startingLocation = startingLocation;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Image getMapImage() {
        return mapImage;
    }

    public void setMapImage(Image mapImage) {
        this.mapImage = mapImage;
    }
}

package entity;

/**
 * The representation of a user in our program, mostly containing user's preferences.
 */
public class User {

    private double timeAvailable;
    private LocationData userLocation;
    private String attractionCategory;

    public Double getTimeAvailable() {return timeAvailable;}

    public void setTimeAvailable(double timeAvailable) {this.timeAvailable = timeAvailable;}

    public LocationData getUserLocation() {return userLocation;}

    public void setUserLocation(LocationData userLocation) {this.userLocation = userLocation;}

    public String getAttractionCategory() {return attractionCategory;}

    public void setAttractionCategory(String attractionCategory) {this.attractionCategory = attractionCategory;}
}
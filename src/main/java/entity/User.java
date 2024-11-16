package entity;

/**
 * The representation of a user in our program, mostly containing user's preferences.
 */
public class User {

    private double timeAvailable;
    private Location userLocation;
    private String attractionCategory;

    public Double getTimeAvailable() {return timeAvailable;}

    public void setTimeAvailable(double timeAvailable) {this.timeAvailable = timeAvailable;}

    public Location getUserLocation() {return userLocation;}

    public void setUserLocation(Location userLocation) {this.userLocation = userLocation;}

    public String getAttractionCategory() {return attractionCategory;}

    public void setAttractionCategory(String attractionCategory) {this.attractionCategory = attractionCategory;}
}
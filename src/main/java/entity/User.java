package entity;

/**
 * The representation of a user in our program, mostly containing user's preferences.
 */
public class User {

    private double timeAvailable;
    private CommonLocationData userLocation;
    private String attractionCategory;

    public Double getTimeAvailable() {return timeAvailable;}

    public void setTimeAvailable(double timeAvailable) {this.timeAvailable = timeAvailable;}

    public CommonLocationData getUserLocation() {return userLocation;}

    public void setUserLocation(CommonLocationData userLocation) {this.userLocation = userLocation;}

    public String getAttractionCategory() {return attractionCategory;}

    public void setAttractionCategory(String attractionCategory) {this.attractionCategory = attractionCategory;}
}
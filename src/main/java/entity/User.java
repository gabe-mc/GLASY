package entity;

/**
 * The representation of a user in our program, mostly containing user's preferences.
 */
public class User {

    private double timeAvailable;
    private CommonLocationData location;
    private String attractionCategory;

    public Double getTimeAvailable() {return timeAvailable;}

    public void setTimeAvailable(double timeAvailable) {this.timeAvailable = timeAvailable;}

    public CommonLocationData getLocation() {return location;}

    public void setLocation(CommonLocationData location) {this.location = location;}

    public String getAttractionCategory() {return attractionCategory;}

    public void setAttractionCategory(String attractionCategory) {this.attractionCategory = attractionCategory;}
}
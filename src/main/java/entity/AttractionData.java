package entity;

import java.util.List;

/**
 * An extension of the CommonLocationData class to include attraction information.
 */
public class AttractionData extends CommonLocationData {
    private String name;
    private List<String> categories;
    private Double rating; // From 0.0 to 10.0
    private int price; // From 1-4, 0 means unknown
    private String photoUrl;
    private int travelTime;
    private String visitTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setTravelTime(int travelTime) {this.travelTime = travelTime;}

    public int getTravelTime() {return travelTime;}

    public void setVisitTime(String time) {this.visitTime = time;}

    public String getVisitTime() {return visitTime;}

}

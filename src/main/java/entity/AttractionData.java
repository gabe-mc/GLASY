package entity;

import java.util.List;

public class AttractionData extends CommonLocationData {
    private String name;
    private List<String> categories;
    private Double rating; // From 0.0 to 10.0
    private int price; // From 1-4, 0 means unknown
    private String photoUrl;
    /* TODO: add type and time spent at location */

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
}

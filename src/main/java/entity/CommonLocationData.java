package entity;

/**
 * A simple implementation of the LocationData interface.
 */
public class CommonLocationData implements LocationData {
    private Double longitude;
    private Double latitude;
    private String address;
    private String locality;
    private String postcode;
    private String region;
    private String country;

    public CommonLocationData(Double longitude, Double latitude, String address, String locality, String postcode,
                              String region, String country) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.locality = locality;
        this.postcode = postcode;
        this.region = region;
        this.country = country;
    }

    public CommonLocationData() {
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}


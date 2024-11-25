package entity;

 interface LocationData {
     Double getLongitude();

     void setLongitude(Double longitude);

     Double getLatitude();

     void setLatitude(Double latitude);

     String getAddress();

     void setAddress(String address);

     String getLocality();

     void setLocality(String locality);

     String getPostcode();

     void setPostcode(String postcode);

     String getRegion();

     void setRegion(String region);

     String getCountry();

     void setCountry(String country);
}

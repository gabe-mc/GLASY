package entity.user;

import entity.AttractionData;
import entity.Settings;
import entity.User;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserEntityTest {

    @Test
    void successTest() {
        User user = new User();
        AttractionData currentLocation = new AttractionData();
        currentLocation.setName("Current Location");
        List<String> categoryNames = Arrays.asList("Restaurant", "Sandwich Shop");
        currentLocation.setCategories(categoryNames);
        currentLocation.setRating(9.0);
        currentLocation.setPrice(2);
        currentLocation.setPhotoUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS41pB_ogITgnAkTES9bnkkXHWhsBER_uxOdw&s");
        currentLocation.setLatitude(40.753);
        currentLocation.setLongitude(-73.983);
        currentLocation.setAddress("27 King's College Cir, Toronto, ON M5S 1A1");
        currentLocation.setLocality("Toronto");
        currentLocation.setPostcode("ON M5S 1A1");
        currentLocation.setRegion("Ontario");
        currentLocation.setCountry("Canada");
        AttractionData startingLocation = currentLocation;
        Settings settings = new Settings(startingLocation,
                10, 3.0, new Date(), new Date(), new HashMap<String, Boolean>());
        Image mapImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);

        user.setCurrentLocation(currentLocation);
        user.setStartingLocation(startingLocation);
        user.setSettings(settings);
        user.setMapImage(mapImage);

        assertEquals(currentLocation, user.getCurrentLocation());
        assertEquals(startingLocation, user.getStartingLocation());
        assertEquals(settings, user.getSettings());
        assertEquals(mapImage, user.getMapImage());

        User newUser = new User();
        assertNull(newUser.getCurrentLocation());
        assertNull(newUser.getStartingLocation());
        assertNull(newUser.getSettings());
        assertNull(newUser.getMapImage());
    }
}

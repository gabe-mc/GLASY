package use_case.choose_options;

import entity.AttractionData;
import entity.LocationData;

import java.util.List;

/**
 * Google Maps Location Provider for the Choose Options Use Case.
 */
public interface ChooseOptionsGoogleMapsLocationProviderInterface {

    /**
     * Returns a LocationData object representing the location at a certain address.
     * @param address The address the location is at
     * @return The LocationData object representing the location
     */
    AttractionData addressToLocation(String address);
}

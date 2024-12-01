package use_case.choose_options;

import entity.AttractionData;
import entity.LocationData;

import java.util.List;

/**
 * The Google Maps Location Provider for the Choose Options Use Case.
 */
public interface ChooseOptionsGoogleMapsLocationProviderInterface {

    /**
     * Returns the location data for a given address by sending a request
     * to a geocoding API and parsing the JSON response.
     * @param address The address to be geocoded.
     * @return Null if no address is found, otherwise the AttractionData object
     * representing the address
     */
    AttractionData addressToLocation(String address);
}

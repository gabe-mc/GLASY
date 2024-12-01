package use_case.choose_options;

import entity.AttractionData;
import entity.LocationData;
import entity.Settings;

import java.util.List;

/**
 * The Foursquare Location Provider for the Choose Options Use Case.
 */
public interface ChooseOptionsFoursquareLocationProviderInterface {

    /**
     * Retrieves location data from the Foursquare Places API based on the provided geographic coordinates
     * and optional query parameters.
     *
     * @param settings A class representing of optional query parameters to refine the search.
     * @return A JSON string representing the locations returned by the Foursquare API. The string is formatted with an
     *         indent factor of 4 for readability.
     */
    List<AttractionData> getLocationList(Settings settings);
}

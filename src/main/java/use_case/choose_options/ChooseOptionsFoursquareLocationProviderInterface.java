package use_case.choose_options;

import entity.AttractionData;
import entity.LocationData;
import entity.Settings;

import java.util.List;

/**
 * Foursquare Location Provider for the Choose Options Use Case.
 */
public interface ChooseOptionsFoursquareLocationProviderInterface {

    /**
     * Returns a list of locations corresponding to the user's query
     * @return a list of locations
     */
    List<AttractionData> getLocationList(Settings settings);
}

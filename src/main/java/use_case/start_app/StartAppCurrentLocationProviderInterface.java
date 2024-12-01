package use_case.start_app;

import entity.AttractionData;

/**
 * The Current Location Provider for the Start App Use Case.
 */
public interface StartAppCurrentLocationProviderInterface {

    /**
     * Returns the location data of the current user of the application
     * @return the location data of the current user
     */
    AttractionData getLocation();
}

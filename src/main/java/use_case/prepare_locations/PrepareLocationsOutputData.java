package use_case.prepare_locations;

import entity.AttractionData;

import java.util.HashMap;

/**
 * The output data for the Prepare Locations Path Use Case.
 */
public class PrepareLocationsOutputData {
    private final HashMap<AttractionData, String> cleanedLocations;

    public PrepareLocationsOutputData(HashMap<AttractionData, String> cleanedLocations) {
        this.cleanedLocations = cleanedLocations;
    }

    public HashMap<AttractionData, String> getCleanedLocations() {
        return cleanedLocations;
    }
}

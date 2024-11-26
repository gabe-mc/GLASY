package use_case.add_location;

import org.json.JSONObject;

/**
 * The interface of the DAO for the Add Location Use Case.
 */
public interface AddLocationDataAccessInterface {

    /**
     * Executes the API call for the system to get location data.
     */
    JSONObject getJSONResults();

    /**
     * Updates the system to record the results from API call to get locations.
     * @param JSONResults the location data is to be updated.
     */
    void setJSONResults(JSONObject JSONResults);
}

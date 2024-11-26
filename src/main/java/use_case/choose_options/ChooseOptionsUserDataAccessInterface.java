package use_case.choose_options;

import entity.CommonLocationData;

import java.util.List;

/**
 * The interface of the DAO for the Choose Options Use Case.
 */
public interface ChooseOptionsUserDataAccessInterface {

    /**
     * Executes the API call for the system to get location data.
     */
    List<CommonLocationData> getLocations();
}
package use_case.start_app;

import entity.AttractionData;

/**
 * The User DAO for the Start App Use Case.
 */
public interface StartAppUserDataAccessInterface {
    /**
     * Sets the current location of the user who is using the application.
     * @param currentLocation The user's current location.
     */
    void setCurrentLocation(AttractionData currentLocation);
}

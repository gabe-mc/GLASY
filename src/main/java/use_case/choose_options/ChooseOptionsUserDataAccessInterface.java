package use_case.choose_options;

import entity.AttractionData;
import entity.Settings;

/**
 * The User DAO for the Choose Options Use Case.
 */
public interface ChooseOptionsUserDataAccessInterface {
    /**
     * Saves the user's settings
     * @param settings the user's inputted settings
     */
    void setSettings(Settings settings);

    /**
     * Saves the user's starting location
     * @param startingLocation the starting location
     */
    void setStartingLocation(AttractionData startingLocation);
}
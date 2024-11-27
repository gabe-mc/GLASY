package use_case.choose_options;

import entity.Settings;

/**
 * The interface of the DAO for the Choose Options Use Case.
 */
public interface ChooseOptionsUserDataAccessInterface {

    void setSettings(Settings settings);
}
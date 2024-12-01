package use_case.compute_time;

import entity.Settings;

/**
 * DAO for the Compute Time Use Case.
 */
public interface ComputeTimeUserDataAccessInterface {
    double getStartTime();

    double getEndTime();

    Settings getSettings();
}

package use_case.compute_time;

import java.awt.*;

import java.util.Date;

/**
 * The User DAO for the Compute Time Use Case.
 */
public interface ComputeTimeUserDataAccessInterface {
    /**
     * Returns the start time of the user
     * @return the start time
     */
    Date getStartTime();

    /**
     * Returns the end time of the user
     * @return the end time
     */
    Date getEndTime();

    /**
     * Returns the image of the generated map
     * @return the image of the map
     */
    Image getMapImage();
}

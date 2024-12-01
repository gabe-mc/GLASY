package use_case.compute_time;

import java.awt.*;

import java.util.Date;

/**
 * DAO for the Compute Time Use Case.
 */
public interface ComputeTimeUserDataAccessInterface {
    Date getStartTime();

    Image getMapImage();

    Date getEndTime();
}

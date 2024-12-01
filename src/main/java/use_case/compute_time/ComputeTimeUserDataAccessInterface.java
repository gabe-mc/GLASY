package use_case.compute_time;

import java.awt.*;

/**
 * DAO for the Compute Time Use Case.
 */
public interface ComputeTimeUserDataAccessInterface {
    double getStartTime();

    double getEndTime();

    Image getMapImage();
}

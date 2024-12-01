package use_case.compute_time;

import java.util.Date;

/**
 * DAO for the Compute Time Use Case.
 */
public interface ComputeTimeUserDataAccessInterface {
    Date getStartTime();

    Date getEndTime();
}

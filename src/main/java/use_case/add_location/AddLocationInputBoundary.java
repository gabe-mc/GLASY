package use_case.add_location;

import java.io.IOException;

/**
 * Add Location Use Case
 */
public interface AddLocationInputBoundary {

    /**
     * Execute the Add Location Use Case.
     * @param addLocationInputData the input data for this use case
     */
    void execute(AddLocationInputData addLocationInputData) throws IOException;
}

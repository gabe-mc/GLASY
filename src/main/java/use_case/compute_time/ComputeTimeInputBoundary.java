package use_case.compute_time;

/**
 * Input Boundary for actions which are related to computing itinerary time.
 */
public interface ComputeTimeInputBoundary {

    /**
     * Executes the compute time use case.
     * @param computeTimeInputData the input data containing the list of locations
     */
    void execute(ComputeTimeInputData computeTimeInputData);
}

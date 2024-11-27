package use_case.compute_time;

/**
 * Input Boundary for actions which are related to computing itinerary time.
 */
public interface ComputeTimeInputBoundary {

    /**
     * Executes the Compute Time Use Case.
     * @param computeTimeInputData the input data
     */
    void execute(ComputeTimeInputData computeTimeInputData);
}

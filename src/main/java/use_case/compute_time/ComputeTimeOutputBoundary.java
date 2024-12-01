package use_case.compute_time;

/**
 * The output boundary for the Compute Time Use Case.
 */
public interface ComputeTimeOutputBoundary {
    /**
     * Prepares the success view for the Compute Time Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ComputeTimeOutputData outputData);


     /** Prepares the failure view for the Compute Time Use Case.
      *  @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}

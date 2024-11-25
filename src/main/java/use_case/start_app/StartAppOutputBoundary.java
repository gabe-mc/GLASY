package use_case.start_app;

/**
 * The output boundary for the Start App Use Case.
 */
public interface StartAppOutputBoundary {
    /**
     * Prepares the success view for the Login Use Case.
     * @param outputData the output data
     */
    void prepareView(StartAppOutputData outputData);
}

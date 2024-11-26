package use_case.choose_options;

/**
 * The output boundary for the Start App Use Case.
 */
public interface ChooseOptionsOutputBoundary {
    /**
     * Prepares the success view for the Choose Options Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ChooseOptionsOutputData outputData);

    /**
     * Prepares the failure view for the Choose Options Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Splash Screen View.
     */
    void switchToPreviousView();
}

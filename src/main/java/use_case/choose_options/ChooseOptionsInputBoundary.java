package use_case.choose_options;

/**
 * Input Boundary for setting a user's information.
 */
public interface ChooseOptionsInputBoundary {

    /**
     * Executes the choose options use case.
     */
    void execute(ChooseOptionsInputData chooseOptionsInputData);

    /**
     * Switches the view to the previous page.
     */
    void switchToPreviousView();
}

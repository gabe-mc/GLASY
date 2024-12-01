package use_case.choose_options;

/**
 * Input Boundary for saving a user's settings.
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

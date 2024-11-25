package use_case.choose_options;

import entity.CommonLocationData;

/**
 * Input Boundary for setting a user's information.
 */
public interface ChooseOptionsInputBoundary {

    /**
     * Executes the choose options use case.
     */
    void execute(ChooseOptionsInputData chooseOptionsInputData);
}

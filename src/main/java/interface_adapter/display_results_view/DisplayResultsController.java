package interface_adapter.display_results_view;

import use_case.choose_options.ChooseOptionsInputBoundary;

public class DisplayResultsController {

    private final ChooseOptionsInputBoundary chooseOptionsUseCaseInteractor;

    public DisplayResultsController(ChooseOptionsInputBoundary chooseOptionsUseCaseInteractor) {
        this.chooseOptionsUseCaseInteractor = chooseOptionsUseCaseInteractor;
    }

    public void execute(){
//        chooseOptionsUseCaseInteractor.execute();
    }
}

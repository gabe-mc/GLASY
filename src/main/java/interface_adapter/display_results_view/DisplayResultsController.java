package interface_adapter.display_results_view;

import use_case.add_location.AddLocationInputBoundary;

public class DisplayResultsController {

    private final AddLocationInputBoundary addLocationUseCaseInteractor;

    public DisplayResultsController(AddLocationInputBoundary addLocationUseCaseInteractor) {
        this.addLocationUseCaseInteractor = addLocationUseCaseInteractor;
    }

    public void execute(){
        addLocationUseCaseInteractor.execute();
    }
}

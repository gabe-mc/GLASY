package interface_adapter.display_itinerary_view;

import use_case.choose_options.ChooseOptionsInputBoundary;

public class DisplayItineraryController {

    private final ChooseOptionsInputBoundary chooseOptionsUseCaseInteractor;

    public DisplayItineraryController(ChooseOptionsInputBoundary chooseOptionsUseCaseInteractor) {
        this.chooseOptionsUseCaseInteractor = chooseOptionsUseCaseInteractor;
    }

    public void execute(){
//        chooseOptionsUseCaseInteractor.execute();
    }
}

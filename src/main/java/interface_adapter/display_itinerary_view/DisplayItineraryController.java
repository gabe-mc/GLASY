package interface_adapter.display_itinerary_view;

import use_case.choose_options.ChooseOptionsInputBoundary;
import use_case.save_itinerary.SaveItineraryInputBoundary;

public class DisplayItineraryController {

    private final SaveItineraryInputBoundary saveItineraryUseCaseInteractor;

    public DisplayItineraryController(SaveItineraryInputBoundary saveItineraryUseCaseInteractor) {
        this.saveItineraryUseCaseInteractor = saveItineraryUseCaseInteractor;
    }

    public void execute(){
//        chooseOptionsUseCaseInteractor.execute();
    }

    public void switchToPreviousView() { saveItineraryUseCaseInteractor.switchToPreviousView(); }
}

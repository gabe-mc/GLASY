package interface_adapter.display_itinerary_view;

import use_case.save_itinerary.SaveItineraryInputBoundary;

public class DisplayItineraryController {

    private final SaveItineraryInputBoundary saveItineraryUseCaseInteractor;

    public DisplayItineraryController(SaveItineraryInputBoundary saveItineraryUseCaseInteractor) {
        this.saveItineraryUseCaseInteractor = saveItineraryUseCaseInteractor;
    }

    public void execute(){
//        saveItineraryUseCaseInteractor.execute();
    }

    public void switchToPreviousView() { saveItineraryUseCaseInteractor.switchToPreviousView(); }
}

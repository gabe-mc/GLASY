package interface_adapter.display_itinerary_view;

import use_case.choose_options.ChooseOptionsInputBoundary;
import use_case.save_itinerary.SaveItineraryInputBoundary;

public class DisplayItineraryController {

    private final ChooseOptionsInputBoundary chooseOptionsUseCaseInteractor;
    private final SaveItineraryInputBoundary saveItineraryUseCaseInteractor;

    public DisplayItineraryController(ChooseOptionsInputBoundary chooseOptionsUseCaseInteractor,
                                      SaveItineraryInputBoundary saveItineraryUseCaseInteractor) {
        this.chooseOptionsUseCaseInteractor = chooseOptionsUseCaseInteractor;
        this.saveItineraryUseCaseInteractor = saveItineraryUseCaseInteractor;
    }

    public void switchToPreviousView() { chooseOptionsUseCaseInteractor.switchToPreviousView(); }

    public void execute(){
//        chooseOptionsUseCaseInteractor.execute();
    }
}

package interface_adapter.display_itinerary_view;

import use_case.choose_options.ChooseOptionsInputBoundary;
import use_case.save_itinerary.SaveItineraryInputBoundary;
import use_case.save_itinerary.SaveItineraryInputData;

import java.util.List;

/**
 * Controller for the Display Itinerary View.
 */
public class DisplayItineraryController {

    private final SaveItineraryInputBoundary saveItineraryUseCaseInteractor;

    public DisplayItineraryController(SaveItineraryInputBoundary saveItineraryUseCaseInteractor) {
        this.saveItineraryUseCaseInteractor = saveItineraryUseCaseInteractor;
    }

    /**
     * Executes the Save Itinerary Use Case.
     * @param info The information to be saved
     * @param filePath The filepath to save the itinerary at
     */
    public void execute(List<String[]> info, String filePath) {

        SaveItineraryInputData inputData = new SaveItineraryInputData();
        inputData.setContent(info);
        inputData.setFilePath(filePath);
        inputData.setFileName("GLASY_Itinerary");

        saveItineraryUseCaseInteractor.execute(inputData);
    }

    /**
     * Switches the view to the previous view.
     */
    public void switchToPreviousView() { saveItineraryUseCaseInteractor.switchToPreviousView(); }
}

package interface_adapter.display_itinerary_view;

import use_case.choose_options.ChooseOptionsInputBoundary;
import use_case.save_itinerary.SaveItineraryInputBoundary;
import use_case.save_itinerary.SaveItineraryInputData;

import java.util.List;

public class DisplayItineraryController {

    private final SaveItineraryInputBoundary saveItineraryUseCaseInteractor;

    public DisplayItineraryController(SaveItineraryInputBoundary saveItineraryUseCaseInteractor) {
        this.saveItineraryUseCaseInteractor = saveItineraryUseCaseInteractor;
    }

    public void execute(List<String[]> info, String filePath) {

        SaveItineraryInputData inputData = new SaveItineraryInputData();
        inputData.setContent(info);
        inputData.setFilePath(filePath);
        inputData.setFileName("GLASY_Itinerary");

        saveItineraryUseCaseInteractor.execute(inputData);
    }

    public void switchToPreviousView() { saveItineraryUseCaseInteractor.switchToPreviousView(); }
}

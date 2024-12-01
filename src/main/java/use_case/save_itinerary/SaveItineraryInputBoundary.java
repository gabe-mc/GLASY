package use_case.save_itinerary;

/**
 * Input Boundary for actions which are related to saving files.
 */
public interface SaveItineraryInputBoundary {
     /**
     * Executes the save itinerary use case.
     */
     void saveText(SaveItineraryInputData inputData);
}

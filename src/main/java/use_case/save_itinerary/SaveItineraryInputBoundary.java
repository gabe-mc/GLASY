package use_case.save_itinerary;

/**
 * Input Boundary for actions which are related to saving files.
 */
public interface SaveItineraryInputBoundary {
     /**
     * Executes the save itinerary use case.
     */
     void execute(SaveItineraryInputData inputData);

     /**
      * Switches the view to the previous page.
      */
     void switchToPreviousView();
}

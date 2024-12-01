package use_case.save_itinerary;


/**
 * The output interface for the Save Itinerary Use Case.
 */
public interface SaveItineraryOutputBoundary {
    public void presentOutput(SaveItineraryOutputData outputData);

    /**
     * Switches to the Display Options View.
     */
    void switchToPreviousView();
}


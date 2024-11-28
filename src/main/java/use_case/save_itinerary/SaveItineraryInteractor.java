package use_case.save_itinerary;

public class SaveItineraryInteractor implements SaveItineraryInputBoundary {
    private final SaveItineraryOutputBoundary saveItineraryPresenter;

    public SaveItineraryInteractor(SaveItineraryOutputBoundary saveItineraryPresenter) {
        this.saveItineraryPresenter = saveItineraryPresenter;
    }

    @Override
    public void switchToPreviousView() {
        saveItineraryPresenter.switchToPreviousView();
    }
}

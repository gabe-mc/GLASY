package interface_adapter.display_itinerary_view;

import interface_adapter.ViewManagerModel;
import interface_adapter.display_options.DisplayOptionsViewModel;
import use_case.save_itinerary.SaveItineraryOutputBoundary;
import use_case.save_itinerary.SaveItineraryOutputData;

public class DisplayItineraryPresenter implements SaveItineraryOutputBoundary {
    private final DisplayItineraryViewModel displayItineraryViewModel;
    private final DisplayOptionsViewModel displayOptionsViewModel;
    private final ViewManagerModel viewManagerModel;

    public DisplayItineraryPresenter(ViewManagerModel viewManagerModel,
                                     DisplayItineraryViewModel displayItineraryViewModel,
                                     DisplayOptionsViewModel displayOptionsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.displayOptionsViewModel = displayOptionsViewModel;
        this.displayItineraryViewModel = displayItineraryViewModel;
    }

    @Override
    public void presentOutput(SaveItineraryOutputData outputData) {

    }

    @Override
    public void switchToPreviousView() {
        viewManagerModel.setState(displayOptionsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

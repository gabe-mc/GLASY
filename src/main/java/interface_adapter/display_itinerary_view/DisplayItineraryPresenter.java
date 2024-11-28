package interface_adapter.display_itinerary_view;

import interface_adapter.ViewManagerModel;
import interface_adapter.display_options.DisplayOptionsViewModel;
import use_case.save_itinerary.SaveItineraryOutputBoundary;

public class DisplayItineraryPresenter implements SaveItineraryOutputBoundary {
    private final DisplayOptionsViewModel displayOptionsViewModel;
    private final DisplayItineraryViewModel displayItineraryViewModel;
    private final ViewManagerModel viewManagerModel;

    public DisplayItineraryPresenter(ViewManagerModel viewManagerModel,
                                     DisplayItineraryViewModel displayItineraryViewModel,
                                     DisplayOptionsViewModel displayOptionsViewModel) {
        this.displayOptionsViewModel = displayOptionsViewModel;
        this.viewManagerModel = viewManagerModel;
        this.displayItineraryViewModel = displayItineraryViewModel;
    }

    @Override
    public void switchToPreviousView() {
        viewManagerModel.setState(displayOptionsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

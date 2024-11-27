package interface_adapter.display_itinerary_view;

import interface_adapter.ViewManagerModel;

public class DisplayItineraryPresenter {
    private final DisplayItineraryViewModel displayItineraryViewModel;
    private final ViewManagerModel viewManagerModel;

    public DisplayItineraryPresenter(ViewManagerModel viewManagerModel,
                                     DisplayItineraryViewModel displayItineraryViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.displayItineraryViewModel = displayItineraryViewModel;
    }
}

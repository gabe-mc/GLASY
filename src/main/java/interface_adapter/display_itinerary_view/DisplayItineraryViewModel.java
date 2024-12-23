package interface_adapter.display_itinerary_view;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Display Itinerary View.
 */
public class DisplayItineraryViewModel extends ViewModel<DisplayItineraryState> {

    public DisplayItineraryViewModel() {
        super("display results");
        setState(new DisplayItineraryState());
    }

}

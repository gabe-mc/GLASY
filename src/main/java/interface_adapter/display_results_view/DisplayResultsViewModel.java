package interface_adapter.display_results_view;

import interface_adapter.ViewModel;

public class DisplayResultsViewModel extends ViewModel<DisplayResultsState> {

    public DisplayResultsViewModel() {
        super("display results");
        setState(new DisplayResultsState());
    }

}

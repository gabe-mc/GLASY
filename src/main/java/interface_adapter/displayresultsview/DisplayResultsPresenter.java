package interface_adapter.displayresultsview;

import interface_adapter.ViewManagerModel;
import interface_adapter.displayresultsview.DisplayResultsViewModel;

public class DisplayResultsPresenter {
    private final DisplayResultsViewModel displayResultsViewModel;
    private final ViewManagerModel viewManagerModel;

    public DisplayResultsPresenter(ViewManagerModel viewManagerModel,
                                 DisplayResultsViewModel displayResultsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.displayResultsViewModel = displayResultsViewModel;
    }
}

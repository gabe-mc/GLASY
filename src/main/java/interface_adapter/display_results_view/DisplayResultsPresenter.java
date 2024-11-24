package interface_adapter.display_results_view;

import interface_adapter.ViewManagerModel;

public class DisplayResultsPresenter {
    private final DisplayResultsViewModel displayResultsViewModel;
    private final ViewManagerModel viewManagerModel;

    public DisplayResultsPresenter(ViewManagerModel viewManagerModel,
                                 DisplayResultsViewModel displayResultsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.displayResultsViewModel = displayResultsViewModel;
    }
}

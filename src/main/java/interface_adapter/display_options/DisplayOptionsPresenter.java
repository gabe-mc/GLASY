package interface_adapter.display_options;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_options.ChooseOptionsViewModel;
import interface_adapter.display_results_view.DisplayResultsState;
import interface_adapter.display_results_view.DisplayResultsViewModel;
import use_case.compute_time.ComputeTimeOutputBoundary;
import use_case.compute_time.ComputeTimeOutputData;
import use_case.find_shortest_path.FindShortestPathOutputBoundary;
import use_case.find_shortest_path.FindShortestPathOutputData;

public class DisplayOptionsPresenter implements FindShortestPathOutputBoundary, ComputeTimeOutputBoundary {

    private final DisplayOptionsViewModel displayOptionsViewModel;
    private final ChooseOptionsViewModel chooseOptionsViewModel;
    private final DisplayResultsViewModel displayResultsViewModel;
    private final ViewManagerModel viewManagerModel;
    public DisplayOptionsPresenter(ViewManagerModel viewManagerModel,
                                   DisplayOptionsViewModel displayOptionsViewModel,
                                   ChooseOptionsViewModel chooseOptionsViewModel,
                                   DisplayResultsViewModel displayResultsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.displayOptionsViewModel = displayOptionsViewModel;
        this.chooseOptionsViewModel = chooseOptionsViewModel;
        this.displayResultsViewModel = displayResultsViewModel;
    }

    @Override
    public void prepareSuccessView(ComputeTimeOutputData outputData) {
        final DisplayResultsState displayResultsState = displayResultsViewModel.getState();
        displayResultsState.setShortestPath(outputData.getSequentialLocations());
        displayResultsViewModel.setState(displayResultsState);
        displayResultsViewModel.firePropertyChanged();

        viewManagerModel.setState(displayResultsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final DisplayOptionsState displayOptionsState = displayOptionsViewModel.getState();
        displayOptionsState.setErrorText(errorMessage);
        displayOptionsViewModel.firePropertyChanged();
    }

    @Override
    public void switchToPreviousView() {
        viewManagerModel.setState(chooseOptionsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

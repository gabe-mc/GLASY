package interface_adapter.display_options;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_options.ChooseOptionsViewModel;
import interface_adapter.display_itinerary_view.DisplayItineraryViewModel;
import interface_adapter.display_itinerary_view.DisplayItineraryState;
import interface_adapter.display_itinerary_view.DisplayItineraryViewModel;
import use_case.compute_time.ComputeTimeOutputBoundary;
import use_case.compute_time.ComputeTimeOutputData;
import use_case.find_shortest_path.FindShortestPathOutputBoundary;
import use_case.find_shortest_path.FindShortestPathOutputData;

public class DisplayOptionsPresenter implements FindShortestPathOutputBoundary, ComputeTimeOutputBoundary {

    private final DisplayOptionsViewModel displayOptionsViewModel;
    private final ChooseOptionsViewModel chooseOptionsViewModel;
    private final DisplayItineraryViewModel displayItineraryViewModel;
    private final ViewManagerModel viewManagerModel;
    public DisplayOptionsPresenter(ViewManagerModel viewManagerModel,
                                   DisplayOptionsViewModel displayOptionsViewModel,
                                   ChooseOptionsViewModel chooseOptionsViewModel,
                                   DisplayItineraryViewModel displayItineraryViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.displayOptionsViewModel = displayOptionsViewModel;
        this.chooseOptionsViewModel = chooseOptionsViewModel;
        this.displayItineraryViewModel = displayItineraryViewModel;
    }

    @Override
    public void prepareSuccessView(ComputeTimeOutputData outputData) {
        final DisplayItineraryState displayItineraryState = displayItineraryViewModel.getState();
        displayItineraryState.setShortestPath(outputData.getSequentialLocations());
        displayItineraryState.setMapImage(outputData.getMapImage());
        displayItineraryViewModel.setState(displayItineraryState);
        displayItineraryViewModel.firePropertyChanged();

        viewManagerModel.setState(displayItineraryViewModel.getViewName());
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

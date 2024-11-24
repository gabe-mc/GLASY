package interface_adapter.display_options;

import interface_adapter.ViewManagerModel;

public class DisplayOptionsPresenter{

    private final DisplayOptionsViewModel displayOptionsViewModel;
    private final ViewManagerModel viewManagerModel;
    public DisplayOptionsPresenter(DisplayOptionsViewModel displayOptionsViewModel, ViewManagerModel viewManagerModel){
        this.displayOptionsViewModel = displayOptionsViewModel;
        this.viewManagerModel = new ViewManagerModel();
    }
}

package interface_adapter.choose_options;

import interface_adapter.ViewManagerModel;

public class ChooseOptionsPresenter{

    private final ChooseOptionsViewModel chooseOptionsViewModel;
    private final ViewManagerModel viewManagerModel;
    public ChooseOptionsPresenter(ChooseOptionsViewModel chooseOptionsViewModel, ViewManagerModel viewManagerModel){
        this.chooseOptionsViewModel = chooseOptionsViewModel;
        this.viewManagerModel = new ViewManagerModel();
    }
}

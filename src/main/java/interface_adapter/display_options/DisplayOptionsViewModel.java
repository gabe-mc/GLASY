package interface_adapter.display_options;

import interface_adapter.ViewModel;

public class DisplayOptionsViewModel extends ViewModel<DisplayOptionsState> {
    public DisplayOptionsViewModel() {
        super("display options");
        setState(new DisplayOptionsState());
    }
}
package interface_adapter.display_options;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Display Options View.
 */
public class DisplayOptionsViewModel extends ViewModel<DisplayOptionsState> {
    public DisplayOptionsViewModel() {
        super("display options");
        setState(new DisplayOptionsState());
    }
}

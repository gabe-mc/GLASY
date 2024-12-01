package interface_adapter.choose_options;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Choose Options View.
 */
public class ChooseOptionsViewModel extends ViewModel<ChooseOptionsState> {

    public ChooseOptionsViewModel(){
        super("choose options");
        setState(new ChooseOptionsState());
    }
}

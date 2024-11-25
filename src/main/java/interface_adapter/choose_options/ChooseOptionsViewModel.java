package interface_adapter.choose_options;

import interface_adapter.ViewModel;

public class ChooseOptionsViewModel extends ViewModel<ChooseOptionsState> {

    public ChooseOptionsViewModel(){
        super("choose options");
        setState(new ChooseOptionsState());
    }
}

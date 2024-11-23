package interface_adapter;

public class ChooseOptionsViewModel extends ViewModel<ChooseOptionsState> {

    public ChooseOptionsViewModel(){
        super("Choose Options");
        setState(new ChooseOptionsState());
    }
}

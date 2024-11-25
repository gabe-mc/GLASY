package interface_adapter.choose_options;

import use_case.choose_options.ChooseOptionsInputBoundary;
import use_case.choose_options.ChooseOptionsInputData;

import java.util.Date;
import java.util.Map;

public class ChooseOptionsController {

    private final ChooseOptionsInputBoundary chooseOptionsUseCaseInteractor;

    public ChooseOptionsController(ChooseOptionsInputBoundary chooseOptionsUseCaseInteractor) {
        this.chooseOptionsUseCaseInteractor = chooseOptionsUseCaseInteractor;
    }

    public void execute(String startingAddress,
                        int maxDistance,
                        double minStars,
                        Date startTime,
                        Date endTime,
                        Map<String, Boolean> possibleLocationTypes) {
        final ChooseOptionsInputData chooseOptionsInputData = new ChooseOptionsInputData(
                startingAddress,
                maxDistance,
                minStars,
                startTime,
                endTime,
                possibleLocationTypes);
        chooseOptionsUseCaseInteractor.execute(chooseOptionsInputData);
    }
}

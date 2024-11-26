package interface_adapter.choose_options;

import entity.LocationData;
import entity.Settings;
import use_case.choose_options.ChooseOptionsGoogleMapsLocationProviderInterface;
import use_case.choose_options.ChooseOptionsInputBoundary;
import use_case.choose_options.ChooseOptionsInputData;

import java.util.Date;
import java.util.Map;

public class ChooseOptionsController {

    private final ChooseOptionsInputBoundary chooseOptionsUseCaseInteractor;
    private final ChooseOptionsGoogleMapsLocationProviderInterface googleMapsLocationProvider;

    public ChooseOptionsController(ChooseOptionsInputBoundary chooseOptionsUseCaseInteractor,
                                   ChooseOptionsGoogleMapsLocationProviderInterface googleMapsLocationProvider) {
        this.chooseOptionsUseCaseInteractor = chooseOptionsUseCaseInteractor;
        this.googleMapsLocationProvider = googleMapsLocationProvider;
    }

    public void execute(String startingAddress,
                        int maxDistance,
                        double minStars,
                        Date startTime,
                        Date endTime,
                        Map<String, Boolean> possibleLocationTypes) {
        final LocationData location = googleMapsLocationProvider.addressToLocation(startingAddress);
        final Settings settings = new Settings(location,
                maxDistance,
                minStars,
                startTime,
                endTime,
                possibleLocationTypes);
        final ChooseOptionsInputData chooseOptionsInputData = new ChooseOptionsInputData(settings);
        chooseOptionsUseCaseInteractor.execute(chooseOptionsInputData);
    }
}

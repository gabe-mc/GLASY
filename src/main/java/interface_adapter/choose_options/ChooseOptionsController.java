package interface_adapter.choose_options;

import entity.LocationData;
import entity.Settings;
import use_case.choose_options.ChooseOptionsGoogleMapsLocationProviderInterface;
import use_case.choose_options.ChooseOptionsInputBoundary;
import use_case.choose_options.ChooseOptionsInputData;
import use_case.use_current_location.UseCurrentLocationInputBoundary;

import java.util.Date;
import java.util.Map;

public class ChooseOptionsController {

    private ChooseOptionsInputBoundary chooseOptionsUseCaseInteractor;
    private ChooseOptionsGoogleMapsLocationProviderInterface googleMapsLocationProvider;
    private UseCurrentLocationInputBoundary currentLocationUseCaseInteractor;

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

    public void switchToPreviousView() { chooseOptionsUseCaseInteractor.switchToPreviousView(); }

    public void useCurrentLocation() { currentLocationUseCaseInteractor.useCurrentLocation(); }

    public void setCurrentLocationUseCaseInteractor(UseCurrentLocationInputBoundary currentLocationUseCaseInteractor) {
        this.currentLocationUseCaseInteractor = currentLocationUseCaseInteractor;
    }

    public void setGoogleMapsLocationProvider(ChooseOptionsGoogleMapsLocationProviderInterface googleMapsLocationProvider) {
        this.googleMapsLocationProvider = googleMapsLocationProvider;
    }

    public void setChooseOptionsUseCaseInteractor(ChooseOptionsInputBoundary chooseOptionsUseCaseInteractor) {
        this.chooseOptionsUseCaseInteractor = chooseOptionsUseCaseInteractor;
    }
}

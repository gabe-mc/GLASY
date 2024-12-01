package interface_adapter.choose_options;

import entity.AttractionData;
import entity.LocationData;
import entity.Settings;
import use_case.choose_options.ChooseOptionsGoogleMapsLocationProviderInterface;
import use_case.choose_options.ChooseOptionsInputBoundary;
import use_case.choose_options.ChooseOptionsInputData;
import use_case.use_current_location.UseCurrentLocationInputBoundary;

import java.util.Date;
import java.util.Map;

/**
 * Controller for the Choose Options View.
 */
public class ChooseOptionsController {

    private ChooseOptionsInputBoundary chooseOptionsUseCaseInteractor;
    private ChooseOptionsGoogleMapsLocationProviderInterface googleMapsLocationProvider;
    private UseCurrentLocationInputBoundary currentLocationUseCaseInteractor;

    /**
     * Executes the Choose Options Use Case.
     * @param startingAddress the address the user begins at
     * @param maxDistance the maximum distance attractions should be searched for
     * @param minStars the minimum rating the attractions should have
     * @param startTime the starting time
     * @param endTime the ending time
     * @param possibleLocationTypes the possible types of locations to search for
     */
    public void execute(String startingAddress,
                        int maxDistance,
                        double minStars,
                        Date startTime,
                        Date endTime,
                        Map<String, Boolean> possibleLocationTypes) {
        final AttractionData location = googleMapsLocationProvider.addressToLocation(startingAddress);
        final Settings settings = new Settings(location,
                maxDistance,
                minStars,
                startTime,
                endTime,
                possibleLocationTypes);
        final ChooseOptionsInputData chooseOptionsInputData = new ChooseOptionsInputData(settings);
        chooseOptionsUseCaseInteractor.execute(chooseOptionsInputData);
    }

    /**
     * Switches the view to the previous view.
     */
    public void switchToPreviousView() { chooseOptionsUseCaseInteractor.switchToPreviousView(); }

    /**
     * Executes the Use Current Location Use Case.
     */
    public void useCurrentLocation() { currentLocationUseCaseInteractor.useCurrentLocation(); }

    public void setChooseOptionsUseCaseInteractor(ChooseOptionsInputBoundary chooseOptionsUseCaseInteractor) {
        this.chooseOptionsUseCaseInteractor = chooseOptionsUseCaseInteractor;
    }

    public void setGoogleMapsLocationProvider(ChooseOptionsGoogleMapsLocationProviderInterface googleMapsLocationProvider) {
        this.googleMapsLocationProvider = googleMapsLocationProvider;
    }

    public void setCurrentLocationUseCaseInteractor(UseCurrentLocationInputBoundary currentLocationUseCaseInteractor) {
        this.currentLocationUseCaseInteractor = currentLocationUseCaseInteractor;
    }
}

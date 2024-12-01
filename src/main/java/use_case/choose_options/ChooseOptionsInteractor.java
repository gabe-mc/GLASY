package use_case.choose_options;

import data_access.FoursquareLocationProvider;
import entity.AttractionData;
import entity.Settings;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The interactor data for the Set User Info Use Case.
 */
public class ChooseOptionsInteractor implements ChooseOptionsInputBoundary {
    private final ChooseOptionsFoursquareLocationProviderInterface foursquareLocationProvider;
    private final ChooseOptionsUserDataAccessInterface userDataAccessObject;
    private final ChooseOptionsOutputBoundary chooseOptionsPresenter;

    public ChooseOptionsInteractor(ChooseOptionsFoursquareLocationProviderInterface foursquareLocationProvider,
                                   ChooseOptionsUserDataAccessInterface userDataAccessInterface,
                                   ChooseOptionsOutputBoundary chooseOptionsOutputBoundary) {
        this.foursquareLocationProvider = foursquareLocationProvider;
        this.userDataAccessObject = userDataAccessInterface;
        this.chooseOptionsPresenter = chooseOptionsOutputBoundary;
    }

    /**
     * Executes the Interactor's main logic by retrieving location data based on the input parameters,
     * converting the results into a list, and preparing the success view for the presenter.
     *
     * @param chooseOptionsInputData the input data containing parameters such as radius, categories, and max locations.
     */
    @Override
    public void execute(ChooseOptionsInputData chooseOptionsInputData) {
        final Settings settings = chooseOptionsInputData.getSettings();
        if (settings.getLocation() == null) {
            chooseOptionsPresenter.prepareFailView("Couldn't find address");
        }
        else {
            userDataAccessObject.setSettings(settings);
            userDataAccessObject.setStartingLocation(settings.getLocation());
            final List<AttractionData> locationList = foursquareLocationProvider.getLocationList(settings);

            if (locationList.size() < 2) {
                chooseOptionsPresenter.prepareFailView("Couldn't find available locations");
            }
            else {
                final int maxLocations = 50;
                final Map<AttractionData, Boolean> checkedLocationList = new LinkedHashMap<>();
                for (int i = 0; i < locationList.size() && i < maxLocations; i++) {
                    checkedLocationList.put(locationList.get(i), false);
                }
                final ChooseOptionsOutputData chooseOptionsOutputData =
                        new ChooseOptionsOutputData(checkedLocationList);
                chooseOptionsPresenter.prepareSuccessView(chooseOptionsOutputData);
            }
        }
    }

    @Override
    public void switchToPreviousView() {
        chooseOptionsPresenter.switchToPreviousView();
    }
}

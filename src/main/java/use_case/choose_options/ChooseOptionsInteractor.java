package use_case.choose_options;

import data_access.FoursquareLocationProvider;
import data_access.UserDataAccessObject;
import entity.CommonLocationData;

/**
 * The interactor data for the Set User Info Use Case.
 */
public class ChooseOptionsInteractor implements ChooseOptionsInputBoundary {
    private final FoursquareLocationProvider foursquareLocationProvider;
    private final ChooseOptionsUserDataAccessInterface userDataAccessObject;
    private final ChooseOptionsOutputBoundary chooseOptionsPresenter;

    public ChooseOptionsInteractor(FoursquareLocationProvider foursquareLocationProvider,
                                   ChooseOptionsUserDataAccessInterface userDataAccessInterface,
                                   ChooseOptionsOutputBoundary chooseOptionsOutputBoundary) {
        this.foursquareLocationProvider = foursquareLocationProvider;
        this.userDataAccessObject = userDataAccessInterface;
        this.chooseOptionsPresenter = chooseOptionsOutputBoundary;
    }

    @Override
    public void execute(ChooseOptionsInputData chooseOptionsInputData) {

    }
}
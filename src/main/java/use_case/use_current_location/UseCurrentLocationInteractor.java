package use_case.use_current_location;


import entity.CommonLocationData;
import entity.LocationData;

public class UseCurrentLocationInteractor implements UseCurrentLocationInputBoundary {
    private final UseCurrentLocationUserDataAccessInterface userDataAccessObject;
    private final UseCurrentLocationOutputBoundary chooseOptionsPresenter;

    public UseCurrentLocationInteractor(UseCurrentLocationUserDataAccessInterface userDataAccessObject,
                                               UseCurrentLocationOutputBoundary chooseOptionsPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.chooseOptionsPresenter = chooseOptionsPresenter;
    }

    @Override
    public void useCurrentLocation() {
        LocationData currentLocation = userDataAccessObject.getCurrentLocation();
        if (currentLocation != null) {
            final UseCurrentLocationOutputData useCurrentLocationOutputData =
                    new UseCurrentLocationOutputData(currentLocation.getAddress());
            chooseOptionsPresenter.updateCurrentLocation(useCurrentLocationOutputData);
        }
        else {
            chooseOptionsPresenter.prepareFailView("Current location not found");
        }
    }
}

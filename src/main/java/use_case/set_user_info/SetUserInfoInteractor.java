package use_case.set_user_info;

import entity.CommonLocationData;

/**
 * The interactor data for the Set User Info Use Case.
 */
public class SetUserInfoInteractor implements SetUserInfoInputBoundary {
    private final entity.User user;

    public SetUserInfoInteractor(entity.User user) {
        this.user = user;
    }

    @Override
    public void setUserTimeAvailable(double timeAvailable) {
        user.setTimeAvailable(timeAvailable);
    }

    @Override
    public void setUserLocation(CommonLocationData location) {
        user.setUserLocation(location);

    }

    @Override
    public void setUserPreferredAttraction(String attraction) {
        user.setAttractionCategory(attraction);
    }
}
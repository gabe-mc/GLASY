package data_access;

import entity.CommonLocationData;
import entity.User;
import use_case.start_app.StartAppUserDataAccessInterface;

public class UserDataAccessObject implements StartAppUserDataAccessInterface {
    private final User user = new User();

    @Override
    public void setCurrentLocation(CommonLocationData currentLocation) {
        this.user.setCurrentLocation(currentLocation);
    }
}

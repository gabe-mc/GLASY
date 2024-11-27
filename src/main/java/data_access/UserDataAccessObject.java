package data_access;

import entity.CommonLocationData;
import entity.LocationData;
import entity.Settings;
import entity.User;
import use_case.choose_options.ChooseOptionsUserDataAccessInterface;
import use_case.start_app.StartAppUserDataAccessInterface;
import use_case.use_current_location.UseCurrentLocationUserDataAccessInterface;

public class UserDataAccessObject implements
        StartAppUserDataAccessInterface,
        ChooseOptionsUserDataAccessInterface,
        UseCurrentLocationUserDataAccessInterface {
    private final User user = new User();

    @Override
    public void setCurrentLocation(CommonLocationData currentLocation) {
        this.user.setCurrentLocation(currentLocation);
    }

    @Override
    public void setSettings(Settings settings) {
        user.setSettings(settings);
    }

    @Override
    public LocationData getCurrentLocation() {
        return this.user.getCurrentLocation();
    }
}
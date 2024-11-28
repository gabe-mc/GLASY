package data_access;

import entity.CommonLocationData;
import entity.LocationData;
import entity.Settings;
import entity.User;
import use_case.choose_options.ChooseOptionsUserDataAccessInterface;
import use_case.compute_time.ComputeTimeUserDataAccessInterface;
import use_case.start_app.StartAppUserDataAccessInterface;
import use_case.use_current_location.UseCurrentLocationUserDataAccessInterface;

import java.util.Calendar;
import java.util.Date;

public class UserDataAccessObject implements
        StartAppUserDataAccessInterface,
        ChooseOptionsUserDataAccessInterface,
        UseCurrentLocationUserDataAccessInterface,
        ComputeTimeUserDataAccessInterface {
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

    @Override
    public double getStartTime() {
        return convertTimeToDouble(user.getSettings().getStartTime());
    }

    @Override
    public double getEndTime() {
        return convertTimeToDouble(user.getSettings().getEndTime());
    }

    public static double convertTimeToDouble(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        double fractionalHours = minutes / 60.0;
        return hours + fractionalHours;
    }
}

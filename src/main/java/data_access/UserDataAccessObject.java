package data_access;

import entity.*;
import use_case.choose_options.ChooseOptionsUserDataAccessInterface;
import use_case.compute_time.ComputeTimeUserDataAccessInterface;
import use_case.find_shortest_path.FindShortestPathUserDataAccessInterface;
import use_case.start_app.StartAppUserDataAccessInterface;
import use_case.use_current_location.UseCurrentLocationUserDataAccessInterface;

import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class UserDataAccessObject implements
        StartAppUserDataAccessInterface,
        ChooseOptionsUserDataAccessInterface,
        UseCurrentLocationUserDataAccessInterface,
        FindShortestPathUserDataAccessInterface,
        ComputeTimeUserDataAccessInterface {
    private final User user = new User();

    @Override
    public void setCurrentLocation(AttractionData currentLocation) {
        this.user.setCurrentLocation(currentLocation);
    }

    @Override
    public void setSettings(Settings settings) {
        user.setSettings(settings);
    }

    @Override
    public void setStartingLocation(AttractionData startingLocation) {
        user.setStartingLocation(startingLocation);
    }

    @Override
    public AttractionData getStartingLocation() {
        return user.getStartingLocation();
    }

    @Override
    public AttractionData getCurrentLocation() {
        return this.user.getCurrentLocation();
    }

    @Override
    public void setMapImage(Image image) {
        this.user.setMapImage(image);
    }

    @Override
    public Image getMapImage() {
        return this.user.getMapImage();
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

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
    public Date getStartTime() {
        return user.getSettings().getStartTime();
    }

    @Override
    public Date getEndTime() {
        return user.getSettings().getEndTime();
    }
}

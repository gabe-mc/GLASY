package interface_adapter.display_options;

import entity.LocationData;
import java.util.List;

public class DisplayOptionsState {
    private List<LocationData> locationList;


    public List<LocationData> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<LocationData> locationList) {
        this.locationList = locationList;
    }
}

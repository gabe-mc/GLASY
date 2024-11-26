package use_case.add_location;

import entity.LocationData;

import java.util.List;

/**
 * Output Boundary for actions related to adding a location.
 */
public class AddLocationOutputData {
    private List<LocationData> locationDataList;

    public List<LocationData> getLocationDataList() {
        return locationDataList;
    }

    public void setLocationDataList(List<LocationData> locationDataList) {
        this.locationDataList = locationDataList;
    }
}

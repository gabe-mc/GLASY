package use_case.choose_options;

import entity.LocationData;

import java.util.List;

public class ChooseOptionsOutputData {
    private final List<LocationData> locations;
    private final boolean useCaseFailed;

    public ChooseOptionsOutputData(List<LocationData> locations, boolean useCaseFailed) {
        this.locations = locations;
        this.useCaseFailed = useCaseFailed;
    }

    public List<LocationData> getLocations() {
        return locations;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}

package use_case.choose_options;

import entity.CommonLocationData;

import java.util.List;

public class ChooseOptionsOutputData {
    private final List<CommonLocationData> locations;
    private final boolean useCaseFailed;

    public ChooseOptionsOutputData(List<CommonLocationData> locations, boolean useCaseFailed) {
        this.locations = locations;
        this.useCaseFailed = useCaseFailed;
    }

    public List<CommonLocationData> getLocations() {
        return locations;
    }
}

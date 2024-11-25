package use_case.start_app;

import entity.CommonLocationData;

/**
 * Output Data for the Start App Use Case.
 */
public class StartAppOutputData {

    private final CommonLocationData commonLocationData;

    public StartAppOutputData(CommonLocationData commonLocationData) {
        this.commonLocationData = commonLocationData;
    }

    public CommonLocationData getLocation() {
        return commonLocationData;
    }

}

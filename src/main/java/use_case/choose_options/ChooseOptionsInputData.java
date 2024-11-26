package use_case.choose_options;

import entity.CommonLocationData;
import entity.Settings;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The input data for the Find Shortest Path Use Case.
 */
public class ChooseOptionsInputData {
    private final Settings settings;

    public ChooseOptionsInputData(Settings settings) {
        this.settings = settings;
    }

    public Settings getSettings() {
        return settings;
    }
}

package interface_adapter.display_options;

import entity.AttractionData;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The state for the Display Options View Model.
 */
public class DisplayOptionsState {
    private Map<AttractionData, Boolean> checkedLocationList = new LinkedHashMap<>();
    private String errorText;

    public Map<AttractionData, Boolean> getCheckedLocationList() {
        return checkedLocationList;
    }

    public void setCheckedLocationList(Map<AttractionData, Boolean> checkedLocationList) {
        this.checkedLocationList = checkedLocationList;
    }

    public void setCheckedLocation(AttractionData attractionData, boolean checked) {
        this.checkedLocationList.put(attractionData, checked);
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }
}

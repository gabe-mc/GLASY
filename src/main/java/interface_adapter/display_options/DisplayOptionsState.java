package interface_adapter.display_options;

import entity.AttractionData;

import java.util.Map;

public class DisplayOptionsState {
    private Map<AttractionData, Boolean> checkedLocationList;

    public Map<AttractionData, Boolean> getCheckedLocationList() {
        return checkedLocationList;
    }

    public void setCheckedLocationList(Map<AttractionData, Boolean> checkedLocationList) {
        this.checkedLocationList = checkedLocationList;
    }
}

package use_case.choose_options;

import entity.AttractionData;

import java.util.Map;

/**
 * The Output Data for the Choose Options Use Case.
 */
public class ChooseOptionsOutputData {
    private final Map<AttractionData, Boolean> checkedLocationList;

    public ChooseOptionsOutputData(Map<AttractionData, Boolean> checkedLocationList) {
        this.checkedLocationList = checkedLocationList;
    }

    public Map<AttractionData, Boolean> getCheckedLocationList() {
        return checkedLocationList;
    }
}

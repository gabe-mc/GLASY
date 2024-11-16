package entity;

import java.util.List;

public class AttractionData extends CommonLocationData{
    private String name;
    private List<Double> hoursOfOperation;

    public AttractionData() { /* Instantiated as an empty method because  */ }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getHoursOfOperation() {
        return hoursOfOperation;
    }

    public void setHoursOfOperation(List<Double> hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }
}

package entity;

import java.util.List;

public class AttractionData extends CommonLocationData{
    private String name;
    private List<Double> hoursOfOperation;
    private int travelTime;
    private String visitTime;

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

    public void setTravelTime(int travelTime) {this.travelTime = travelTime;}

    public int getTravelTime() {return travelTime;}

    public void setVisitTime(String time) {this.visitTime = time;}

    public String getVisitTime() {return visitTime;}

}

package use_case.prepare_locations;

import entity.AttractionData;

import java.util.ArrayList;

public class PrepareLocationsInputData {
    private final String wantedLocations;
    private final ArrayList<AttractionData> allLocations;
    private final int startTime;

    public PrepareLocationsInputData(String wantedLocations, ArrayList<AttractionData> allLocations, int startTime) {
        this.wantedLocations = wantedLocations;
        this.allLocations = allLocations;
        this.startTime = startTime;
    }

    public String getWantedLocations() {
        return wantedLocations;
    }

    public ArrayList<AttractionData> getAllLocations() {return allLocations;}

    public int getStartTime() {return startTime;}
}

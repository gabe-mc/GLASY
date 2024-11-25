package use_case.prepare_locations;

import entity.AttractionData;

import java.util.ArrayList;
import java.util.HashMap;

public class PrepareLocationsInteractor implements PrepareLocationsInputBoundary {
    private final PrepareLocationsOutputBoundary outputBoundary;

    public PrepareLocationsInteractor(PrepareLocationsOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void prepareLocations(ArrayList<String> wantedLocations, ArrayList<AttractionData> allLocations, int startTime) {
        HashMap<AttractionData, String> result = new HashMap<>();
        int currentTime = startTime;
        for (AttractionData location : allLocations) {
            if (wantedLocations.contains(location.getAddress())) {
                int previousTime = currentTime;
                int hours = currentTime / 100;
                int minutes = currentTime % 100;

                // Add increment in minutes
                minutes += location.getTimeRequired();

                // Handle overflow in minutes
                hours += minutes / 60;
                minutes %= 60;

                // Handle overflow in hours (24-hour clock reset)
                hours %= 24;

                // Convert back to 24-hour time format
                currentTime = hours * 100 + minutes;
                result.put(location, String.valueOf(previousTime) + "-" + String.valueOf(currentTime));
            }
        }
        PrepareLocationsOutputData output = new PrepareLocationsOutputData(result);
        outputBoundary.presentLocationsOutput(output);
    }
}

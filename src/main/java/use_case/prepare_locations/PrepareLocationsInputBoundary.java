package use_case.prepare_locations;

import entity.AttractionData;

import java.util.ArrayList;

/**
 * Input Boundary for actions related to finding the shortest path between a group of locations.
 */
public interface PrepareLocationsInputBoundary {

    void prepareLocations(ArrayList<String> wantedLocations, ArrayList<AttractionData> allLocations, int startTime);

}

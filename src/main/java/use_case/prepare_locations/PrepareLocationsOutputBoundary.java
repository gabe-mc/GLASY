package use_case.prepare_locations;

import entity.AttractionData;

import java.util.HashMap;

public interface PrepareLocationsOutputBoundary {

    HashMap<AttractionData, String> presentLocationsOutput(PrepareLocationsOutputData data);

}

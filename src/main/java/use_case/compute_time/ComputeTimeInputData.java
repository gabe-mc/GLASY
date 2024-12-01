package use_case.compute_time;

import entity.AttractionData;

import java.util.List;

/**
 * The ComputeTimeInputData class represents the input data required for the "Compute Time" use case.
 * It contains the sequence of attractions to be visited, as well as the start and end times for the visit.
 */
public class ComputeTimeInputData {
    private final List<AttractionData> sequentialLocations;

    /**
     * Constructs a ComputeTimeInputData object with the specified locations, start time, and end time.
     *
     * @param sequentialLocations A list of {@link AttractionData} objects representing the locations to visit.
     */
    public ComputeTimeInputData(List<AttractionData> sequentialLocations) {
        this.sequentialLocations = sequentialLocations;
    }

    /**
     * Retrieves the sequence of locations to visit.
     *
     * @return A list of {@link AttractionData} objects representing the locations to visit.
     */
    public List<AttractionData> getSequentialLocations() {
        return sequentialLocations;
    }
}

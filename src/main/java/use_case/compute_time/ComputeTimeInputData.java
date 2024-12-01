package use_case.compute_time;

import entity.AttractionData;

import java.util.List;

/**
 * The Input Data for the Compute Time Use Case.
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

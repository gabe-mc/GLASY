package use_case.compute_time;

import entity.AttractionData;
import java.util.List;

/**
 * Represents the output data of the "Compute Time" use case.
 * This class encapsulates a list of sequentially ordered locations and a flag indicating
 * whether the use case failed.
 */
public class ComputeTimeOutputData {
    private final List<AttractionData> sequentialLocations;

    /**
     * Constructs a ComputeTimeOutputData object with the provided list of locations and failure status.
     *
     * @param sequentialLocations A list of sequentially ordered {@link AttractionData} objects.
     */
    public ComputeTimeOutputData(List<AttractionData> sequentialLocations) {
        this.sequentialLocations = sequentialLocations;
    }

    /**
     * Retrieves the list of sequential locations.
     *
     * @return A list of {@link AttractionData} objects in sequential order.
     */
    public List<AttractionData> getSequentialLocations() {
        return sequentialLocations;
    }
}

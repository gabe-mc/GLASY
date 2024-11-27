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
    private final boolean useCaseFailed;

    /**
     * Constructs a ComputeTimeOutputData object with the provided list of locations and failure status.
     *
     * @param sequentialLocations A list of sequentially ordered {@link AttractionData} objects.
     * @param useCaseFailed       A boolean indicating whether the use case failed.
     */
    public ComputeTimeOutputData(List<AttractionData> sequentialLocations, boolean useCaseFailed) {
        this.sequentialLocations = sequentialLocations;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Retrieves the list of sequential locations.
     *
     * @return A list of {@link AttractionData} objects in sequential order.
     */
    public List<AttractionData> getSequentialLocations() {
        return sequentialLocations;
    }

    /**
     * Checks whether the use case failed.
     *
     * @return true if the use case failed; false otherwise.
     */
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}

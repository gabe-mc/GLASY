package use_case.compute_time;

import entity.AttractionData;

import java.awt.*;
import java.util.List;

/**
 * The Output Data for the Choose Options Use Case.
 */
public class ComputeTimeOutputData {
    private final List<AttractionData> sequentialLocations;
    private final Image mapImage;

    /**
     * Constructs a ComputeTimeOutputData object with the provided list of locations and failure status.
     *
     * @param sequentialLocations A list of sequentially ordered {@link AttractionData} objects.
     */
    public ComputeTimeOutputData(List<AttractionData> sequentialLocations, Image mapImage) {
        this.sequentialLocations = sequentialLocations;
        this.mapImage = mapImage;
    }

    /**
     * Retrieves the list of sequential locations.
     *
     * @return A list of {@link AttractionData} objects in sequential order.
     */
    public List<AttractionData> getSequentialLocations() {
        return sequentialLocations;
    }

    public Image getMapImage() {
        return this.mapImage;
    }
}

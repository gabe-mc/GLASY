package use_case.compute_time;

import entity.AttractionData;

import java.util.List;

/**
 * The ComputeTimeInputData class represents the input data required for the "Compute Time" use case.
 * It contains the sequence of attractions to be visited, as well as the start and end times for the visit.
 */
public class ComputeTimeInputData {
    private final List<AttractionData> sequentialLocations;
    private final double startTime;
    private final double endTime;

    /**
     * Constructs a ComputeTimeInputData object with the specified locations, start time, and end time.
     *
     * @param sequentialLocations A list of {@link AttractionData} objects representing the locations to visit.
     * @param startTime           The start time for the visit in decimal hours.
     * @param endTime             The end time for the visit in decimal hours.
     */
    public ComputeTimeInputData(List<AttractionData> sequentialLocations, double startTime, double endTime) {
        this.sequentialLocations = sequentialLocations;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Retrieves the sequence of locations to visit.
     *
     * @return A list of {@link AttractionData} objects representing the locations to visit.
     */
    public List<AttractionData> getSequentialLocations() {
        return sequentialLocations;
    }

    /**
     * Retrieves the start time for the visit.
     *
     * @return The start time in decimal hours.
     */
    public double getStartTime() {
        return startTime;
    }

    /**
     * Retrieves the end time for the visit.
     *
     * @return The end time in decimal hours.
     */
    public double getEndTime() {
        return endTime;
    }
}

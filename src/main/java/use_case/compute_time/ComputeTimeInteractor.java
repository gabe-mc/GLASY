package use_case.compute_time;

import java.util.List;
import entity.AttractionData;

/**
 * The ComputeTimeInteractor class implements the business logic for the "Compute Time" use case.
 * It calculates travel times and visit times for a sequence of attractions based on user input.
 */
public class ComputeTimeInteractor implements ComputeTimeInputBoundary{
    private final ComputeTimeUserDataAccessInterface userDataAccessObject;
    private final ComputeTimeOutputBoundary computeTimePresenter;

    /**
     * Constructs a ComputeTimeInteractor with the specified data access and output boundary.
     *
     * @param userDataAccessInterface The data access interface for travel time data.
     * @param computeTimePresenter                 The output boundary for presenting results.
     */
    public ComputeTimeInteractor(ComputeTimeUserDataAccessInterface userDataAccessInterface,
                                 ComputeTimeOutputBoundary computeTimePresenter) {
        this.userDataAccessObject = userDataAccessInterface;
        this.computeTimePresenter = computeTimePresenter;
    }

    /**
     * Converts a time value from decimal hours to a formatted string (HH:mm).
     *
     * @param time The time in decimal hours.
     * @return A string representing the time in HH:mm format.
     * If hours or minutes is single digit, 0 will appear in front of it.
     */
    private String timeConvert(double time) {
        Double minutes = Math.floor((time - Math.floor(time)) * 60);
        Double hours = Math.floor(time);

        return String.format("%02d:%02d", hours.intValue(), minutes.intValue());
    }

    /**
     * Calculates the total travel time for a sequence of attractions.
     *
     * @param sequentialLocations A list of {@link AttractionData} objects representing the locations.
     * @return The total travel time in minutes.
     */
    private Double getTotalTravelTime(List<AttractionData> sequentialLocations) {
//        Double result = startingLocation.getTravelTime() * 60.0;
        double result = 0.0;

        for (AttractionData node : sequentialLocations) {
            result += node.getTravelTime() * 60.0;
        }
        return result;
    }

    /**
     * Fills the visit times for a sequence of attractions based on start and end times.
     *
     * @param startTime           The start time in decimal hours.
     * @param endTime             The end time in decimal hours.
     * @param sequentialLocations A list of {@link AttractionData} objects to be updated.
     * @return A list of {@link AttractionData} objects with updated visit times.
     */
    private List<AttractionData> fillSequentialLocations(double startTime, double endTime, List<AttractionData> sequentialLocations) {
        // subtract one assuming that the starting location is also included, and we are not spending time there.
        double timeAtEachLocation = Math.floor((endTime - startTime - getTotalTravelTime(sequentialLocations)) / sequentialLocations.size() - 1);
        double lastTravelTime = sequentialLocations.get(0).getTravelTime() * 60.0;
        double currentTime = startTime;

        for (int i = 1; i < sequentialLocations.size() - 1; i++) {
            AttractionData node = sequentialLocations.get(i);
            String visitTime = timeConvert(currentTime + lastTravelTime) + timeConvert(currentTime + lastTravelTime + timeAtEachLocation);
            currentTime += lastTravelTime + timeAtEachLocation;
            node.setVisitTime(visitTime);
        }
        String visitTime = timeConvert(currentTime + lastTravelTime) + timeConvert(endTime);
        sequentialLocations.get(sequentialLocations.size() - 1).setVisitTime(visitTime);
        return sequentialLocations;
    }

    /**
     * Executes the "Compute Time" use case by calculating visit times for a sequence of attractions.
     *
     * @param computeTimeInputData The input data containing start time, end time, and attractions.
     */
    @Override
    public void execute(ComputeTimeInputData computeTimeInputData) {
        List<AttractionData> newSequentialLocations = fillSequentialLocations(
                userDataAccessObject.getStartTime(),
                userDataAccessObject.getEndTime(),
                computeTimeInputData.getSequentialLocations());
        final ComputeTimeOutputData outputData = new ComputeTimeOutputData(newSequentialLocations,
                userDataAccessObject.getMapImage());
        computeTimePresenter.prepareSuccessView(outputData);
    }

}

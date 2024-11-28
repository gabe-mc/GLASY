package use_case.compute_time;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import entity.AttractionData;
import entity.Settings;

/**
 * The ComputeTimeInteractor class implements the business logic for the "Compute Time" use case.
 * It calculates travel times and visit times for a sequence of attractions based on user input.
 */
public class ComputeTimeInteractor implements ComputeTimeInputBoundary{
    private final ComputeTimeUserDataAccessInterface computeTimeDataAccessInterface;
    private final ComputeTimeOutputBoundary outputBoundary;

    /**
     * Constructs a ComputeTimeInteractor with the specified data access and output boundary.
     *
     * @param computeTimeUserDataAccessInterface The data access interface for travel time data.
     * @param outputBoundary                 The output boundary for presenting results.
     */
    public ComputeTimeInteractor(ComputeTimeUserDataAccessInterface computeTimeUserDataAccessInterface,
                                 ComputeTimeOutputBoundary outputBoundary) {
        this.computeTimeDataAccessInterface = computeTimeUserDataAccessInterface;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Converts a time value from decimal hours to a formatted string (HH:mm).
     *
     * @param time The time in decimal hours.
     * @return A string representing the time in HH:mm format.
     * If hours or minutes is single digit, 0 will appear in front of it.
     */
    private String timeConvert(Date time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(time);
    }

    /**
     * Calculates the total travel time for a sequence of attractions.
     *
     * @param sequentialLocations A list of {@link AttractionData} objects representing the locations.
     * sequentialLocations.get(0) should be the user's current location.
     * @return The total travel time in minutes.
     */
    private Double getTotalTravelTime(List<AttractionData> sequentialLocations) {
        double result = 0.0;

        for (AttractionData node : sequentialLocations) {
            result += node.getTravelTime();
        }
        return result;
    }

    /**
     * Fills the visit times for a sequence of attractions based on start and end times.
     *
     * @param startTime           The start time in Date object.
     * @param endTime             The end time in Date object.
     * @param sequentialLocations A list of {@link AttractionData} objects to be updated.
     * @return A list of {@link AttractionData} objects with updated visit times.
     */
    private List<AttractionData> fillSequentialLocations(Date startTime, Date endTime, List<AttractionData> sequentialLocations) {
        // totalTime is total time available for the itinerary.
        long totalTime = endTime.getTime() - startTime.getTime();

        // totalTravelTime is the sum of all travelTimes between two attractions, converted to milliseconds.
        double totalTravelTime = getTotalTravelTime(sequentialLocations) * 60 * 1000;

        // subtract one assuming that the starting location is also included, and we are not spending time there, in milliseconds.
        long timeAtEachLocation = (long) ((totalTime - totalTravelTime) / (sequentialLocations.size() - 1));

        // lastTravelTime is set to be the amount of time from user's starting location to first attraction, in milliseconds.
        long lastTravelTime = sequentialLocations.get(0).getTravelTime() * 60L * 1000L;
        long currentTime = startTime.getTime();

        for (int i = 1; i < sequentialLocations.size() - 1; i++) {
            AttractionData node = sequentialLocations.get(i);
            Date newStartTime = new Date(currentTime + lastTravelTime);
            Date newEndTime = new Date(currentTime + lastTravelTime + timeAtEachLocation);

            String visitTime = timeConvert(newStartTime) + timeConvert(newEndTime);
            currentTime += lastTravelTime + timeAtEachLocation;
            node.setVisitTime(visitTime);
        }
        Date newStartTime = new Date(currentTime + lastTravelTime);
        String visitTime = timeConvert(newStartTime) + timeConvert(endTime);
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
        final Settings settings = computeTimeDataAccessInterface.getSettings();
        List<AttractionData> newSequentialLocations = fillSequentialLocations(
                                                        settings.getStartTime(),
                                                        settings.getEndTime(),
                                                        computeTimeInputData.getSequentialLocations());
        final ComputeTimeOutputData outputData = new ComputeTimeOutputData(newSequentialLocations);
        outputBoundary.prepareSuccessView(outputData);
    }

}

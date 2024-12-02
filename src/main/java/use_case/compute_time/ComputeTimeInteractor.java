package use_case.compute_time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import entity.AttractionData;

/**
 * The Compute Time Interactor.
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
    private String timeConvert(Date time) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(time);
    }

    /**
     * Calculates the total travel time for a sequence of attractions.
     *
     * @param sequentialLocations A list of {@link AttractionData} objects representing the locations.
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
     * @param startTime           The start time in decimal hours.
     * @param endTime             The end time in decimal hours.
     * @param sequentialLocations A list of {@link AttractionData} objects to be updated.
     * @return A list of {@link AttractionData} objects with updated visit times.
     */
    private List<AttractionData> fillSequentialLocations(Date startTime, Date endTime, List<AttractionData> sequentialLocations) {
        // subtract one assuming that the starting location is also included, and we are not spending time there.
        double totalTravelTime = getTotalTravelTime(sequentialLocations);

        if (endTime.getTime() - startTime.getTime() - totalTravelTime <= 0) {
            Calendar endTimeCalender = Calendar.getInstance();
            endTimeCalender.setTime(endTime);
            endTimeCalender.add(Calendar.DATE, 1);
            endTime = endTimeCalender.getTime();
        }
        // Calculate total available time in minutes
        long availableTimeMillis = endTime.getTime() - startTime.getTime();
        long availableTimeMinutes = availableTimeMillis / (60 * 1000);
        long timeAtEachLocationMinutes = (availableTimeMinutes - (long) totalTravelTime) / (sequentialLocations.size() - 1);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);

        double lastTravelTime = sequentialLocations.get(0).getTravelTime();
        System.out.println( sequentialLocations.get(0).getAddress() + "\n" +
                            "available time min: " + availableTimeMinutes + "\n" +
                            "total travel time: " + totalTravelTime + "\n" +
                            "time at each location: " + timeAtEachLocationMinutes + "\n" +
                            "start time: " + startTime.toString() + " - " + startTime.getTime() + "\n" +
                            "end time: " + endTime.toString() + " - " + endTime.getTime());
        sequentialLocations.get(0).setVisitTime(timeConvert(startTime));
        for (int i = 1; i < sequentialLocations.size() - 1; i++) {
            AttractionData node = sequentialLocations.get(i);

            calendar.add(Calendar.MINUTE, (int) lastTravelTime);

            Date visitStartTime = calendar.getTime();

            calendar.add(Calendar.MINUTE, (int) timeAtEachLocationMinutes);

            Date visitEndTime = calendar.getTime();

            String visitTime = timeConvert(visitStartTime) + " - " + timeConvert(visitEndTime);
            node.setVisitTime(visitTime);

            lastTravelTime = node.getTravelTime();
        }
        calendar.add(Calendar.MINUTE, (int) lastTravelTime);
        Date visitStartTime = calendar.getTime();

        String visitTime = timeConvert(visitStartTime) + " - " + timeConvert(endTime);

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

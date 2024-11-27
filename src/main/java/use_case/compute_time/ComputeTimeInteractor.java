package use_case.compute_time;

import java.util.List;
import entity.LocationData;
import use_case.find_shortest_path.FindShortestPathOutputBoundary;

public class ComputeTimeInteractor implements ComputeTimeInputBoundary{
    private final ComputeTimeOutputBoundary outputBoundary;
    private final List<LocationData> sequentialLocations;

    public ComputeTimeInteractor(List<LocationData> sequentialLocations,
                                      ComputeTimeOutputBoundary outputBoundary) {
        this.sequentialLocations = sequentialLocations;
        this.outputBoundary = outputBoundary;
    }


}

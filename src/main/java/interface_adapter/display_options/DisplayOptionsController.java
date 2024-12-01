package interface_adapter.display_options;

import entity.AttractionData;
import use_case.compute_time.ComputeTimeInputBoundary;
import use_case.compute_time.ComputeTimeInputData;
import use_case.find_shortest_path.FindShortestPathInputBoundary;
import use_case.find_shortest_path.FindShortestPathInputData;
import use_case.find_shortest_path.FindShortestPathOutputData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DisplayOptionsController {

    private final FindShortestPathInputBoundary findShortestPathUseCaseInteractor;
    private final ComputeTimeInputBoundary computeTimeInputUseCaseInteractor;

    public DisplayOptionsController(FindShortestPathInputBoundary findShortestPathUseCaseInteractor,
                                    ComputeTimeInputBoundary computeTimeInputUseCaseInteractor) {
        this.findShortestPathUseCaseInteractor = findShortestPathUseCaseInteractor;
        this.computeTimeInputUseCaseInteractor = computeTimeInputUseCaseInteractor;
    }

    public void execute(Map<AttractionData, Boolean> checkedLocationList) {
        List<AttractionData> path = new ArrayList<>();
        for (Map.Entry<AttractionData, Boolean> entry : checkedLocationList.entrySet()) {
            if (entry.getValue()) {
                path.add(entry.getKey());
            }
        }
        final FindShortestPathInputData findShortestPathInputData = new FindShortestPathInputData(path);
        final FindShortestPathOutputData findShortestPathOutputData =
                findShortestPathUseCaseInteractor.execute(findShortestPathInputData);

        if (findShortestPathOutputData != null) {
            final ComputeTimeInputData computeTimeInputData =
                    new ComputeTimeInputData(findShortestPathOutputData.getShortestPath());
            computeTimeInputUseCaseInteractor.execute(computeTimeInputData);
        }
    }

    public void switchToPreviousView() { findShortestPathUseCaseInteractor.switchToPreviousView(); }
}

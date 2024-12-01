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

    private FindShortestPathInputBoundary findShortestPathUseCaseInteractor;
    private ComputeTimeInputBoundary computeTimeInputUseCaseInteractor;

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

    public void setFindShortestPathUseCaseInteractor(FindShortestPathInputBoundary findShortestPathUseCaseInteractor) {
        this.findShortestPathUseCaseInteractor = findShortestPathUseCaseInteractor;
    }

    public void setComputeTimeInputUseCaseInteractor(ComputeTimeInputBoundary computeTimeInputUseCaseInteractor) {
        this.computeTimeInputUseCaseInteractor = computeTimeInputUseCaseInteractor;
    }
}

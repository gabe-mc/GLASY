package interface_adapter.display_options;

import use_case.find_shortest_path.FindShortestPathInputBoundary;

public class DisplayOptionsController {

    private final FindShortestPathInputBoundary findShortestPathUseCaseInteractor;

    public DisplayOptionsController(FindShortestPathInputBoundary findShortestPathUseCaseInteractor) {
        this.findShortestPathUseCaseInteractor = findShortestPathUseCaseInteractor;
    }

    public void execute() {
//        findShortestPathUseCaseInteractor.execute();
    }
}

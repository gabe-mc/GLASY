package use_case.find_shortest_path;

import java.lang.reflect.Field;

/**
 * The interactor data for the Find Shortest Path Use Case.
 */
public class findShortestPathInteractor implements findShortestPathInputBoundary{
    private final findShortestPathOutputBoundary outputBoundary;

    public findShortestPathInteractor(findShortestPathOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void findShortestPath(findShortestRouteInputData inputData) {


    }
}

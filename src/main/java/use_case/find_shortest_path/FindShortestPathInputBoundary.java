package use_case.find_shortest_path;


/**
 * Input Boundary for actions related to finding the shortest path between a group of locations.
 */

public interface FindShortestPathInputBoundary {

    /**
     * Execute the findShortestRoute Use Case.
     * @param inputData is the input data for this use case
     */
    FindShortestPathOutputData execute(FindShortestPathInputData inputData);

    /**
     * Switches the view to the previous page.
     */
    void switchToPreviousView();
}

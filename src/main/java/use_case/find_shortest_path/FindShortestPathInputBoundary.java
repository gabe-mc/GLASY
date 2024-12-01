package use_case.find_shortest_path;


/**
 * Input Boundary for actions related to finding the shortest path between a group of locations.
 */

public interface FindShortestPathInputBoundary {

    /**
     * Execute the findShortestRoute use case.
     * @param inputData is the a class representing a list of locations
     */
    FindShortestPathOutputData execute(FindShortestPathInputData inputData);

    /**
     * Switches the view to the previous page.
     */
    void switchToPreviousView();
}

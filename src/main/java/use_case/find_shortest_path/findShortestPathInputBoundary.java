package use_case.find_shortest_path;



/**
 * Input Boundary for actions related to finding the shortest path between a group of locations.
 */

public interface findShortestPathInputBoundary {

    /**
     * Execute the findShortestRoute Use Case.
     * @param findShortestRouteInputData is the input data for this use case
     */
    void findShortestPath(findShortestRouteInputData inputData);
}

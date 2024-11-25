package use_case.find_shortest_path;


import java.io.IOException;

/**
 * Input Boundary for actions related to finding the shortest path between a group of locations.
 */

public interface FindShortestPathInputBoundary {

    /**
     * Execute the findShortestRoute Use Case.
     * @param inputData is the input data for this use case
     */
    void findShortestPath(FindShortestPathInputData inputData) throws IOException;
}

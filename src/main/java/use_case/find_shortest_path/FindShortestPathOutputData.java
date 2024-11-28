package use_case.find_shortest_path;

import entity.AttractionData;

import java.util.List;

/**
 * The output data for the Find Shortest Path Use Case.
 */
public class FindShortestPathOutputData {

    private final List<AttractionData> shortestPath;

    public FindShortestPathOutputData(List<AttractionData> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public List<AttractionData> getShortestPath() {return shortestPath;}
}

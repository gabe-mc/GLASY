package use_case.find_shortest_path;

import java.util.ArrayList;

/**
 * The output data for the Find Shortest Path Use Case.
 */
public class FindShortestPathOutputData {

    private final ArrayList<String> shortestPath;

    public FindShortestPathOutputData(ArrayList<String> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public ArrayList<String> getShortestPath() {return shortestPath;}
}

package use_case.find_shortest_path;

import java.util.ArrayList;

/**
 * The input data for the Find Shortest Path Use Case.
 */
public class FindShortestPathInputData {
    private final ArrayList<String> path;

    public FindShortestPathInputData(ArrayList<String> path) {
        if (path.isEmpty()) {
            throw new IllegalArgumentException("Start and end locations must not be null or empty.");
        }
        else {
            this.path = path;
        }
    }

    public ArrayList<String> getPath() {
        return path;
    }
}

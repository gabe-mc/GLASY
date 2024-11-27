package use_case.find_shortest_path;

import entity.AttractionData;
import entity.User;

import java.util.ArrayList;

/**
 * The input data for the Find Shortest Path Use Case.
 */
public class FindShortestPathInputData {
    private final ArrayList<AttractionData> path;

    public FindShortestPathInputData(ArrayList<AttractionData> path) {
        if (path.isEmpty()) {
            throw new IllegalArgumentException("Start and end locations must not be null or empty.");
        }
        else {
            this.path = path;
        }
    }

    public ArrayList<AttractionData> getPath() {
        return path;
    }
}

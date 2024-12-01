package use_case.find_shortest_path;

import entity.AttractionData;
import entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * The Input Data for the Find Shortest Path Use Case.
 */
public class FindShortestPathInputData {
    private final List<AttractionData> path;

    public FindShortestPathInputData(List<AttractionData> path) {
        this.path = path;
    }

    public List<AttractionData> getPath() {
        return path;
    }
}

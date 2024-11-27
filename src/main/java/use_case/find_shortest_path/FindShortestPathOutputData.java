package use_case.find_shortest_path;

import entity.AttractionData;
import org.w3c.dom.Attr;

import java.util.ArrayList;

/**
 * The output data for the Find Shortest Path Use Case.
 */
public class FindShortestPathOutputData {

    private final ArrayList<AttractionData> shortestPath;

    public FindShortestPathOutputData(ArrayList<AttractionData> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public ArrayList<AttractionData> getShortestPath() {return shortestPath;}
}

package interface_adapter.display_results_view;

import entity.AttractionData;

import java.util.List;

public class DisplayResultsState {
    private List<AttractionData> shortestPath;

    public List<AttractionData> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<AttractionData> shortestPath) {
        this.shortestPath = shortestPath;
    }
}

package interface_adapter.display_itinerary_view;

import entity.AttractionData;

import java.awt.*;
import java.util.List;

/**
 * The state for the Display Itinerary View Model.
 */
public class DisplayItineraryState {
    private List<AttractionData> shortestPath;
    private Image mapImage;

    public List<AttractionData> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<AttractionData> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Image getMapImage() {
        return mapImage;
    }

    public void setMapImage(Image mapImage) {
        this.mapImage = mapImage;
    }
}

package use_case.find_shortest_path;


import entity.AttractionData;

import java.awt.*;

public interface FindShortestPathUserDataAccessInterface {
    AttractionData getStartingLocation();

    void setMapImage(Image image);
}

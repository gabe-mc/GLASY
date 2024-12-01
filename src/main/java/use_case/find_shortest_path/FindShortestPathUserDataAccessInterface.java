package use_case.find_shortest_path;


import entity.AttractionData;

import java.awt.*;

/**
 * The User DAO for the Find Shortest Path Use Case.
 */
public interface FindShortestPathUserDataAccessInterface {
    AttractionData getStartingLocation();

    /**
     * Saves the generated image of the map
     * @param image the image to save
     */
    void setMapImage(Image image);
}

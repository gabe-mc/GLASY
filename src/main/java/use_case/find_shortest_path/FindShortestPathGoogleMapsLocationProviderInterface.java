package use_case.find_shortest_path;

import entity.AttractionData;

import java.util.List;

/**
 * The Google Maps Location Provider for the Find Shortest Path Use Case.
 */
public interface FindShortestPathGoogleMapsLocationProviderInterface {
    /**
     * Returns two 2d integer arrays representing the distance and duration between locations respectively
     * @param locations a list of locations to calculate the distance and duraction between
     * @return the distances and durations between each of the locations
     */
    int[][][] getDistanceMatrix(List<AttractionData> locations);

    /**
     * Returns a link to a google maps image that shows a path and markers through locations
     * @param path a list of locations to show a path through
     * @return a link to a google maps api url
     */
    String generateStaticMapUrl(List<AttractionData> path);
}

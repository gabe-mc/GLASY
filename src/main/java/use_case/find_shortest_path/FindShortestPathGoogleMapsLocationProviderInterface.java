package use_case.find_shortest_path;

import entity.AttractionData;

import java.util.List;

public interface FindShortestPathGoogleMapsLocationProviderInterface {
    int[][][] getDistanceMatrix(List<AttractionData> locations);

    String generateStaticMapUrl(List<AttractionData> path);
}

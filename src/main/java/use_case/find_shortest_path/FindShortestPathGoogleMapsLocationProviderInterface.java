package use_case.find_shortest_path;

import entity.AttractionData;

import java.util.List;

public interface FindShortestPathGoogleMapsLocationProviderInterface {
    Float matrixDistance(String address1, String address2);

    Integer calculateTravelTime(String address1, String address2);

    String generateStaticMapUrl(List<AttractionData> path);
}

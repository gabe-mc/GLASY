package use_case.find_shortest_path;

public interface FindShortestPathGoogleMapsLocationProviderInterface {
    Float matrixDistance(String address1, String address2);

    Integer calculateTravelTime(String address1, String address2);
}

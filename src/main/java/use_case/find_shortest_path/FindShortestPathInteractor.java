package use_case.find_shortest_path;

import data_access.GoogleMapsLocationProvider;
import entity.AttractionData;
import entity.LocationData;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

/**
 * The interactor data for the Find Shortest Path Use Case.
 */
public class FindShortestPathInteractor implements FindShortestPathInputBoundary{
    private final FindShortestPathGoogleMapsLocationProviderInterface googleMapsLocationProvider;
    private final FindShortestPathOutputBoundary findShortestPathPresenter;
    private final FindShortestPathUserDataAccessInterface userDataAccessObject;

    public FindShortestPathInteractor(FindShortestPathGoogleMapsLocationProviderInterface googleMapsLocationProvider,
                                      FindShortestPathUserDataAccessInterface userDataAccessInterface,
                                      FindShortestPathOutputBoundary findShortestPathOutputBoundary) {
        this.googleMapsLocationProvider = googleMapsLocationProvider;
        this.userDataAccessObject = userDataAccessInterface;
        this.findShortestPathPresenter = findShortestPathOutputBoundary;
    }

    @Override
    public FindShortestPathOutputData execute(FindShortestPathInputData inputData) {
        List<AttractionData> locations = inputData.getPath();
        locations.add(0, userDataAccessObject.getStartingLocation());
        FindShortestPathOutputData findShortestPathOutputData = null;
        if (locations.size() < 2) {
            findShortestPathPresenter.prepareFailView("Not enough locations");
        }
        else {
            try {
                final AttractionData origin = locations.get(0);
                final ArrayList<AttractionData> result = new ArrayList<>();
                result.add(origin);
                Set<AttractionData> visited = new HashSet<>();
                visited.add(origin); // Mark the origin as visited
                HashMap<AttractionData, Float> temp = new HashMap<>();
                int i = 0;
                while (visited.size() < locations.size()) {
                    temp.clear();
                    System.out.println("Current Location: " + locations.get(i).getName());

                    // Find the nearest unvisited location
                    for (int j = 1; j < locations.size(); j++) {
                        AttractionData currentLocation = locations.get(j);
                        if (!visited.contains(currentLocation)) {
                            final Float dist = googleMapsLocationProvider.matrixDistance(locations.get(i).getAddress(),
                                    currentLocation.getAddress());
                            temp.put(currentLocation, dist);
                            System.out.println(currentLocation.getName() + ": " + dist);
                        }
                    }

                    // Find the closest unvisited location
                    AttractionData minLocation = null;
                    Float minValue = Float.MAX_VALUE;
                    for (Map.Entry<AttractionData, Float> entry : temp.entrySet()) {
                        if (entry.getValue() < minValue) {
                            minLocation = entry.getKey();
                            minValue = entry.getValue();
                        }
                    }

                    System.out.println("Visiting: " + minLocation.getName());
                    System.out.println("----");

                    // Mark the chosen location as visited
                    if (minLocation != null) {
                        result.add(minLocation);
                        visited.add(minLocation);
                        // Update i to the index of the newly visited location
                        i = locations.indexOf(minLocation);
                    }
                }

                for (int m = 0; m < result.size() - 1; m++) {
                    result.get(m).setTravelTime(googleMapsLocationProvider.calculateTravelTime(
                            result.get(m).getAddress(), result.get(m + 1).getAddress()));
                }
                result.get(result.size() - 1).setTravelTime(0);

                URL url = new URL(googleMapsLocationProvider.generateStaticMapUrl(result));
                Image mapImage = ImageIO.read(url);

                userDataAccessObject.setMapImage(mapImage);

                findShortestPathOutputData = new FindShortestPathOutputData(result);
            } catch (Exception e) {
                e.printStackTrace();
                findShortestPathPresenter.prepareFailView("Error generating itinerary");
            }
        }
        return findShortestPathOutputData;
    }

    @Override
    public void switchToPreviousView() {
        findShortestPathPresenter.switchToPreviousView();
    }
}

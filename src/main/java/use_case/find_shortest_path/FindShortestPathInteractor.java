package use_case.find_shortest_path;

import data_access.GoogleMapsLocationProvider;
import entity.AttractionData;
import entity.LocationData;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
                HashMap<AttractionData, Float> temp = new HashMap<>();
                final int size = locations.size() - 2;
                int i = 0;
                while (i < size) {
                    for (int j = 1; j < locations.size(); j++) {
                        final Float dist = googleMapsLocationProvider.matrixDistance(locations.get(i).getAddress(),
                                locations.get(j).getAddress());
                        temp.put(locations.get(j), dist);
                    }
                    AttractionData minName = null;
                    Float minValue = Float.MAX_VALUE;
                    for (AttractionData location : temp.keySet()) {
                        if (temp.get(location) < minValue) {
                            minName = location;
                            minValue = temp.get(location);
                        }
                    }
                    result.add(minName);
                    locations.remove(minName);
                    temp = new HashMap<>();
                    i += 1;
                }
                result.add(locations.get(1));

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

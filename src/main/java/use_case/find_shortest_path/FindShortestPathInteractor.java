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
                int n = locations.size();
                int[][][] distanceMatrix = googleMapsLocationProvider.getDistanceMatrix(locations);
                int[][] dist = distanceMatrix[0];
                int[][] duration = distanceMatrix[1];
                int[][] memo = new int[n][1 << n];
                int[][] parent = new int[n][1 << n];
                for (int i = 0; i < n; i++) {
                    Arrays.fill(memo[i], -1);
                    Arrays.fill(parent[i], -1);
                }
                totalCost(1, 0, n, dist, memo, parent);

                List<Integer> route = new ArrayList<>();
                int mask = 1;
                int curr = 0; // Start at location 0
                route.add(curr);
                while (true) {
                    int nextCity = parent[curr][mask];
                    if (nextCity == -1) break;

                    route.add(nextCity);
                    mask |= (1 << nextCity);
                    curr = nextCity;
                }
                List<AttractionData> result = new ArrayList<>();
                for (int i = 0; i < route.size(); i++) {
                    result.add(locations.get(route.get(i)));
                }

                for (int m = 0; m < result.size() - 1; m++) {
                    result.get(m).setTravelTime(duration[m][m + 1] / 60);
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

    static int totalCost(int mask, int curr, int n,
                         int[][] cost, int[][] memo, int[][] parent) {
        // Dynamic programming implementation of modified  travelling salesman problem
        if (mask == (1 << n) - 1) {
            // Do not need to return to origin, so return 0
            return 0;
        }
        if (memo[curr][mask] != -1) {
            return memo[curr][mask];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0) {
                int tempCost = cost[curr][i] + totalCost(mask | (1 << i), i, n, cost, memo, parent);
                if (tempCost < ans) {
                    ans = tempCost;
                    parent[curr][mask] = i;
                }
            }
        }
        return memo[curr][mask] = ans;
    }

    @Override
    public void switchToPreviousView() {
        findShortestPathPresenter.switchToPreviousView();
    }
}

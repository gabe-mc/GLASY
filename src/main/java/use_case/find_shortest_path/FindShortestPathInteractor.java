package use_case.find_shortest_path;

import data_access.GoogleMapsLocationProvider;
import entity.AttractionData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The interactor data for the Find Shortest Path Use Case.
 */
public class FindShortestPathInteractor implements FindShortestPathInputBoundary{
    private final FindShortestPathGoogleMapsLocationProviderInterface googleMapsLocationProvider;
    private final FindShortestPathOutputBoundary findShortestPathPresenter;

    public FindShortestPathInteractor(FindShortestPathGoogleMapsLocationProviderInterface googleMapsLocationProvider,
                                      FindShortestPathOutputBoundary findShortestPathOutputBoundary) {
        this.googleMapsLocationProvider = googleMapsLocationProvider;
        this.findShortestPathPresenter = findShortestPathOutputBoundary;
    }

    @Override
    public FindShortestPathOutputData execute(FindShortestPathInputData inputData) {
        List<AttractionData> locations = inputData.getPath();
        FindShortestPathOutputData findShortestPathOutputData = null;
        if (locations.isEmpty()) {
            findShortestPathPresenter.prepareFailView("Not enough locations");
        }
        else {
            final AttractionData origin = locations.get(0);
            final ArrayList<AttractionData> result = new ArrayList<>();
            result.add(origin);
            HashMap<AttractionData, Float> temp = new HashMap<>();
            final int size = locations.size() - 2;
            int i = 0;
            while (i < size) {
                for (int j = 1; j < locations.size(); j++) {
                    final Float dist = googleMapsLocationProvider.matrixDistance(locations.get(i).getAddress(), locations.get(j).getAddress());
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
                result.get(m).setTravelTime(googleMapsLocationProvider.calculateTravelTime(result.get(m).getAddress(), result.get(m + 1).getAddress()));
            }
            result.get(result.size() - 1).setTravelTime(0);

            findShortestPathOutputData = new FindShortestPathOutputData(result);
            // TODO: On a failure, call findShortestPathPresenter.prepareFailView(error message for
            //  matrixDistance and for calculateTravelTime – otherwise, return the value (don't need to add anything
            //  it already returns it at the end of this function
//            findShortestPathPresenter.prepareSuccessView(outputData);

            // Temporary error call
//            findShortestPathPresenter.prepareFailView("Error generating path");
        }
        return findShortestPathOutputData;
    }

    @Override
    public void switchToPreviousView() {
        findShortestPathPresenter.switchToPreviousView();
    }

    public static void main(String[] args) {
        GoogleMapsLocationProvider example = new GoogleMapsLocationProvider();
//        ArrayList<Double> getResponse = example.getAddress("197 Yonge St, Toronto ON");
        ArrayList<String> path = new ArrayList<>();
        path.add("47 Willcocks St, Toronto ON");
        path.add("197 Yonge St, Toronto ON");
        path.add("57 St Joseph St, Toronto ON");
        path.add("4 Hoskin Ave, Toronto ON");
//        FindShortestPathInputData result = new FindShortestPathInputData(path);
//        FindShortestPathInteractor interactor = new FindShortestPathInteractor(example, null);
//        ArrayList<String> getResponse = interactor.findShortestPath(result);
//        System.out.println(example.generateMapsLink(getResponse));
    }
}

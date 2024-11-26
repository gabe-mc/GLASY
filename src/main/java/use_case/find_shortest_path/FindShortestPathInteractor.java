package use_case.find_shortest_path;

import data_access.GoogleMapsLocationProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The interactor data for the Find Shortest Path Use Case.
 */
public class FindShortestPathInteractor implements FindShortestPathInputBoundary{
    private final GoogleMapsLocationProvider googleMapsDAO;
    private final FindShortestPathOutputBoundary outputBoundary;

    public FindShortestPathInteractor(GoogleMapsLocationProvider googleMapsDAO,
                                      FindShortestPathOutputBoundary outputBoundary) {
        this.googleMapsDAO = googleMapsDAO;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void findShortestPath(FindShortestPathInputData inputData) throws IOException {
        ArrayList<String> locations = inputData.getPath();
        final String origin = locations.get(0);
        final ArrayList<String> result = new ArrayList<>();
        result.add(origin);
        HashMap<String, Float> temp = new HashMap<>();
        final int size = locations.size() - 2;
        int i = 0;
        while (i < size) {
            for (int j = 1; j < locations.size(); j++) {
                final Float dist = googleMapsDAO.matrixDistance(locations.get(i), locations.get(j));
                temp.put(locations.get(j), dist);
            }
            String minName = "";
            Float minValue = Float.MAX_VALUE;
            for (String location : temp.keySet()) {
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

        FindShortestPathOutputData outputData = new FindShortestPathOutputData(result);
        outputBoundary.presentOutput(outputData);

    }

    public static void main(String[] args) throws IOException {
        GoogleMapsLocationProvider example = new GoogleMapsLocationProvider();
//        ArrayList<Double> getResponse = example.getAddress("197 Yonge St, Toronto ON");
        ArrayList<String> path = new ArrayList<>();
        path.add("47 Willcocks St, Toronto ON");
        path.add("197 Yonge St, Toronto ON");
        path.add("57 St Joseph St, Toronto ON");
        path.add("4 Hoskin Ave, Toronto ON");
        FindShortestPathInputData result = new FindShortestPathInputData(path);
        FindShortestPathInteractor interactor = new FindShortestPathInteractor(example, null);
//        ArrayList<String> getResponse = interactor.findShortestPath(result);
//        System.out.println(example.generateMapsLink(getResponse));
    }
}

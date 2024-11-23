package use_case.find_shortest_path;

import data_access.googleMapsDataAccess;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The interactor data for the Find Shortest Path Use Case.
 */
public class FindShortestPathInteractor implements FindShortestPathInputBoundary{
    private final googleMapsDataAccess googleMapsDAO;
    private final FindShortestPathOutputBoundary outputBoundary;

    public FindShortestPathInteractor(googleMapsDataAccess googleMapsDAO,
                                      FindShortestPathOutputBoundary outputBoundary) {
        this.googleMapsDAO = googleMapsDAO;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void findShortestPath(FindShortestPathInputData inputData) throws IOException {
        ArrayList<String> path = googleMapsDAO.findShortestRoute(inputData);
        FindShortestPathOutputData outputData = new FindShortestPathOutputData(path);
        outputBoundary.presentOutput(outputData);

    }
}

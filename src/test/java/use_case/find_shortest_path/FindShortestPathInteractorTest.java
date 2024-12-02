package use_case.find_shortest_path;

import data_access.GoogleMapsLocationProvider;
import data_access.UserDataAccessObject;
import entity.AttractionData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindShortestPathInteractorTest {

    @Test
    void successTest() {
        UserDataAccessObject userDataAccessObject = new UserDataAccessObject();
        GoogleMapsLocationProvider googleMapsLocationProvider = new GoogleMapsLocationProvider();
        AttractionData attraction0 = new AttractionData();
        attraction0.setAddress("15 Bloor St. Toronto, ON");
        attraction0.setLatitude(43.669848);
        attraction0.setLongitude(-79.387450);
        userDataAccessObject.setStartingLocation(attraction0);
        AttractionData attraction1 = new AttractionData();
        attraction1.setAddress("15 Church St, Toronto ON");
        attraction1.setLatitude(43.647953);
        attraction1.setLongitude(-79.373326);
        AttractionData attraction2 = new AttractionData();
        attraction2.setAddress("197 Yonge St, Toronto ON");
        attraction2.setLatitude(43.653347);
        attraction2.setLongitude(-79.379373);
        AttractionData attraction3 = new AttractionData();
        attraction3.setAddress("101 Yonge St, Toronto ON");
        attraction3.setLatitude(43.650199);
        attraction3.setLongitude(-79.378272);
        ArrayList<AttractionData> addresses = new ArrayList<>();
        addresses.add(attraction1);
        addresses.add(attraction2);
        addresses.add(attraction3);
        FindShortestPathInputData inputData = new FindShortestPathInputData(addresses);

        FindShortestPathOutputBoundary outputBoundary = new FindShortestPathOutputBoundary() {
            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToPreviousView() {
                fail("Use case failure is unexpected.");
            }
        };
        FindShortestPathInputBoundary interactor = new FindShortestPathInteractor(googleMapsLocationProvider,
                userDataAccessObject, outputBoundary);
        FindShortestPathOutputData findShortestPathOutputData = interactor.execute(inputData);
        assertNotNull(findShortestPathOutputData);
        List<AttractionData> shortestPath = findShortestPathOutputData.getShortestPath();
        assertEquals("15 Bloor St. Toronto, ON", shortestPath.get(0).getAddress());
        assertEquals("197 Yonge St, Toronto ON", shortestPath.get(1).getAddress());
        assertEquals("101 Yonge St, Toronto ON", shortestPath.get(2).getAddress());
        assertEquals("15 Church St, Toronto ON", shortestPath.get(3).getAddress());

    }
}

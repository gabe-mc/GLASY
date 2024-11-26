package use_case.find_shortest_path;

import data_access.GoogleMapsLocationProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class FindShortestPathInteractorTest {

    @Test
    void SuccessTest() throws IOException {
        ArrayList<String> addresses = new ArrayList<>();
        addresses.add("15 Church St, Toronto ON");
        addresses.add("197 Yonge St, Toronto ON");
        addresses.add("101 Yonge St, Toronto ON");
        FindShortestPathOutputBoundary outputBoundary = new FindShortestPathOutputBoundary() {

            @Override
            public void presentOutput(FindShortestPathOutputData outputData) {
                ArrayList<String> expected = new ArrayList<>();
                expected.add("15 Church St, Toronto ON");
                expected.add("101 Yonge St, Toronto ON");
                expected.add("197 Yonge St, Toronto ON");
                Assertions.assertEquals(expected, outputData.getShortestPath());
            }
        };
        FindShortestPathInputData inputData = new FindShortestPathInputData(addresses);
        GoogleMapsLocationProvider googleObj = new GoogleMapsLocationProvider();
        FindShortestPathInputBoundary interactor = new FindShortestPathInteractor(googleObj, outputBoundary);
        interactor.findShortestPath(inputData);
    }
}

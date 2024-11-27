package use_case.find_shortest_path;

import data_access.GoogleMapsLocationProvider;
import entity.AttractionData;
import entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class FindShortestPathInteractorTest {

    @Test
    void SuccessTest() throws IOException {
        AttractionData attraction1 = new AttractionData();
        attraction1.setAddress("15 Church St, Toronto ON");
        AttractionData attraction2 = new AttractionData();
        attraction2.setAddress("197 Yonge St, Toronto ON");
        AttractionData attraction3 = new AttractionData();
        attraction3.setAddress("101 Yonge St, Toronto ON");
        ArrayList<AttractionData> addresses = new ArrayList<>();
        addresses.add(attraction1);
        addresses.add(attraction2);
        addresses.add(attraction3);
        FindShortestPathOutputBoundary outputBoundary = new FindShortestPathOutputBoundary() {

            @Override
            public void presentOutput(FindShortestPathOutputData outputData) {
                AttractionData attraction11 = new AttractionData();
                attraction11.setAddress("15 Church St, Toronto ON");
                AttractionData attraction22 = new AttractionData();
                attraction22.setAddress("197 Yonge St, Toronto ON");
                AttractionData attraction33 = new AttractionData();
                attraction33.setAddress("101 Yonge St, Toronto ON");
                ArrayList<String> addresses11 = new ArrayList<>();
                addresses11.add(attraction11.getAddress());
                addresses11.add(attraction33.getAddress());
                addresses11.add(attraction22.getAddress());
                ArrayList<AttractionData> output = outputData.getShortestPath();
                ArrayList<String> outputStr = new ArrayList<>();
                for (AttractionData put : output) {
                    outputStr.add(put.getAddress());
                }
                Assertions.assertEquals(addresses11, outputStr);
            }
        };
        FindShortestPathInputData inputData = new FindShortestPathInputData(addresses);
        GoogleMapsLocationProvider googleObj = new GoogleMapsLocationProvider();
        FindShortestPathInputBoundary interactor = new FindShortestPathInteractor(googleObj, outputBoundary);
        interactor.findShortestPath(inputData);
    }
}

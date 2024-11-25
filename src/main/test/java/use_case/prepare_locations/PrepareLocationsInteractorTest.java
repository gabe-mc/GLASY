package java.use_case.prepare_locations;

import entity.AttractionData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import use_case.prepare_locations.*;

import java.util.ArrayList;
import java.util.HashMap;

public class PrepareLocationsInteractorTest {

    @Test
    void SuccessTest() {
        ArrayList<AttractionData> attractions = new ArrayList<>();
        ArrayList<String> wantedLocations = new ArrayList<>();
        AttractionData attraction1 = new AttractionData();
        attraction1.setName("Burger King");
        attraction1.setAddress("197 Yonge Street");
        attraction1.setCountry("Canada");
        attractions.add(attraction1);
        wantedLocations.add("Burger King");
        AttractionData attraction2 = new AttractionData();
        attraction2.setName("McDonalds");
        attraction2.setAddress("101 Yonge Street");
        attraction2.setCountry("Canada");
        attractions.add(attraction2);
        wantedLocations.add("McDonalds");

        PrepareLocationsOutputBoundary outputBoundary = new PrepareLocationsOutputBoundary() {
            @Override
            public void presentLocationsOutput(PrepareLocationsOutputData data) {
                HashMap<AttractionData, String> result = new HashMap<>();
                result.put(attraction1, "1200-1245");
                result.put(attraction2, "1245-1315");
                Assertions.assertEquals(result, data.getCleanedLocations());
            }
        };

        PrepareLocationsInputData inputData = new PrepareLocationsInputData(wantedLocations, attractions, 1200);
        PrepareLocationsInputBoundary interactor = new PrepareLocationsInteractor(outputBoundary);
        interactor.prepareLocations(inputData);

    }
}

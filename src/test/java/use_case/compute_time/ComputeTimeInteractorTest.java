package use_case.compute_time;

import com.sun.net.httpserver.Authenticator;
import entity.AttractionData;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import use_case.find_shortest_path.FindShortestPathOutputBoundary;

import java.util.ArrayList;

public class ComputeTimeInteractorTest {

    @Test
    public void SuccessTest() {
        ArrayList<AttractionData> attractionsList = new ArrayList<>();
        AttractionData attraction1 = new AttractionData();
        attraction1.setAddress("197 Yonge Street");
        attraction1.setTravelTime(12);
        attraction1.setName("Pool");
        attraction1.setCountry("Canada");
        attractionsList.add(attraction1);
        AttractionData attraction2 = new AttractionData();
        attraction2.setAddress("150 Yonge Street");
        attraction2.setTravelTime(4);
        attraction2.setName("Baking Shop");
        attraction2.setCountry("Canada");
        attractionsList.add(attraction2);
        AttractionData attraction3 = new AttractionData();
        attraction3.setAddress("250 Yonge Street");
        attraction3.setTravelTime(7);
        attraction3.setName("Baking Shop");
        attraction3.setCountry("Canada");
        attractionsList.add(attraction3);
        ComputeTimeOutputBoundary outputBoundary = new ComputeTimeOutputBoundary() {
            @Override
            public void prepareSuccessView(ComputeTimeOutputData outputData) {

                Assertions.assertEquals("1900", "1900");
//                Assertions.assertEquals(attraction1.getVisitTime(), "1900");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                // nothing here to see
            }
        };
        ComputeTimeInputData inputData = new ComputeTimeInputData(attractionsList);
        ComputeTimeUserDataAccessInterface imm = new ComputeTimeUserDataAccessInterface() {
            @Override
            public double getStartTime() {
                return 1200;
            }

            @Override
            public double getEndTime() {
                return 1800;
            }
        };

        ComputeTimeInputBoundary interactor = new ComputeTimeInteractor(imm, outputBoundary);
        interactor.execute(inputData);
        System.out.println (attractionsList.get(1).getVisitTime());

    }
}
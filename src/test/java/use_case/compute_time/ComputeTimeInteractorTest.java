package use_case.compute_time;

import entity.AttractionData;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ComputeTimeInteractorTest {

    @Test
    public void successTest() {
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
        attraction3.setTravelTime(10);
        attraction3.setName("Baking Shop");
        attraction3.setCountry("Canada");
        attractionsList.add(attraction3);
        AttractionData attraction4 = new AttractionData();
        attraction4.setAddress("777 College Street");
        attraction4.setTravelTime(0);
        attraction4.setName("Service Ontario");
        attraction4.setCountry("Canada");
        attractionsList.add(attraction4);
        ComputeTimeOutputBoundary outputBoundary = new ComputeTimeOutputBoundary() {
            @Override
            public void prepareSuccessView(ComputeTimeOutputData outputData) {
                assertEquals(attraction1.getVisitTime(), "12:12 - 13:35");
                assertEquals(attraction2.getVisitTime(), "13:47 - 15:10");
                assertEquals(attraction3.getVisitTime(), "15:14 - 16:37");
                assertEquals(attraction4.getVisitTime(), "16:47 - 18:10");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };
        ComputeTimeInputData inputData = new ComputeTimeInputData(attractionsList);
        ComputeTimeUserDataAccessInterface computeTimeUserDataAccessInterface =
                new ComputeTimeUserDataAccessInterface() {
            @Override
            public Date getStartTime() {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 12); // 12:00 PM
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                return calendar.getTime();
            }

            @Override
            public Date getEndTime() {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 18); // 6:00 PM
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                return calendar.getTime();
            }

            @Override
            public Image getMapImage() {
                return null;
            }
        };

        ComputeTimeInputBoundary interactor = new ComputeTimeInteractor(computeTimeUserDataAccessInterface,
                outputBoundary);
        interactor.execute(inputData);
    }
}

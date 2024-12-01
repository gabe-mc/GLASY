package use_case.save_itinerary;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class SaveItineraryInteractorTest {
    String filePath = "C:\\Users\\Lily";

    @Test
    public void SuccessTest(){
        SaveItineraryOutputBoundary outputBoundary = new SaveItineraryOutputBoundary() {
            @Override
            public void presentOutput(SaveItineraryOutputData outputData) {
                Assertions.assertEquals("C:\\Users\\Lily", filePath);
            }

            @Override
            public void switchToPreviousView() {
                // Nothing needed here
            }
        };
    }

}

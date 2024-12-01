package use_case.save_itinerary;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class SaveItineraryInteractorTest {
    String filePath = "C:\\Users\\Lily";

    @Test
    public void successTest(){
        SaveItineraryOutputBoundary outputBoundary = new SaveItineraryOutputBoundary() {
            @Override
            public void switchToPreviousView() {
                // Nothing needed here
            }
        };
    }

}

package use_case.save_itinerary;

import org.junit.jupiter.api.Test;


class SaveItineraryInteractorTest {
    String filePath = "C:\\Users\\Lily";

    @Test
    void successTest(){
        SaveItineraryOutputBoundary outputBoundary = new SaveItineraryOutputBoundary() {
            @Override
            public void switchToPreviousView() {
                // Nothing needed here
            }
        };
    }

}

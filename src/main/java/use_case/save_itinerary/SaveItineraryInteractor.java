package use_case.save_itinerary;

import java.io.FileWriter;
import java.io.IOException;


public class SaveItineraryInteractor implements SaveItineraryInputBoundary {
    private final SaveItineraryOutputBoundary saveItineraryPresenter;

    public SaveItineraryInteractor(SaveItineraryOutputBoundary saveItineraryOutputBoundary) {
        this.saveItineraryPresenter = saveItineraryOutputBoundary;
    }

    @Override
    public void execute(SaveItineraryInputData inputData) {
        String filePath = inputData.getFilePath() + "/" + inputData.getFileName() + ".txt";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(inputData.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        SaveItineraryOutputData outputData = new SaveItineraryOutputData(filePath);
        saveItineraryPresenter.presentOutput(outputData);
    }

    @Override
    public void switchToPreviousView() {
        saveItineraryPresenter.switchToPreviousView();
    }
}

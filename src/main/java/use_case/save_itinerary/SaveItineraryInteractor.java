package use_case.save_itinerary;

import use_case.find_shortest_path.FindShortestPathOutputData;
import use_case.save_itinerary.SaveItineraryOutputBoundary;

import java.io.FileWriter;
import java.io.IOException;


public class SaveItineraryInteractor implements SaveItineraryInputBoundary {
    private final SaveItineraryOutputBoundary outputBoundary;

    public SaveItineraryInteractor(SaveItineraryOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void saveText(SaveItineraryInputData inputData) {
        String filePath = inputData.getFilePath() + "/" + inputData.getFileName() + ".txt";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(inputData.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        SaveItineraryOutputData outputData = new SaveItineraryOutputData(filePath);
        outputBoundary.presentOutput(outputData);
    }
}

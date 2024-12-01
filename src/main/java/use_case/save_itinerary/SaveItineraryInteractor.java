package use_case.save_itinerary;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class SaveItineraryInteractor implements SaveItineraryInputBoundary {
    private final SaveItineraryOutputBoundary saveItineraryPresenter;

    public SaveItineraryInteractor(SaveItineraryOutputBoundary saveItineraryOutputBoundary) {
        this.saveItineraryPresenter = saveItineraryOutputBoundary;
    }

    @Override
    public void execute(SaveItineraryInputData inputData) {
        String filePath = inputData.getFilePath() + "/" + inputData.getFileName() + ".txt";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Itinerary Planner\n");
            List<String[]> itinerary = inputData.getContent();
            for (String[] info : itinerary) {
                writer.write(info[0] + " - " + info[1] + "(" + info[2] + ")\n");
            }
        } catch (Exception e) {
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

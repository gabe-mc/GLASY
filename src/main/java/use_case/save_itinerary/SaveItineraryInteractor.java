package use_case.save_itinerary;

import java.io.FileWriter;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * The Save Itinerary Interactor.
 */
public class SaveItineraryInteractor implements SaveItineraryInputBoundary {
    private final SaveItineraryOutputBoundary saveItineraryPresenter;

    public SaveItineraryInteractor(SaveItineraryOutputBoundary saveItineraryOutputBoundary) {
        this.saveItineraryPresenter = saveItineraryOutputBoundary;
    }

    @Override
    public void execute(SaveItineraryInputData inputData) {
        String filePath = inputData.getFilePath() + "/" + inputData.getFileName() + ".md";
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
        String date = ft.format(new Date());
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("# Your Itinerary\n");
            writer.write("## " + date + "\n\n");
            List<String[]> itinerary = inputData.getContent();
            for (String[] info : itinerary) {
                writer.write("### " + info[0] + "\n");
                writer.write("- " + info[1] + "\n");
                writer.write("");
                writer.write("  - *" + info[2] + "*\n\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void switchToPreviousView() {
        saveItineraryPresenter.switchToPreviousView();
    }
}

package use_case.save_itinerary;

public class SaveItineraryOutputData {
    private final String filePath;

    public SaveItineraryOutputData(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}

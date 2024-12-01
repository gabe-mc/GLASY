package use_case.save_itinerary;

import java.util.List;

/**
 * The Input Data for the Save Itinerary Use Case.
 */
public class SaveItineraryInputData {
    private List<String[]> content;
    private String filePath;
    private String fileName;

    public String getFileName() {
        return fileName;
    }
    public List<String[]> getContent() {
        return content;
    }
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public void setContent(List<String[]> content) {
        this.content = content;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

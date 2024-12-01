package use_case.use_current_location;

public interface UseCurrentLocationOutputBoundary {

    void updateCurrentLocation(UseCurrentLocationOutputData outputData);

    void prepareFailView(String errorMessage);

}

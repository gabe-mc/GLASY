package use_case.find_shortest_path;

/**
 * The output interface for the Find Shortest Path Use Case.
 */
public interface FindShortestPathOutputBoundary {
    void prepareFailView(String errorMessage);

    void switchToPreviousView();
}

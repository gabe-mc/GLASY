package use_case.find_shortest_path;

/**
 * The output boundary for the Find Shortest Path Use Case.
 */
public interface FindShortestPathOutputBoundary {
    /**
     * Prepares the fail view for the Find Shortest Path Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Choose Options View.
     */
    void switchToPreviousView();
}

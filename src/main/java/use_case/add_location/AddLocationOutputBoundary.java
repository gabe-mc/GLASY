package use_case.add_location;

public interface AddLocationOutputBoundary {

    /**
     * Prepares the success view for the Add Location Use Case.
     * @param addLocationOutputData the output data
     */
    void prepareSuccessView(AddLocationOutputData addLocationOutputData);

    /**
     * Prepares the failure view for the Add Location Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);


}

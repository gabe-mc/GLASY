package use_case.add_location;

/**
 * The `AddLocationInputData` class serves as a data container for the input parameters required
 * to fetch location data. It encapsulates details such as the search radius, categories,
 * and the maximum number of locations to retrieve.
 */
public class AddLocationInputData {
    private String radius;
    private String categories;
    private String maxLocations;

    /**
     * Constructs a new `AddLocationInputData` instance with the specified parameters.
     *
     * @param radius       the search radius for locations (e.g., in meters or kilometers).
     * @param categories   the categories to filter the locations by (e.g., "restaurants", "parks").
     * @param maxLocations the maximum number of locations to retrieve, provided as a string.
     */
    public AddLocationInputData(String radius, String categories, String maxLocations) {
        this.radius = radius;
        this.categories = categories;
        this.maxLocations = maxLocations;
    }

    /**
     * Gets the search radius.
     *
     * @return the radius as a string.
     */
    public String getRadius() {
        return radius;
    }

    /**
     * Gets the location categories.
     *
     * @return the categories as a string.
     */
    public String getCategories() {
        return categories;
    }

    /**
     * Gets the maximum number of locations to retrieve as an integer.
     *
     * @return the maximum number of locations.
     * @throws NumberFormatException if `maxLocations` cannot be parsed as an integer.
     */
    public Integer getMaxLocations() {
        return Integer.parseInt(maxLocations);
    }
}

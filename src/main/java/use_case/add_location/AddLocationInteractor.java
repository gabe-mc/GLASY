package use_case.add_location;
import com.glasy.use_case.set_user_info.GeoCoordinates;
import com.google.gson.Gson;
import data_access.FoursquareLocationProvider;
import entity.AttractionData;
import entity.LocationData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// TODO: Controller for AddLocationInputBoundary has not been implemented yet.
/**
 * The `AddLocationInteractor` class is responsible for managing the logic of retrieving location data,
 * processing it into a list of structured objects, and preparing the success view for presentation.
 * It interacts with the data access layer to fetch data and the output boundary to present results.
 */
public class AddLocationInteractor implements AddLocationInputBoundary{
    private final AddLocationDataAccessInterface userDataAccessObject;
    private final AddLocationOutputBoundary userPresenter;
    final AddLocationOutputData addLocationOutputData = new AddLocationOutputData();

    public AddLocationInteractor (AddLocationOutputBoundary userPresenter, AddLocationDataAccessInterface userDataAccessObject) {
        this.userPresenter = userPresenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    /**
     * Converts JSON location data from the data access object into a list of `LocationData` objects,
     * limiting the number of results based on the provided `maxLocations` parameter.
     *
     * @param maxLocations the maximum number of locations to include in the result list.
     */
    public void resultToList(int maxLocations) {
        final Gson gson = new Gson();
        JSONArray locations = userDataAccessObject.getJSONResults().getJSONArray("results");
        List<LocationData> result = new ArrayList<>();

        for (Object n: locations) {

            JSONObject node = (JSONObject) n;
            JSONObject loc = node.getJSONObject("location");

            AttractionData locationNode = gson.fromJson(loc.toString(), AttractionData.class);
            locationNode.setName(node.getString("name"));
            result.add(locationNode);

            maxLocations--;
            if (maxLocations == 0) {
                break;
            }
        }
        addLocationOutputData.setLocationDataList(result);
    }

    /**
     * Executes the Interactor's main logic by retrieving location data based on the input parameters,
     * converting the results into a list, and preparing the success view for the presenter.
     *
     * @param addLocationInputData the input data containing parameters such as radius, categories, and max locations.
     * @throws IOException if an error occurs during the data retrieval process.
     */
    @Override
    public void execute(AddLocationInputData addLocationInputData) throws IOException {
        GeoCoordinates geoCoordinates = new GeoCoordinates();
        Map<String, String> params = new HashMap<>();

        params.put("radius", addLocationInputData.getRadius());
        params.put("categories", addLocationInputData.getCategories());
        JSONObject getResult = FoursquareLocationProvider.get(geoCoordinates, params);
        userDataAccessObject.setJSONResults(getResult);
        resultToList(addLocationInputData.getMaxLocations());

        userPresenter.prepareSuccessView(addLocationOutputData);
    }
}

package use_case.choose_options;

import com.google.gson.Gson;
import data_access.FoursquareLocationProvider;
import data_access.UserDataAccessObject;
import entity.AttractionData;
import entity.CommonLocationData;
import entity.LocationData;
import entity.Settings;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The interactor data for the Set User Info Use Case.
 */
public class ChooseOptionsInteractor implements ChooseOptionsInputBoundary {
    private final FoursquareLocationProvider foursquareLocationProvider;
    private final ChooseOptionsUserDataAccessInterface userDataAccessObject;
    private final ChooseOptionsOutputBoundary chooseOptionsPresenter;

    public ChooseOptionsInteractor(FoursquareLocationProvider foursquareLocationProvider,
                                   ChooseOptionsUserDataAccessInterface userDataAccessInterface,
                                   ChooseOptionsOutputBoundary chooseOptionsOutputBoundary) {
        this.foursquareLocationProvider = foursquareLocationProvider;
        this.userDataAccessObject = userDataAccessInterface;
        this.chooseOptionsPresenter = chooseOptionsOutputBoundary;
    }

    /**
     * Executes the Interactor's main logic by retrieving location data based on the input parameters,
     * converting the results into a list, and preparing the success view for the presenter.
     *
     * @param chooseOptionsInputData the input data containing parameters such as radius, categories, and max locations.
     */
    @Override
    public void execute(ChooseOptionsInputData chooseOptionsInputData) {
//        Settings settings = chooseOptionsInputData.getSettings();
//        GeoCoordinates geoCoordinates = new GeoCoordinates();
//        Map<String, String> params = new HashMap<>();
//
//        params.put("radius", setings.getRadius());
//        params.put("categories", settings.getCategories());
//        JSONObject getResult = FoursquareLocationProvider.get(settings.get, params);
//        userDataAccessObject.setJSONResults(getResult);
//        resultToList(chooseOptionsInputData.getMaxLocations());
//
//        userPresenter.prepareSuccessView(addLocationOutputData);
    }

    @Override
    public void switchToPreviousView() {
        chooseOptionsPresenter.switchToPreviousView();
    }

    /**
     * Converts JSON location data from the data access object into a list of `LocationData` objects,
     * limiting the number of results based on the provided `maxLocations` parameter.
     *
     * @param maxLocations the maximum number of locations to include in the result list.
     */
//    public void resultToList(int maxLocations) {
//        final Gson gson = new Gson();
//        JSONArray locations = userDataAccessObject.getJSONResults().getJSONArray("results");
//        List<LocationData> result = new ArrayList<>();
//
//        for (Object n: locations) {
//
//            JSONObject node = (JSONObject) n;
//            JSONObject loc = node.getJSONObject("location");
//
//            AttractionData locationNode = gson.fromJson(loc.toString(), AttractionData.class);
//            locationNode.setName(node.getString("name"));
//            result.add(locationNode);
//
//            maxLocations--;
//            if (maxLocations == 0) {
//                break;
//            }
//        }
//        addLocationOutputData.setLocationDataList(result);
//    }
}
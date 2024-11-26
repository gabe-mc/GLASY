package data_access;

import com.google.gson.Gson;
import entity.CommonLocationData;
import entity.LocationData;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.choose_options.ChooseOptionsFoursquareLocationProviderInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FoursquareLocationProvider implements ChooseOptionsFoursquareLocationProviderInterface {
    private static JSONObject JSONResults;
    private static List<CommonLocationData> listResults;

    /**
     * Retrieves location data from the Foursquare Places API based on the provided geographic coordinates
     * and optional query parameters.
     *
     * @param location The geographic coordinates (latitude and longitude) to search around.
     * @param params A map of optional query parameters to refine the search. Can be {@code null} or empty.
     * @return A JSON string representing the locations returned by the Foursquare API. The string is formatted with an
     *         indent factor of 4 for readability.
     * @throws IOException If an I/O error occurs while making the HTTP request or reading the response.
     */
    public static JSONObject get(CommonLocationData location, Map<String, String> params) throws IOException {
        final String latLong = location.getLatitude() + "," + location.getLongitude();

        final String baseUrl = "https://api.foursquare.com/v3/places/search";
        final StringBuilder urlBuilder = new StringBuilder(baseUrl);

        urlBuilder.append("?");
        urlBuilder.append("ll=").append(latLong).append("&");
        if (params != null && !params.isEmpty()) {
            params.forEach((key, value) -> {
                if (key != null && value != null) {
                    urlBuilder.append(key).append("=").append(value).append("&");
                }
            });
        }
        urlBuilder.setLength(urlBuilder.length() - 1);

        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", ConfigLoader.getKey("foursquare.api.key"))
                .build();

        JSONObject locations = new JSONObject();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                locations = new JSONObject(response.body().string());
            }
        }
        return locations;
    }

//    public static void resultToList(Location example, int max_items) {
//        final Gson gson = new Gson();
//        JSONArray locations = example.getJSONResults().getJSONArray("results");
//        List<CommonLocationData> result = new ArrayList<>();
//
//        for (Object n: locations) {
//
//            JSONObject node = (JSONObject) n;
//            JSONObject loc = node.getJSONObject("location");
//
//            CommonLocationData locationNode = gson.fromJson(loc.toString(), CommonLocationData.class);
////            locationNode.setName(node.getString("name"));
//            result.add(locationNode);
//
//            max_items--;
//            if (max_items == 0) {
//                break;
//            }
//        }
//        example.setListResults(result);
//    }

    @Override
    public List<LocationData> getLocations() {
        return List.of();
    }
}
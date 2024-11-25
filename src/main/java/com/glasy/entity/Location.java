package com.glasy.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glasy.use_case.set_user_info.GeoCoordinates;
import entity.CommonLocationData;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import data_access.ConfigLoader;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Class to get nearby locations.
 */
public class Location {

    private static JSONObject JSONResults;
    private static List<CommonLocationData> listResults;

    /**
     * Retrieves location data from the Foursquare Places API based on the provided geographic coordinates
     * and optional query parameters.
     *
     * @param geoCoordinates The geographic coordinates (latitude and longitude) to search around.
     * @param params A map of optional query parameters to refine the search. Can be {@code null} or empty.
     * @return A JSON string representing the locations returned by the Foursquare API. The string is formatted with an
     *         indent factor of 4 for readability.
     * @throws IOException If an I/O error occurs while making the HTTP request or reading the response.
     */
    public static JSONObject getLocations(GeoCoordinates geoCoordinates, Map<String, String> params) throws IOException {
        final String latLong = geoCoordinates.getLatitude() + "," + geoCoordinates.getLongitude();

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
                final JSONObject jsonResponse = new JSONObject(response.body().string());
                locations = jsonResponse;
            }
        }
        return locations;
    }

    public static void resultToList(Location example, int max_items) {
        final Gson gson = new Gson();
        JSONArray locations = example.getJSONResults().getJSONArray("results");
        List<CommonLocationData> result = new ArrayList<>();

        for (Object n: locations) {

            JSONObject node = (JSONObject) n;
            JSONObject loc = node.getJSONObject("location");

            CommonLocationData locationNode = gson.fromJson(loc.toString(), CommonLocationData.class);
//            locationNode.setName(node.getString("name"));
            result.add(locationNode);

            max_items--;
            if (max_items == 0) {
                break;
            }
        }
        example.setListResults(result);
    }

    public JSONObject getJSONResults() {
        return JSONResults;
    }

    public void setJSONResults(JSONObject results) {
        this.JSONResults = results;
    }

    public List<CommonLocationData> getListResults() {
        return listResults;
    }

    public void setListResults(List<CommonLocationData> listResults) {
        this.listResults = listResults;
    }

    public static void main(String[] args) throws IOException {
        GeoCoordinates geoCoordinates = new GeoCoordinates();
        Location example = new Location();
        Map<String, String> params = new HashMap<>();

        params.put("radius", "800");
        params.put("categories", "13000"); // Dining & Drinking
        JSONObject getResult = Location.getLocations(geoCoordinates, params);
        System.out.println(getResult.toString(3));
        example.setJSONResults(getResult);
        resultToList(example, 5);
    }
}

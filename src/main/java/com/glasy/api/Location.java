package com.glasy.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glasy.LocationData;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import com.glasy.config.ConfigLoader;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Class to get nearby locations.
 */
public class Location {
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

    public static void main(String[] args) throws IOException {
        GeoCoordinates geoCoordinates = new GeoCoordinates();
        Location example = new Location();
        Map<String, String> params = new HashMap<>();
        Gson gson = new Gson();

        params.put("radius", "5000");
        params.put("categories", "13000"); // Dining & Drinking
        JSONObject getResult = example.getLocations(geoCoordinates, params);
        JSONArray locations = getResult.getJSONArray("results");

        List result = new ArrayList();

        for (Object n: locations) {
            JSONObject node = (JSONObject) n;
            LocationData locationNode = new LocationData();
            JSONObject loc = node.getJSONObject("location");

            locationNode = gson.fromJson(loc.toString(), LocationData.class);
            locationNode.setName(node.getString("name"));
            result.add(locationNode);

            System.out.println(locationNode.getName() + " " + locationNode.getAddress());
            System.out.println(result);

        }
    }
}

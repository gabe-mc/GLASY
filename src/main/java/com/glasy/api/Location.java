package com.glasy.api;

import com.glasy.config.ConfigLoader;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Location {
    public static String getLocations(GeoCoordinates geoCoordinates, Map<String, String> params) throws IOException {
        String latLong = geoCoordinates.getLatitude() + "," + geoCoordinates.getLongitude();

        String baseUrl = "https://api.foursquare.com/v3/places/search";
        StringBuilder urlBuilder = new StringBuilder(baseUrl);

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

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", ConfigLoader.getKey("foursquare.api.key"))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                JSONObject jsonResponse = new JSONObject(response.body().string());
                return jsonResponse.toString(4);
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        GeoCoordinates geoCoordinates = new GeoCoordinates();
        Location example = new Location();
        Map<String, String> params = new HashMap<>();
        params.put("radius", "1000");
        params.put("categories", "13000"); // Dining & Drinking
        String getResponse = example.getLocations(geoCoordinates, params);
        System.out.println(getResponse);
    }
}

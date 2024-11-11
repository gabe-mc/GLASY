package com.glasy.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class googleAPI {

    OkHttpClient client = new OkHttpClient();
    String APIKEY = "AIzaSyBweNOe6qrpFyf7xeKEX360vCn4UbQ30AU";

    /**
     * Constructs a URL for the Google Maps Geocoding API based on the provided address.
     *
     * @param address The address to be converted into a geocoding API request URL.
     * @return A formatted URL string that can be used to send a geocoding request to the Google Maps API.
     * @throws IOException If an encoding error occurs while processing the address components.
     */
    public String createGeocodeUrl(String address) throws IOException {
        StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/geocode/json?address=");
        String[] address_components = address.split(" ");
        for (String component : address_components) {
            if (component.contains(",")) {
                url.append(component, 0, component.length() - 1);
            }
            else {
                url.append(component).append("%20");
            }
        }
        return url.substring(0, url.length() - 2) + "&key=" + APIKEY;
    }

    public String createDistanceMatrixUrl(String address1, String address2) throws IOException {
        StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/distancematrix/json?destinations=");
        String[] address_1_components = address1.split(" ");
        for (String component : address_1_components) {
            if (component.contains(",")) {
                url.append(component, 0, component.length() - 1);
            }
            else {url.append(component).append("%20");}
        }
        url.append("&origins=");

        String[] address_2_components = address2.split(" ");
        for (String component : address_2_components) {
            if (component.contains(",")) {
                url.append(component, 0, component.length() - 1);
            }
            else {url.append(component).append("%20");}
        }
        return url.substring(0, url.length() - 2) + "&key=" + APIKEY;
    }

    /**
     * Retrieves the latitude and longitude for a given address by sending a request
     * to a geocoding API and parsing the JSON response.
     *
     * @param address The address to be geocoded.
     * @return An ArrayList containing the latitude and longitude as Double values.
     *         The first element is the latitude, and the second element is the longitude.
     * @throws IOException If an input or output exception occurs while sending the request
     *                     or receiving the response.
     */
    public ArrayList<Double> getAddress(String address) throws IOException {
        String url = createGeocodeUrl(address);
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String responseBody = response.body().string();
            JSONObject jsonObject = new JSONObject(responseBody);
            JSONObject locationData = jsonObject.
                    getJSONArray("results").
                    getJSONObject(0).
                    getJSONObject("geometry").
                    getJSONObject("location");
            ArrayList<Double> result = new ArrayList<>();
            result.add(locationData.getDouble("lat"));
            result.add(locationData.getDouble("lng"));
            return result;
        }
        }

    /**
     * Calculates the distance between two locations using the Google Maps Distance Matrix API.
     *
     * @param address1 The starting address for the distance calculation.
     * @param address2 The destination address for the distance calculation.
     * @return A string representing the distance between the two locations in KM, rounded to one decimal place.
     * @throws IOException If an error occurs while building the request or processing the response.
     */
    public Float matrixDistance(String address1, String address2) throws IOException {
        String url = createDistanceMatrixUrl(address1, address2);
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String responseBody = response.body().string();
            JSONObject jsonObject = new JSONObject(responseBody);
            String result = jsonObject.
                    getJSONArray("rows").
                    getJSONObject(0).
                    getJSONArray("elements").
                    getJSONObject(0).
                    getJSONObject("distance").
                    getString("text");
            return Float.parseFloat(result.substring(0, result.length() - 2));
        }
    }

    /**
     * Determines the order of locations from closest to furthest starting from the initial location.
     * This method calculates pairwise distances between the starting location and remaining locations,
     * ordering them by the shortest route based on distance.
     *
     * @param locations An ArrayList of location names or addresses, where the first location is
     *                  the starting point.
     * @return An ArrayList of locations ordered from the starting location to the furthest, with each
     *         subsequent location being the closest unvisited location.
     * @throws IOException If an error occurs during the distance calculation requests.
     */
    public ArrayList<String> findShortestRoute(ArrayList<String> locations) throws IOException {
        String origin = locations.get(0);
        ArrayList<String> result = new ArrayList<>();
        result.add(origin);
        HashMap<String, Float> temp = new HashMap<>();
        int size = locations.size() - 2;
        int i = 0;
        while (i < size) {
            for (int j = 1; j < locations.size(); j++) {
                Float dist = matrixDistance(locations.get(i), locations.get(j));
                temp.put(locations.get(j), dist);
            }
            String minName = "";
            Float minValue = 999999999999999F;
            int index = 0;
            for (String location: temp.keySet()) {
                if (temp.get(location) < minValue) {
                    minName = location;
                    minValue = temp.get(location);

                }
            }
            System.out.println(temp);
            result.add(minName);
            locations.remove(minName);
            System.out.println(locations);
            temp = new HashMap<>();
            i += 1;

        }
        result.add(locations.get(1));
        return result;
    }

    /**
     * Generates a Google Maps link for a route with multiple destinations.
     *
     * @param destinations An ArrayList of destination addresses.
     * @return A URL string for Google Maps showing a route through the specified destinations.
     */
    public String generateMapsLink(ArrayList<String> destinations) {
        StringBuilder url = new StringBuilder("https://www.google.ca/maps/dir/");
        for (String destination : destinations) {
            String[] address_1_components = destination.split(" ");
            for (String component : address_1_components) {
                if (component.contains(",")) {
                    url.append(component, 0, component.length() - 1);
                }
                else {url.append(component).append("%20");}
            }
            url.append("/");
        }
        return url.toString();
    }

}


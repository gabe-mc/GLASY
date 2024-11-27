package data_access;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static java.lang.Integer.parseInt;

/**
 * The DAO for google maps data.
 */
public class GoogleMapsLocationProvider {

    private final OkHttpClient client = new OkHttpClient();
    private final String apiKey = ConfigLoader.getKey("google.api.key");

    /**
     * Constructs a URL for the Google Maps Geocoding API based on the provided address.
     *
     * @param address The address to be converted into a geocoding API request URL.
     * @return A formatted URL string that can be used to send a geocoding request to the Google Maps API.
     * @throws IOException If an encoding error occurs while processing the address components.
     */
    public String createGeocodeUrl(String address) throws IOException {
        final StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/geocode/json?address=");
        formatAddress(address, url);
        return url.substring(0, url.length() - 2) + "&key=" + apiKey;
    }

    /**
     * Constructs a Google Maps Distance Matrix API URL for calculating the distance
     * between two given addresses.
     *
     * @param address1 The origin address, which will be used as the starting point for the distance calculation.
     * @param address2 The destination address, which will be used as the endpoint for the distance calculation.
     * @return A properly formatted URL to the Google Maps Distance Matrix API for the given addresses.
     * @throws IOException If there is an issue with network connectivity or API access during URL construction.
     */
    public String createDistanceMatrixUrl(String address1, String address2) throws IOException {
        final StringBuilder url = new
                StringBuilder("https://maps.googleapis.com/maps/api/distancematrix/json?destinations=");
        formatAddress(address1, url);
        url.append("&origins=");

        formatAddress(address2, url);
        return url.substring(0, url.length() - 2) + "&key=" + apiKey;
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
    public ArrayList<Double> getLonLat(String address) throws IOException {
        final String url = createGeocodeUrl(address);
        final Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            final String responseBody = response.body().string();
            final JSONObject jsonObject = new JSONObject(responseBody);
            final JSONObject locationData = jsonObject
                    .getJSONArray("results")
                    .getJSONObject(0)
                    .getJSONObject("geometry")
                    .getJSONObject("location");
            final ArrayList<Double> result = new ArrayList<>();
            result.add(locationData.getDouble("lat"));
            result.add(locationData.getDouble("lng"));
            return result;
        }
    }

    /**
     * Retrieves the address for a given longitude and latitude by sending a request
     * to a geocoding API and parsing the JSON response.
     *
     * @param longitude The longitude of the address to be geocoded.
     * @param latitude The latitude of the address to be geocoded.
     * @return The address of the location.
     * @throws IOException If an input or output exception occurs while sending the request
     *                     or receiving the response.
     */
    public String getAddress(Double longitude, Double latitude) throws IOException {
        final String url = String.format(
                "https://maps.googleapis.com/maps/api/geocode/json?latlng=%f,%f&key=%s",
                latitude, longitude, apiKey);
        final Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            final String responseBody = response.body().string();
            final JSONObject jsonObject = new JSONObject(responseBody);
            return jsonObject.getJSONArray("results")
                    .getJSONObject(0)
                    .getString("formatted_address");
        }
    }

    public int calculateTravelTime(String address1, String address2) throws IOException {
        final String url = createDistanceMatrixUrl(address1, address2);
        final Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            final String responseBody = response.body().string();
            final JSONObject jsonObject = new JSONObject(responseBody);
            final  String result = jsonObject.getJSONArray("rows")
                    .getJSONObject(0)
                    .getJSONArray("elements")
                    .getJSONObject(0)
                    .getJSONObject("duration")
                    .getString("text");
            return parseInt(result.substring(0,result.length() -5));
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
        final String url = createDistanceMatrixUrl(address1, address2);
        final Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            final String responseBody = response.body().string();
            final JSONObject jsonObject = new JSONObject(responseBody);
            final String result = jsonObject
                    .getJSONArray("rows")
                    .getJSONObject(0)
                    .getJSONArray("elements")
                    .getJSONObject(0)
                    .getJSONObject("distance")
                    .getString("text");
            return Float.parseFloat(result.substring(0, result.length() - 2));
        }
    }

    /**
     * Generates a Google Maps link for a route with multiple destinations.
     *
     * @param destinations An ArrayList of destination addresses.
     * @return A URL string for Google Maps showing a route through the specified destinations.
     */
    public String generateMapsLink(ArrayList<String> destinations) {
        final StringBuilder url = new StringBuilder("https://www.google.ca/maps/dir/");
        for (String destination : destinations) {
            formatAddress(destination, url);
            url.append("/");
        }
        return url.toString();
    }

    /**
     * Formats an address string into a URL-friendly format and appends it to a given StringBuilder.
     * Spaces are replaced with "%20", and commas are removed.
     *
     * @param address the raw address string to be formatted
     * @param url the StringBuilder to which the formatted address will be appended
     */
    private static void formatAddress(String address, StringBuilder url) {
        final String[] addressComponents = address.split(" ");
        for (String component : addressComponents) {
            if (component.contains(",")) {
                url.append(component, 0, component.length() - 1);
            }
            else {
                url.append(component).append("%20");
            }
        }
    }
}

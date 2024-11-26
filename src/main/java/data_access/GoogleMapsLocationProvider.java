package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import entity.CommonLocationData;
import entity.LocationData;
import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import use_case.choose_options.ChooseOptionsGoogleMapsLocationProviderInterface;
import use_case.find_shortest_path.FindShortestPathInputData;

/**
 * The DAO for google maps data.
 */
public class GoogleMapsLocationProvider implements ChooseOptionsGoogleMapsLocationProviderInterface {

    private final OkHttpClient client = new OkHttpClient();
    private final String apiKey = ConfigLoader.getKey("google.api.key");

    /**
     * Retrieves the latitude and longitude for a given address by sending a request
     * to a geocoding API and parsing the JSON response.
     *
     * @param address The address to be geocoded.
     * @return Null if no address is found, otherwise the LocationData object
     * representing the address
     */
    public LocationData addressToLocation(String address) {
        StringBuilder stringBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/geocode/json?address=");
        formatAddress(address, stringBuilder);
        String url = stringBuilder.substring(0, stringBuilder.length() - 2) + "&key=" + apiKey;
        final Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            final JSONObject jsonObject = new JSONObject(response.body().string());
            if (jsonObject.getString("status").equals("OK")) {
                final JSONObject results = jsonObject.getJSONArray("results").getJSONObject(0);
                final JSONObject lonLat = results.getJSONObject("geometry").getJSONObject("location");
                final JSONArray addressComponents = results.getJSONArray("address_components");
                String locality = "", postcode = "", region = "", country = "";
                for (int i = 0; i < addressComponents.length(); i++) {
                    JSONObject component = addressComponents.getJSONObject(i);
                    String types = component.getJSONArray("types").toString();
                    String longName = component.getString("long_name");
                    if (types.contains("locality") || types.contains("administrative_area_level_2")) {
                        locality = longName;
                    } else if (types.contains("postal_code")) {
                        postcode = longName;
                    } else if (types.contains("administrative_area_level_1")) {
                        region = longName;
                    } else if (types.contains("country")) {
                        country = longName;
                    }
                }
                return new CommonLocationData(
                        lonLat.getDouble("lat"),
                        lonLat.getDouble("lng"),
                        results.getString("formatted_address"),
                        locality, postcode, region, country
                );
            }
            return null;
        } catch (IOException e) {
            return null;
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

    /**
     * Calculates the distance between two locations using the Google Maps Distance Matrix API.
     *
     * @param address1 The starting address for the distance calculation.
     * @param address2 The destination address for the distance calculation.
     * @return A string representing the distance between the two locations in KM, rounded to one decimal place.
     * @throws IOException If an error occurs while building the request or processing the response.
     */
    public Float matrixDistance(String address1, String address2) throws IOException {
        final StringBuilder stringBuilder = new
                StringBuilder("https://maps.googleapis.com/maps/api/distancematrix/json?destinations=");
        formatAddress(address1, stringBuilder);
        stringBuilder.append("&origins=");
        formatAddress(address2, stringBuilder);
        String url = stringBuilder.substring(0, stringBuilder.length() - 2) + "&key=" + apiKey;
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

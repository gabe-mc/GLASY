package data_access;

import java.io.IOException;
import java.util.ArrayList;

import entity.CommonLocationData;
import entity.LocationData;
import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static java.lang.Integer.parseInt;
import use_case.choose_options.ChooseOptionsGoogleMapsLocationProviderInterface;
import use_case.find_shortest_path.FindShortestPathGoogleMapsLocationProviderInterface;

/**
 * The DAO for google maps data.
 */
public class GoogleMapsLocationProvider implements
        ChooseOptionsGoogleMapsLocationProviderInterface,
        FindShortestPathGoogleMapsLocationProviderInterface {

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
        StringBuilder urlBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/geocode/json?address=");
        formatAddress(address, urlBuilder);
        String url = urlBuilder.substring(0, urlBuilder.length() - 2) + "&key=" + apiKey;
        final Request request = new Request.Builder().url(url).build();

        CommonLocationData result = null;

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
                result = new CommonLocationData(
                        lonLat.getDouble("lat"),
                        lonLat.getDouble("lng"),
                        results.getString("formatted_address"),
                        locality, postcode, region, country
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
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
    public String createDistanceMatrixUrl(String address1, String address2) {
        final StringBuilder url = new
                StringBuilder("https://maps.googleapis.com/maps/api/distancematrix/json?destinations=");
        formatAddress(address1, url);
        url.append("&origins=");

        formatAddress(address2, url);
        return url.substring(0, url.length() - 2) + "&key=" + apiKey;
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
    public String getAddress(Double latitude, Double longitude) throws IOException {
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

    public Integer calculateTravelTime(String address1, String address2) {
        Integer result = null;
        final String url = createDistanceMatrixUrl(address1, address2);
        final Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            final String responseBody = response.body().string();
            final JSONObject jsonObject = new JSONObject(responseBody);
            final  String travelTime = jsonObject.getJSONArray("rows")
                    .getJSONObject(0)
                    .getJSONArray("elements")
                    .getJSONObject(0)
                    .getJSONObject("duration")
                    .getString("text");
            result = parseInt(travelTime.substring(0,travelTime.length() -5));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Calculates the distance between two locations using the Google Maps Distance Matrix API.
     *
     * @param address1 The starting address for the distance calculation.
     * @param address2 The destination address for the distance calculation.
     * @return A string representing the distance between the two locations in KM, rounded to one decimal place.
     * @throws IOException If an error occurs while building the request or processing the response.
     */
    public Float matrixDistance(String address1, String address2) {
        Float result = null;
        final String url = createDistanceMatrixUrl(address1, address2);
        final Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            final String responseBody = response.body().string();
            final JSONObject jsonObject = new JSONObject(responseBody);
            final String distance = jsonObject
                    .getJSONArray("rows")
                    .getJSONObject(0)
                    .getJSONArray("elements")
                    .getJSONObject(0)
                    .getJSONObject("distance")
                    .getString("text");
            result = Float.parseFloat(distance.substring(0, distance.length() - 2));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Generates a Google Maps link for a route with multiple destinations.
     *
     * @param destinations An ArrayList of destination addresses.
     * @return A URL string for Google Maps showing a route through the specified destinations.
     */
    public String generateMapsLink(ArrayList<String> destinations) {
        final StringBuilder urlBuilder = new StringBuilder("https://www.google.ca/maps/dir/");
        for (String destination : destinations) {
            formatAddress(destination, urlBuilder);
            urlBuilder.append("/");
        }
        return urlBuilder.toString();
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
                url.append(component).append("%2C");
            }
        }
    }
}

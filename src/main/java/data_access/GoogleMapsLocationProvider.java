package data_access;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import entity.AttractionData;
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

    private static final OkHttpClient client = new OkHttpClient();
    private static final String apiKey = ConfigLoader.getKey("google.api.key");

    /**
     * Creates a Google Maps link to the users route
     *
     * @param address The addresses to be put into the route.
     * @return a String with the link to the map
     */
    public String createGeocodeUrl(ArrayList<AttractionData> address) throws IOException {
        final StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/geocode/json?address=");
        for (AttractionData data : address) {
            formatAddress(data.getAddress(), url);
            url.append("/");
        }
        return url.substring(0, url.length() - 2) + "&key=" + apiKey;
    }

    /**
     * Retrieves the latitude and longitude for a given address by sending a request
     * to a geocoding API and parsing the JSON response.
     *
     * @param address The address to be geocoded.
     * @return Null if no address is found, otherwise the LocationData object
     * representing the address
     */
    public AttractionData addressToLocation(String address) {
        StringBuilder urlBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/geocode/json?address=");
        formatAddress(address, urlBuilder);
        String url = urlBuilder.substring(0, urlBuilder.length() - 2) + "&key=" + apiKey;
        final Request request = new Request.Builder().url(url).build();

        AttractionData result = null;

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
                result = new AttractionData();
                result.setName(address);
                result.setLatitude(lonLat.getDouble("lat"));
                result.setLongitude(lonLat.getDouble("lng"));
                result.setAddress(results.getString("formatted_address"));
                result.setLocality(locality);
                result.setPostcode(postcode);
                result.setRegion(region);
                result.setCountry(country);
            }
        } catch (Exception e) {
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

    private static String getPolylines(List<String> coordinates) {
        String origin = coordinates.get(0); // First coordinate is the origin
        String destination = coordinates.get(coordinates.size() - 1); // Last coordinate is the destination
        StringBuilder waypoints = new StringBuilder();

        // Add waypoints (if any)
        for (int i = 1; i < coordinates.size() - 1; i++) {
            if (i > 1) waypoints.append("|");
            waypoints.append(coordinates.get(i));
        }

        try {
            String urlStr = String.format(
                    "https://maps.googleapis.com/maps/api/directions/json?origin=%s&destination=%s&waypoints=%s&key=%s",
                    URLEncoder.encode(origin, "UTF-8"),
                    URLEncoder.encode(destination, "UTF-8"),
                    URLEncoder.encode(waypoints.toString(), "UTF-8"),
                    apiKey
            );
            // Make HTTP request using OkHttp
            Request request = new Request.Builder()
                    .url(urlStr)
                    .build();
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            JSONObject jsonResponse = new JSONObject(responseBody);
            JSONArray routes = jsonResponse.getJSONArray("routes");

            if (!routes.isEmpty()) {
                JSONObject route = routes.getJSONObject(0);
                JSONObject polyline = route.getJSONObject("overview_polyline");
                return polyline.getString("points");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String generateStaticMapUrl(List<AttractionData> path) {
        List<String> coordinates = new ArrayList<>();

        for (LocationData attraction : path) {
            double latitude = attraction.getLatitude();
            double longitude = attraction.getLongitude();

            String coordinate = String.format("%.6f,%.6f", latitude, longitude);  // Ensures 6 decimal places
            coordinates.add(coordinate);
        }

        String polyline = getPolylines(coordinates);
        if (polyline == null) {
            return null;
        }

        try {
            StringBuilder markers = new StringBuilder();
            for (String coord : coordinates) {
                if (markers.length() > 0) markers.append("&");
                markers.append("markers=").append(URLEncoder.encode("color:red|" + coord, "UTF-8"));
            }

            // Build the Static Map URL
            return String.format(
                    "https://maps.googleapis.com/maps/api/staticmap?size=600x400&%s&path=enc:%s&key=%s",
                    markers.toString(),
                    polyline,
                    apiKey
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

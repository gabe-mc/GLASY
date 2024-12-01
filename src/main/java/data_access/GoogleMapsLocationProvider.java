package data_access;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import entity.AttractionData;
import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import use_case.choose_options.ChooseOptionsGoogleMapsLocationProviderInterface;
import use_case.find_shortest_path.FindShortestPathGoogleMapsLocationProviderInterface;

/**
 * Implementation for interacting with the Google Maps API.
 */
public class GoogleMapsLocationProvider implements
        ChooseOptionsGoogleMapsLocationProviderInterface,
        FindShortestPathGoogleMapsLocationProviderInterface {

    private static final OkHttpClient client = new OkHttpClient();
    private static final String apiKey = ConfigLoader.getKey("google.api.key");

    /**
     * Creates a Google Maps link to the users route
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

    @Override
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
     * @param address1 The origin address, which will be used as the starting point for the distance calculation.
     * @param address2 The destination address, which will be used as the endpoint for the distance calculation.
     * @return A properly formatted URL to the Google Maps Distance Matrix API for the given addresses.
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

    @Override
    public int[][][] getDistanceMatrix(List<AttractionData> locations) {
        int[][] distanceMatrix = new int[locations.size()][locations.size()];
        int[][] durationMatrix = new int[locations.size()][locations.size()];

        List<String> coordinates = new ArrayList<>();

        for (AttractionData attraction : locations) {
            double latitude = attraction.getLatitude();
            double longitude = attraction.getLongitude();
            String coordinate = String.format("%.6f,%.6f", latitude, longitude);  // Ensures 6 decimal places
            coordinates.add(coordinate);
        }
        String originsParam = String.join("|", coordinates);
        String destinationsParam = String.join("|", coordinates);

        String requestUrl = String.format("%s?origins=%s&destinations=%s&key=%s",
                "https://maps.googleapis.com/maps/api/distancematrix/json", originsParam, destinationsParam, apiKey);

        Request request = new Request.Builder()
                .url(requestUrl)
                .build();

        try {
            Response response = client.newCall(request).execute();

            String responseBody = response.body().string();
            JSONObject jsonResponse = new JSONObject(responseBody);

            JSONArray rows = jsonResponse.getJSONArray("rows");
            for (int i = 0; i < rows.length(); i++) {
                JSONArray elements = rows.getJSONObject(i).getJSONArray("elements");
                for (int j = 0; j < elements.length(); j++) {
                    JSONObject element = elements.getJSONObject(j);
                    if (element.getString("status").equals("OK")) {
                        // Get the distance
                        JSONObject distance = element.getJSONObject("distance");
                        JSONObject duration = element.getJSONObject("duration");
                        distanceMatrix[i][j] = distance.getInt("value");
                        durationMatrix[i][j] = duration.getInt("value");
                    } else {
                        distanceMatrix[i][j] = Integer.MAX_VALUE;
                        durationMatrix[i][j] = Integer.MAX_VALUE;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new int[][][]{distanceMatrix, durationMatrix};
    }

    /**
     * Generates a Google Maps link for a route with multiple destinations.
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

    /**
     * Generates a string representing the route through the list of coordinates.
     * @param coordinates the list of coordinates to generate the route through
     * @return A string representing the points on the polyline
     */
    private static String getPolylines(List<String> coordinates) {
        String origin = coordinates.get(0); // First coordinate is the origin
        String destination = coordinates.get(coordinates.size() - 1); // Last coordinate is the destination
        StringBuilder waypoints = new StringBuilder();

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

        for (AttractionData attraction : path) {
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
            for (int i = 0; i < coordinates.size(); i++) {
                String coord = coordinates.get(i);
                String label = i == 0 ? "S" : String.valueOf(i);
                String color = getColorFromValue((double) i / (coordinates.size() - 1));
                if (markers.length() > 0) markers.append("&");
                markers.append("markers=").append(URLEncoder.encode("color:" + color + "||label:" + label + "|" + coord, "UTF-8"));
            }

            // Build the Static Map URL
            return String.format(
                    "https://maps.googleapis.com/maps/api/staticmap?size=600x600&%s&path=enc:%s&key=%s",
                    markers.toString(),
                    polyline,
                    apiKey
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the 24-bit representation of the a double from 0 to 1 from red to blue.
     * @param value A decimal value from 0 to 1.
     * @return A string representation of the color, from red to blue.
     */
    private static String getColorFromValue(double value) {
        value = Math.max(0.0, Math.min(value, 1.0));
        int red = (int) ((1 - value) * 255);
        int blue = (int) (value * 255);
        int green = 0;
        int color = (red << 16) | (green << 8) | blue;
        return String.format("0x%06X", color);
    }
}

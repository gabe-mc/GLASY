package data_access;

import entity.AttractionData;
import entity.LocationData;
import entity.Settings;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.choose_options.ChooseOptionsFoursquareLocationProviderInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FoursquareLocationProvider implements ChooseOptionsFoursquareLocationProviderInterface {
    /**
     * Retrieves location data from the Foursquare Places API based on the provided geographic coordinates
     * and optional query parameters.
     *
     * @param settings A class representing of optional query parameters to refine the search.
     * @return A JSON string representing the locations returned by the Foursquare API. The string is formatted with an
     *         indent factor of 4 for readability.
     */
    @Override
    public List<AttractionData> getLocationList(Settings settings) {
        final Map<String, String> params = getParams(settings);

        final String baseUrl = "https://api.foursquare.com/v3/places/search";
        final StringBuilder urlBuilder = new StringBuilder(baseUrl);

        urlBuilder.append("?");
        if (!params.isEmpty()) {
            params.forEach((key, value) -> {
                if (key != null && value != null) {
                    urlBuilder.append(key).append("=").append(value.replace(",", "%2C")).append("&");
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

        List<AttractionData> locationList = new ArrayList<>();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                JSONArray results = new JSONObject(response.body().string()).getJSONArray("results");
                for (int i = 0; i < results.length(); i++) {
                    JSONObject result = results.getJSONObject(i);
                    JSONArray categories = result.getJSONArray("categories");
                    List<String> categoryNames = new ArrayList<>();
                    for (int j = 0; j < categories.length(); j++) {
                        JSONObject category = categories.getJSONObject(j);
                        String name = category.getString("name");
                        categoryNames.add(name);
                    }
                    JSONObject photo = result.getJSONArray("photos").optJSONObject(0);
                    String photoUrl = (photo != null) ?
                            photo.optString("prefix") + "original" + photo.getString("suffix") : "";
                    JSONObject geocodes = result.getJSONObject("geocodes").getJSONObject("main");
                    JSONObject positionData = result.getJSONObject("location");

                    AttractionData location = new AttractionData();
                    location.setName(result.getString("name"));
                    location.setCategories(categoryNames);
                    location.setRating(result.optDouble("rating"));
                    location.setPrice(result.optInt("price"));
                    location.setPhotoUrl(photoUrl);
                    location.setLatitude(geocodes.getDouble("latitude"));
                    location.setLongitude(geocodes.getDouble("longitude"));
                    location.setAddress(positionData.optString("formatted_address"));
                    location.setLocality(positionData.optString("locality"));
                    location.setPostcode(positionData.optString("postcode"));
                    location.setRegion(positionData.optString("region"));
                    location.setCountry(positionData.optString("country"));
                    locationList.add(location);
                }
                locationList = locationList.stream()
                        .filter(location -> location.getRating() >= settings.getMinStars())
                        .collect(Collectors.toList());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return locationList;
    }

    private static Map<String, String> getParams(Settings settings) {
        Map<String, String> params = new HashMap<>();
        LocationData location =  settings.getLocation();
        params.put("ll", location.getLatitude() + "," + location.getLongitude());
        params.put("radius", String.valueOf(settings.getMaxDistance() * 1000));
        params.put("limit", "50");
        params.put("sort", "POPULARITY");
        String categories = getCategories(settings);
        if (!categories.isEmpty()) {
            categories = categories.substring(0, categories.length() - 1); // Remove trailing comma
            params.put("categories", categories);
        }

        params.put("fields", "categories,geocodes,location,name,closed_bucket,rating,price,photos");
        return params;
    }

    private static String getCategories(Settings settings) {
        Map<String, Boolean> possibleLocationTypes = settings.getPossibleLocationTypes();
        Map<String, String> categoryMap = Map.of(
                "Attraction", "10000",  // Arts and Entertainment
                "Restaurant", "13000",  // Dining and Drinking
                "Shop", "17000"         // Retail
        );
        StringBuilder categoriesBuilder = new StringBuilder();
        possibleLocationTypes.forEach((key, value) -> {
            if (Boolean.TRUE.equals(value)) {
                categoriesBuilder.append(categoryMap.get(key)).append(",");
            }
        });
        return categoriesBuilder.toString();
    }
}
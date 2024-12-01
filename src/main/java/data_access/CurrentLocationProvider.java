package data_access;

import java.io.IOException;

import entity.AttractionData;
import org.json.JSONException;
import org.json.JSONObject;

import entity.CommonLocationData;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import use_case.start_app.StartAppCurrentLocationProviderInterface;

/**
 * Implementation of api call that determines the user's current location based on their ip address.
 */
public class CurrentLocationProvider implements StartAppCurrentLocationProviderInterface {
    private AttractionData currentLocationData;

    public CurrentLocationProvider() {
        final OkHttpClient client = new OkHttpClient();
        final String url = "http://ip-api.com/json/";
        final Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                final String responseBody = response.body().string();
                final JSONObject jsonObject = new JSONObject(responseBody);
                final Double longitude = jsonObject.getDouble("lon");
                final Double latitude = jsonObject.getDouble("lat");
                GoogleMapsLocationProvider googleMapsLocationProvider = new GoogleMapsLocationProvider();
                final String address = googleMapsLocationProvider.getAddress(latitude, longitude);
                currentLocationData = new AttractionData();
                currentLocationData.setLatitude(latitude);
                currentLocationData.setLongitude(longitude);
                currentLocationData.setAddress(address);
                currentLocationData.setLocality(jsonObject.getString("city"));
                currentLocationData.setPostcode(jsonObject.getString("zip"));
                currentLocationData.setRegion(jsonObject.getString("regionName"));
                currentLocationData.setCountry(jsonObject.getString("country"));
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public AttractionData getLocation() {
        return currentLocationData;
    }
}

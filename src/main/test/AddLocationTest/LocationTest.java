package api;

import com.glasy.LocationData;
import com.glasy.api.Location;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

class LocationTest {

    private static JSONObject getResult;
    private static Location example;

    @BeforeAll
    public static void setup() throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get("src/test/resources/responses.json")));
        getResult = new JSONObject(jsonString);
        example = new Location();
        example.setJSONResults(getResult);
        Location.resultToList(example, 5);
    }

    @Test
    void locationArraySizeTest() {
        assert example.getListResults().size() == 5;
    }

    @Test
    void locationArrayAddressTest() {
        LocationData node = example.getListResults().get(0);
        assert "196 Robert St".equals(node.getAddress());

    }
}

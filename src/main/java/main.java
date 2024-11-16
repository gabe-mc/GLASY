import com.glasy.api.GoogleMaps;

import java.io.IOException;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) throws IOException {
        GoogleMaps example = new GoogleMaps();
//        ArrayList<Double> getResponse = example.getAddress("197 Yonge St, Toronto ON");
        ArrayList<String> result = new ArrayList<>();
        result.add("47 Willcocks St, Toronto ON");
        result.add("197 Yonge St, Toronto ON");
        result.add("57 St Joseph St, Toronto ON");
        result.add("4 Hoskin Ave, Toronto ON");
        ArrayList<String> getResponse = example.findShortestRoute(result);
        System.out.println(example.generateMapsLink(getResponse));
    }
}

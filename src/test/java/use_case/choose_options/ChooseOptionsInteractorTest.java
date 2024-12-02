package use_case.choose_options;

import data_access.FoursquareLocationProvider;
import data_access.UserDataAccessObject;
import entity.AttractionData;
import entity.Settings;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ChooseOptionsInteractorTest {
    @Test
    void successTest() {
        AttractionData attraction = new AttractionData();
        attraction.setAddress("197 Yonge Street");
        attraction.setLatitude(43.653351);
        attraction.setLongitude(-79.379372);
        attraction.setTravelTime(12);
        attraction.setName("Pool");
        attraction.setCountry("Canada");
        Map<String, Boolean> possibleLocationTypes = new HashMap<>();
        possibleLocationTypes.put("Restaurant", true);
        possibleLocationTypes.put("Attraction", false);
        possibleLocationTypes.put("Shop", true);

        int maxDistance = 8;
        double minStars = 9.0; // From 0.0 to 10.0
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(Calendar.HOUR_OF_DAY, 12);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND, 0);
        startCalendar.set(Calendar.MILLISECOND, 0);
        Date startTime = startCalendar.getTime();
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(Calendar.HOUR_OF_DAY, 12);
        endCalendar.set(Calendar.MINUTE, 0);
        endCalendar.set(Calendar.SECOND, 0);
        endCalendar.set(Calendar.MILLISECOND, 0);
        Date endTime = endCalendar.getTime();
        Settings settings = new Settings(attraction, maxDistance, minStars, startTime, endTime, possibleLocationTypes);

        ChooseOptionsInputData chooseOptionsInputData = new ChooseOptionsInputData(settings);

        FoursquareLocationProvider foursquareLocationProvider = new FoursquareLocationProvider();
        UserDataAccessObject userDataAccessObject = new UserDataAccessObject();

        ChooseOptionsOutputBoundary successPresenter = new ChooseOptionsOutputBoundary() {
            @Override
            public void prepareSuccessView(ChooseOptionsOutputData outputData) {
                Map<AttractionData, Boolean> locationList = new LinkedHashMap<>();
                AttractionData attraction1 = new AttractionData();
                attraction1.setName("Uniqlo");
                locationList.put(attraction1, false);
                AttractionData attraction2 = new AttractionData();
                attraction2.setName("St. Lawrence Market Outdoor Vendors");
                locationList.put(attraction2, false);
                AttractionData attraction3 = new AttractionData();
                attraction3.setName("Loblaws");
                locationList.put(attraction3, false);
                for (Map.Entry<AttractionData, Boolean> entry : outputData.getCheckedLocationList().entrySet()) {
                    String attractionName = entry.getKey().getName();
                    boolean nameMatched = false;
                    for (AttractionData attraction : locationList.keySet()) {
                        if (attraction.getName().equals(attractionName)) {
                            nameMatched = true;
                            break;
                        }
                    }
                    assertTrue(nameMatched && !entry.getValue());
                }
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToPreviousView() {
                fail("Use case failure is unexpected.");
            }
        };

        ChooseOptionsInputBoundary interactor = new ChooseOptionsInteractor(foursquareLocationProvider,
                userDataAccessObject, successPresenter);
        interactor.execute(chooseOptionsInputData);
    }
}

package interface_adapter.choose_options;

import entity.AttractionData;
import interface_adapter.ViewManagerModel;
import interface_adapter.display_options.DisplayOptionsState;
import interface_adapter.display_options.DisplayOptionsViewModel;
import interface_adapter.splash_screen_view.SplashScreenViewModel;
import use_case.choose_options.ChooseOptionsOutputBoundary;
import use_case.choose_options.ChooseOptionsOutputData;

import java.util.List;
import java.util.Map;

public class ChooseOptionsPresenter implements ChooseOptionsOutputBoundary {

    private final ChooseOptionsViewModel chooseOptionsViewModel;
    private final SplashScreenViewModel splashScreenViewModel;
    private final DisplayOptionsViewModel displayOptionsViewModel;
    private final ViewManagerModel viewManagerModel;
    public ChooseOptionsPresenter(ViewManagerModel viewManagerModel,
                                  ChooseOptionsViewModel chooseOptionsViewModel,
                                  SplashScreenViewModel splashScreenViewModel,
                                  DisplayOptionsViewModel displayOptionsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.chooseOptionsViewModel = chooseOptionsViewModel;
        this.splashScreenViewModel = splashScreenViewModel;
        this.displayOptionsViewModel = displayOptionsViewModel;
    }

    @Override
    public void prepareSuccessView(ChooseOptionsOutputData outputData) {
        final DisplayOptionsState displayOptionsState = displayOptionsViewModel.getState();
        displayOptionsState.setCheckedLocationList(outputData.getCheckedLocationList());
        displayOptionsViewModel.setState(displayOptionsState);
        displayOptionsViewModel.firePropertyChanged();

        viewManagerModel.setState(displayOptionsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        Map<AttractionData, Boolean> checkedLocationList = displayOptionsState.getCheckedLocationList();
        for (AttractionData location : checkedLocationList.keySet()) {
            String name = location.getName();
            List<String> categories = location.getCategories();
            Integer price = location.getPrice();
            Double rating = location.getRating();
            String photoUrl = location.getPhotoUrl();
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();
            String address = location.getAddress();
            String locality = location.getLocality();
            String postcode = location.getPostcode();
            String region = location.getRegion();
            String country = location.getCountry();
            System.out.println("Location Info:");
            System.out.println("Name: " + name);
            System.out.println("Categories: " + categories);
            System.out.println("Price: " + price);
            System.out.println("Rating: " + rating);
            System.out.println("PhotoUrl: " + photoUrl);
            System.out.println("Latitude: " + latitude);
            System.out.println("Longitude: " + longitude);
            System.out.println("Address: " + address);
            System.out.println("Locality: " + locality);
            System.out.println("Postcode: " + postcode);
            System.out.println("Region: " + region);
            System.out.println("Country: " + country);
            System.out.println();
        }
        System.out.println(checkedLocationList.size());

    }

    @Override
    public void prepareFailView(String errorMessage) {
        final ChooseOptionsState chooseOptionsState = chooseOptionsViewModel.getState();
        chooseOptionsState.setErrorText(errorMessage);
        chooseOptionsViewModel.firePropertyChanged();
    }

    @Override
    public void switchToPreviousView() {
        viewManagerModel.setState(splashScreenViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

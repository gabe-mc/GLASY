package use_case.set_user_info;

import entity.AttractionData;
import entity.CommonLocationData;

/**
 * Input Boundary for setting a user's information.
 */
public interface SetUserInfoInputBoundary {

    void setUserTimeAvailable(double timeAvailable);

    void setUserLocation(CommonLocationData location);

    void setUserPreferredAttraction(String attraction);
}

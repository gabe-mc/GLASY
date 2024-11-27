package entity.user;

import entity.CommonLocationData;
import entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserEntityTest {

    @Test
    void SuccessTest() {
        User testUser = new User();

        testUser.setTimeAvailable(1200);
        Assertions.assertEquals(Double.valueOf(1200), testUser.getTimeAvailable());

        CommonLocationData commonLocationData = new CommonLocationData();
        commonLocationData.setAddress("197 Yonge Street");
        Assertions.assertEquals(commonLocationData.getAddress(), "197 Yonge Street");

        testUser.setLocation(commonLocationData);
        testUser.setAttractionCategory("Pool");
        Assertions.assertEquals(testUser.getAttractionCategory(), "Pool");

    }
}

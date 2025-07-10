package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.DateAndTime;

public class PassengerClassTests extends BaseTest {

    @DataProvider(name = "pairwiseData")
    public Object[][] providePairwiseCombinations() {
        return new Object[][] {
                {"1", "Economy"},
                {"2", "Business"},
                {"3", "Premium Comfort"},
        };
    }

    @Test(dataProvider = "pairwiseData")
    public void testPassengerAndClassCombinations_TC008(String passengers, String cabinClass) {
        HomePage home = new HomePage(driver);
        home.setOrigin("Dublin");
        home.setDestination("Amsterdam");
        home.selectDate(DateAndTime.fromString("25-08-2025"));
        home.setPassengers(passengers);
        home.setTravelClass(cabinClass);
        home.clickSearch();
        Assert.assertTrue(driver.getCurrentUrl().contains("search"), "Search with passengers + class failed.");
    }
}

package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;

public class PassengerClassTests extends BaseTest {

    @DataProvider(name = "passengerClassCombinations")
    public Object[][] providePassengerClassCombinations() {
        return new Object[][]{
                {1, 0, 0, "Economy", "TC008_OneAdult_Economy"},
                {2, 0, 0, "Business", "TC008_TwoAdults_Business"},
                {2, 1, 0, "Premium Comfort", "TC008_Family_PremiumComfort"},
                {1, 0, 1, "Economy", "TC008_AdultWithInfant"},
                {2, 2, 1, "Business", "TC008_LargeFamily_Business"}
        };
    }

    @Test(dataProvider = "passengerClassCombinations")
    public void testPassengerAndClassCombinations_TC008(int adults, int children, int infants, String cabinClass, String testCaseName) {
        HomePage home = new HomePage(driver);

        home.setFrom("Dublin (DUB)");
        home.setTo("Amsterdam (AMS)");
        home.setDepartureDate("2025-07-25");

        home.setPassengers(adults, children, infants);
        home.setClass(cabinClass);

        home.clickSearch();

        Assert.assertTrue(driver.getCurrentUrl().contains("search"),
                "Test " + testCaseName + " failed: Search with " + adults + " adults, "
                        + children + " children, " + infants + " infants in " + cabinClass + " class.");
    }
}
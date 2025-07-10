package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.DateAndTime;

public class ButtonStateTests extends BaseTest {

    @Test
    public void testSearchButtonState_TC005() {
        HomePage home = new HomePage(driver);
        Assert.assertFalse(home.isSearchEnabled());
        home.setOrigin("Dublin");
        Assert.assertFalse(home.isSearchEnabled());
        home.setDestination("Amsterdam");
        DateAndTime departureDate = DateAndTime.fromString("25-08-2025");
        home.selectDate(departureDate);
        Assert.assertTrue(home.isSearchEnabled());
    }
}

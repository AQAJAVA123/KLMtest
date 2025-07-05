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
        Assert.assertFalse(home.isSearchEnabled(), "Search button should be disabled initially.");
        home.setFrom("Dublin");
        Assert.assertFalse(home.isSearchEnabled(), "Search button should still be disabled.");
        home.setTo("Amsterdam");
        home.setDepartureDate(DateAndTime.fromString("25-07-2025"));
        Assert.assertTrue(home.isSearchEnabled(), "Search button should be enabled after filling all fields.");
    }
}

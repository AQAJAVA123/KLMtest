package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.DateAndTime;

public class SearchTests extends BaseTest {

    @Test
    public void testSuccessfulFlightSearch_TC001() {
        HomePage home = new HomePage(driver);
        home.setOrigin("Dublin");
        home.setDestination("Amsterdam");
        home.selectDate(DateAndTime.fromString("25-08-2025"));
        home.clickSearch();
        Assert.assertTrue(driver.getCurrentUrl().contains("search"), "Search result page not loaded.");
    }

    @Test
    public void testEmptyFromField_TC002() {
        HomePage home = new HomePage(driver);
        home.setOrigin("Amsterdam");
        home.selectDate(DateAndTime.fromString("25-07-2025"));
        home.clickSearch();
        Assert.assertTrue(home.isErrorDisplayed(), "Error message should be displayed.");
        Assert.assertFalse(home.isSearchEnabled(), "Search button should be disabled.");
    }

    @Test
    public void testOneWaySearch_TC004() {
        HomePage home = new HomePage(driver);
        home.setOrigin("Dublin");
        home.setDestination("Amsterdam");
        home.selectDate(DateAndTime.fromString("25-08-2025"));
        home.enableOneWay();
        home.clickSearch();
        Assert.assertTrue(driver.getCurrentUrl().contains("search"), "One-way search failed.");
    }
}

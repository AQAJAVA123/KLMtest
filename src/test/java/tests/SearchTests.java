package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class SearchTests extends BaseTest {

    @Test
    public void testSuccessfulFlightSearch_TC001() {
        HomePage home = new HomePage(driver);
        home.setFrom("Dublin");
        home.setTo("Amsterdam");
        home.setDepartureDate("25-07-2025");
        home.clickSearch();
        Assert.assertTrue(driver.getCurrentUrl().contains("search"), "Search result page not loaded.");
    }

    @Test
    public void testEmptyFromField_TC002() {
        HomePage home = new HomePage(driver);
        home.setTo("Amsterdam");
        home.setDepartureDate("25-07-2025");
        home.clickSearch();
        Assert.assertTrue(home.isErrorDisplayed() || !home.isSearchEnabled(), "Error message or disabled button expected.");
    }

    @Test
    public void testOneWaySearch_TC004() {
        HomePage home = new HomePage(driver);
        home.setFrom("Dublin");
        home.setTo("Amsterdam");
        home.setDepartureDate("25-07-2025");
        home.toggleOneWay(true);
        home.clickSearch();
        Assert.assertTrue(driver.getCurrentUrl().contains("search"), "One-way search failed.");
    }
}



package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utils.DateAndTime;

public class HomePage extends BasePage {

    @FindBy(xpath = "//input[@data-test='bws-station-list__input' and @data-test-value='origin']")
    private WebElement originInput;

    @FindBy(xpath = "//input[@data-test='bws-station-list__input' and @data-test-value='destination']")
    private WebElement destinationInput;

    @FindBy(id = "date-picker-outbound-date")
    private WebElement departureDateInput;

    @FindBy(xpath = "//select[@formcontrolname='tripKind']")
    private WebElement tripTypeSelector;

    @FindBy(xpath = "//button[.//span[normalize-space()='Search flights']]")
    private WebElement searchButton;

    @FindBy(css = "#flight-search-error")
    private WebElement errorMessage;

    @FindBy(id = "mat-input-server-app8")
    private WebElement passengersInput;

    @FindBy(id = "mat-input-server-app9")
    private WebElement travelClassInput;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void setOrigin(String from) {
        originInput.clear();
        originInput.sendKeys(from);
    }

    public void setDestination(String to) {
        destinationInput.clear();
        destinationInput.sendKeys(to);
    }

    public void setDepartureDate(DateAndTime date) {
        departureDateInput.clear();
        departureDateInput.sendKeys(date.getFormatted());
        departureDateInput.sendKeys(Keys.ENTER);
    }

    public void selectTripType(String type) {
        Select tripType = new Select(tripTypeSelector);
        tripType.selectByValue(type);
    }

    public void setPassengers(String value) {
        passengersInput.clear();
        passengersInput.sendKeys(value);
    }

    public void setTravelClass(String value) {
        travelClassInput.clear();
        travelClassInput.sendKeys(value);
    }

    public void clickSearch() {
        searchButton.click();
    }

    public boolean isSearchEnabled() {
        return searchButton.isEnabled();
    }

    public boolean isErrorDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}

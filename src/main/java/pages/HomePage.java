package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DateAndTime;
import java.util.List;


import java.time.Duration;

public class HomePage extends BasePage {

    @FindBy(xpath = "//input[@data-test='bws-station-list__input' and @data-test-value='origin']")
    private WebElement originInput;

    @FindBy(xpath = "//input[@data-test='bws-station-list__input' and @data-test-value='destination']")
    private WebElement destinationInput;

    @FindBy(xpath = "//span[@bwcstartdate]")
    private WebElement calendarOpener;

    @FindBy(xpath = "//select[@formcontrolname='tripKind']")
    private WebElement tripTypeSelector;

    @FindBy(xpath = "//button[.//span[normalize-space()='Search flights']]")
    private WebElement searchButton;

    @FindBy(css = "#flight-search-error")
    private WebElement errorMessage;

    @FindBy(xpath = "//input[@data-test='bwsfe-widget__passenger-manager-input']")
    private WebElement passengersInput;

    @FindBy(xpath = "//select[@data-test='bwsfe-widget__cabin-class-select']")
    private WebElement travelClassSelect;

    @FindBy(xpath = "//button[normalize-space()='Confirm dates']")
    private WebElement confirmDatesButton;

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

    public void openCalendar() {
        calendarOpener.click();
    }

    public void selectDate(DateAndTime date) {
        openCalendar();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'DEPARTURE DATE')]")));
        String month = date.getMonthName();
        int day = date.getDay();
        String xpath = String.format(
                "//div[contains(@class,'calendar')]//div[normalize-space()='%s']/ancestor::div[1]/following-sibling::div//div[normalize-space()='%d']",
                month, day
        );
        WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        dayElement.click();
        confirmDatesButton.click();
    }

    public void selectTripType(String type) {
        new Select(tripTypeSelector).selectByValue(type);
    }

    public void setPassengers(String value) {
        passengersInput.click();
        WebElement passengerOption = driver.findElement(By.xpath("//span[contains(@class,'mat-mdc-option-text') and normalize-space()='" + value + "']"));
        passengerOption.click();
    }

    public void setTravelClass(String value) {
        new Select(travelClassSelect).selectByVisibleText(value);
    }

    public void clickSearch() {
        searchButton.click();
    }

    public boolean isSearchEnabled() {
        return searchButton.isEnabled();
    }

    public boolean isErrorDisplayed() {
        return !driver.findElements(By.cssSelector("#flight-search-error")).isEmpty()
                && driver.findElement(By.cssSelector("#flight-search-error")).isDisplayed();
    }

    public void enableOneWay() {
        new Select(tripTypeSelector).selectByValue("oneway");
    }


}

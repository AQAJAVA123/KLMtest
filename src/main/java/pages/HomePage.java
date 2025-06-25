package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    @FindBy(id = "mat-input-server-app5")
    WebElement fromInput;

    @FindBy(id = "mat-input-server-app6")
    WebElement toInput;

    @FindBy(id = "date-picker-outbound-date")
    WebElement departureDateInput;

    @FindBy(id = "mat-input-server-app0")
    WebElement oneWayCheckbox;

    @FindBy(xpath = "//button[.//span[normalize-space()='Search flights']]")
    WebElement searchButton;

    @FindBy(css = "#flight-search-error")
    WebElement errorMessage;

    @FindBy(id = "mat-input-server-app8")
    WebElement passengersInput;

    @FindBy(id = "mat-input-server-app9")
    WebElement classInput;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setFrom(String from) {
        fromInput.clear();
        fromInput.sendKeys(from);
    }

    public void setTo(String to) {
        toInput.clear();
        toInput.sendKeys(to);
    }

    public void setDepartureDate(String date) {
        departureDateInput.clear();
        departureDateInput.sendKeys(date);
        departureDateInput.sendKeys(Keys.ENTER);
    }

    public void toggleOneWay(boolean on) {
        if (oneWayCheckbox.isSelected() != on) {
            oneWayCheckbox.click();
        }
    }

    public void setPassengers(String value) {
        passengersInput.sendKeys(value);
    }

    public void setClass(String value) {
        classInput.sendKeys(value);
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
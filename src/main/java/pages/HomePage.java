package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;


    @FindBy(xpath = "//input[@aria-label='Flight origin input']")
    private WebElement fromInput;

    @FindBy(xpath = "//input[@aria-label='Flight destination input']")
    private WebElement toInput;

    @FindBy(xpath = "//button[@aria-label='Open datepicker for outbound date']")
    private WebElement departureDateInput;

    @FindBy(xpath = "//label[contains(@class, 'one-way-switch')]//input[@type='checkbox']")
    private WebElement oneWayCheckbox;

    @FindBy(xpath = "//button[@aria-label='Search flights']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[contains(@class, 'error-message') and contains(text(), 'Please fill out this field')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//button[contains(@aria-label, 'Passengers')]")
    private WebElement passengersInput;

    @FindBy(xpath = "//button[contains(@aria-label, 'Cabin class')]")
    private WebElement classInput;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void setFrom(String from) {
        wait.until(ExpectedConditions.elementToBeClickable(fromInput)).clear();
        fromInput.sendKeys(from);
        selectAirportFromDropdown(from);
    }

    public void setTo(String to) {
        wait.until(ExpectedConditions.elementToBeClickable(toInput)).clear();
        toInput.sendKeys(to);
        selectAirportFromDropdown(to);
    }

    private void selectAirportFromDropdown(String airportName) {
        String dropdownItemXpath = String.format("//li[contains(@class, 'airport')]//span[contains(text(), '%s')]", airportName);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownItemXpath))).click();
    }

    public void setDepartureDate(String date) {
        wait.until(ExpectedConditions.elementToBeClickable(departureDateInput)).click();

        String dateXpath = String.format("//td[@data-date='%s']", date);
        WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dateXpath)));
        dateElement.click();
    }

    public void toggleOneWay(boolean enable) {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(oneWayCheckbox));
        if (checkbox.isSelected() != enable) {
            checkbox.click();
        }
    }

    public void setPassengers(int adults, int children, int infants) {
        wait.until(ExpectedConditions.elementToBeClickable(passengersInput)).click();

        setPassengerCount("Adults", adults);

        if (children > 0) {
            setPassengerCount("Children", children);
        }


        if (infants > 0) {
            setPassengerCount("Infants", infants);
        }

        WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@aria-label, 'Confirm passengers selection')]")));
        confirmButton.click();
    }

    private void setPassengerCount(String passengerType, int count) {
        WebElement minusButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format("//button[@aria-label='Decrease %s count']", passengerType))));

        WebElement plusButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format("//button[@aria-label='Increase %s count']", passengerType))));

        WebElement countElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format("//span[@aria-label='%s count']", passengerType))));


        while (Integer.parseInt(countElement.getText()) > 1) {
            minusButton.click();
        }

        while (Integer.parseInt(countElement.getText()) < count) {
            plusButton.click();
        }
    }

    public void setClass(String cabinClass) {
        wait.until(ExpectedConditions.elementToBeClickable(classInput)).click();

        String classXpath = String.format("//li[contains(@class, 'cabin-class')]//span[contains(text(), '%s')]", cabinClass);
        WebElement classOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(classXpath)));
        classOption.click();
    }

    public void clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public boolean isSearchEnabled() {
        return wait.until(ExpectedConditions.elementToBeClickable(searchButton)).isEnabled();
    }

    public boolean isErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(errorMessage)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void waitForPageToLoad() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'loading-indicator')]")));
    }
}
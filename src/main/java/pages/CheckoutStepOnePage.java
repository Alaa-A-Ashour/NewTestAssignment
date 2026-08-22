package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage {
    private WebDriver driver;

    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By postalCodeInput = By.id("postal-code");
    private By continueButton = By.id("continue");

    public CheckoutStepOnePage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillInformation(String fName, String lName, String zip) {
        driver.findElement(firstNameInput).sendKeys(fName);
        driver.findElement(lastNameInput).sendKeys(lName);
        driver.findElement(postalCodeInput).sendKeys(zip);
        driver.findElement(continueButton).click();
    }
}
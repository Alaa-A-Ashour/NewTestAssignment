package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private WebDriver driver;

    // Locators for Step One (Information)
    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By postalCodeInput = By.id("postal-code");
    private By continueButton = By.id("continue");

    // Locators for Step Two (Overview)
    private By subtotalLabel = By.className("summary_subtotal_label");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterCheckoutInformation(String firstName, String lastName, String postalCode) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(postalCodeInput).sendKeys(postalCode);
        driver.findElement(continueButton).click();
    }

    public double getItemTotal() {
        // Text format is usually "Item total: $55.97"
        String rawText = driver.findElement(subtotalLabel).getText();
        String priceText = rawText.replace("Item total: $", "").trim();
        return Double.parseDouble(priceText);
    }
}
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage {
    private WebDriver driver;

    private By itemTotalLabel = By.className("summary_subtotal_label");

    public CheckoutStepTwoPage(WebDriver driver) {
        this.driver = driver;
    }

    public double getItemTotal() {
        String text = driver.findElement(itemTotalLabel).getText(); // e.g., "Item total: $55.97"
        String priceText = text.replace("Item total: $", "").trim();
        return Double.parseDouble(priceText);
    }
}
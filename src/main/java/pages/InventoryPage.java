package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private WebDriver driver;

    private By shoppingCartIcon = By.className("shopping_cart_link");
    private By inventoryItems = By.className("inventory_item");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isCartIconDisplayed() {
        return driver.findElement(shoppingCartIcon).isDisplayed();
    }

    public int getProductCount() {
        return driver.findElements(inventoryItems).size();
    }
}
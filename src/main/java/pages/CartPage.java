package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver driver;

    // Locators
    private By cartItems = By.className("cart_item");
    private By itemName = By.className("inventory_item_name");
    private By checkoutButton = By.id("checkout");
    private By removeButton = By.xpath("//button[text()='Remove']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    public List<String> getCartItemNames() {
        List<WebElement> elements = driver.findElements(itemName);
        List<String> names = new ArrayList<>();
        for (WebElement element : elements) {
            names.add(element.getText());
        }
        return names;
    }

    public void removeProductByName(String productName) {
        String xpath = "//div[text()='" + productName + "']/ancestor::div[@class='cart_item']//button[text()='Remove']";
        driver.findElement(By.xpath(xpath)).click();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public boolean isCartEmpty() {
        return driver.findElements(By.className("cart_item")).isEmpty();
    }
    public void removeProduct(String productName) {
        String xpath = "//div[text()='" + productName + "']/ancestor::div[@class='cart_item']//button";
        driver.findElement(By.xpath(xpath)).click();
    }
    public void clickContinueShopping() {
        driver.findElement(By.id("continue-shopping")).click();
    }

}
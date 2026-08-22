package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {

    private WebDriver driver;
    private By shoppingCartIcon = By.className("shopping_cart_link");
    private By inventoryItems = By.className("inventory_item");

    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");

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


  //Part 2 Locators
    private By linkedinLink = By.xpath("//a[text()='LinkedIn']");
    private By facebookLink = By.xpath("//a[text()='Facebook']");
    private By twitterLink = By.xpath("//a[text()='Twitter']");
    private By cartIcon = By.className("shopping_cart_link");

    public void clickSocialLink(String platform) {
        if (platform.equalsIgnoreCase("linkedin")) driver.findElement(linkedinLink).click();
        else if (platform.equalsIgnoreCase("facebook")) driver.findElement(facebookLink).click();
        else if (platform.equalsIgnoreCase("twitter")) driver.findElement(twitterLink).click();
    }

    public void openCart() {
        driver.findElement(cartIcon).click();
    }

    public String getProductButtonText(String productName) {
        String xpath = "//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button";
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public double getProductPrice(String productName) {
        String xpath = "//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']";
        String priceText = driver.findElement(By.xpath(xpath)).getText().replace("$", "");
        return Double.parseDouble(priceText);
    }

    public String getSocialLinkHref(String SocialLink) {
        if (SocialLink.equalsIgnoreCase("linkedin")) {
            return driver.findElement(By.linkText("LinkedIn")).getAttribute("href");
        } else if (SocialLink.equalsIgnoreCase("facebook")) {
            return driver.findElement(By.linkText("Facebook")).getAttribute("href");
        } else {
            return driver.findElement(By.linkText("Twitter")).getAttribute("href");
        }
    }

    public void addProductToCart(String productName) {
        String xpath = "//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button";
        driver.findElement(By.xpath(xpath)).click();
    }
    public boolean isButtonDisplayingRemoveText(String productName) {
        String xpath = "//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button";
        return driver.findElement(By.xpath(xpath)).getText().equalsIgnoreCase("Remove");
    }
}
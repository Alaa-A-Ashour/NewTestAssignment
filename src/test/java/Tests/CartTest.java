package Tests;

import Base.BaseTests;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import pages.CheckoutStepOnePage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CartTest extends BaseTests {
    private List<String> expectedProducts = new ArrayList<>();
    private InventoryPage inventoryPage;

    @Test
    public void test1_VerifySocialLinks() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.getSocialLinkHref("linkedin").contains("linkedin"));
        //System.out.println("Linkedin Link: " + inventoryPage.getSocialLinkHref("linkedin"));
        Assert.assertTrue(inventoryPage.getSocialLinkHref("facebook").contains("facebook"));
       // System.out.println("Facebook Link: " + inventoryPage.getSocialLinkHref("facebook"));
        Assert.assertTrue(inventoryPage.getSocialLinkHref("twitter").contains("x.com"));
        //System.out.println("Twitter or X Link: " + inventoryPage.getSocialLinkHref("twitter"));

    }

    @Test
    public void test2_VerifyCartIsEmpty() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.openCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartEmpty());
    }

    @Test
    public void test3_Add3SpecificProductsDataDriven() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        for (String product : expectedProducts) {
            inventoryPage.addProductToCart(product);
        }
        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);
        List<String> actualProducts = cartPage.getCartItemNames();
        Assert.assertEquals(actualProducts, expectedProducts);
    }

    @Test
    public void test4_RemoveOneProduct() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        for (String product : expectedProducts) {
            inventoryPage.addProductToCart(product);
        }
        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.removeProduct("Sauce Labs Bolt T-Shirt");
        cartPage.clickContinueShopping();

        Assert.assertFalse(inventoryPage.isButtonDisplayingRemoveText("Sauce Labs Bolt T-Shirt"));
        Assert.assertTrue(inventoryPage.isButtonDisplayingRemoveText("Sauce Labs Backpack"));
    }

    @Test
    public void test5_VerifyCartTotalPrice() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        double calculatedTotal = 0.0;

        for (String product : expectedProducts) {
            calculatedTotal += inventoryPage.getProductPrice(product);
            inventoryPage.addProductToCart(product);
        }

        inventoryPage.openCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutStepOnePage stepOne = new CheckoutStepOnePage(driver);
        stepOne.fillInformation("John", "Doe", "12345");

        CheckoutStepTwoPage stepTwo = new CheckoutStepTwoPage(driver);
        double actualSubtotal = stepTwo.getItemTotal();

        Assert.assertEquals(actualSubtotal, calculatedTotal, 0.01);
    }

    @Test
    public void test6_CheckoutWithEmptyCart() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.openCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"));
    }

    @Test
    public void test7_CartStateAfterLogoutLogin() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart(expectedProducts.get(0));
        inventoryPage.addProductToCart(expectedProducts.get(1));

        // Logout
        driver.findElement(org.openqa.selenium.By.id("react-burger-menu-btn")).click();
        try { Thread.sleep(500); } catch (Exception ignored) {}
        driver.findElement(org.openqa.selenium.By.id("logout_sidebar_link")).click();

        // Relogin
        driver.findElement(org.openqa.selenium.By.id("user-name")).sendKeys("standard_user");
        driver.findElement(org.openqa.selenium.By.id("password")).sendKeys("secret_sauce");
        driver.findElement(org.openqa.selenium.By.id("login-button")).click();

        inventoryPage.openCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getCartItemNames().size(), 2);
    }

}
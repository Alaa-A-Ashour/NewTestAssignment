package Tests;

import Base.BaseTests;
import com.fasterxml.jackson.databind.JsonNode;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.DataDriven;

public class InventoryTest extends BaseTests {

    private JsonNode testData;

    @BeforeClass
    public void loadTestData() {
        testData = DataDriven.jsonReader("testData.json");
    }

    @Test
    public void testInventoryPageElementsAfterLogin() {
        LoginPage loginPage = new LoginPage(driver);

        String username = testData.get("validUser").get("username").asText();
        String password = testData.get("validUser").get("password").asText();

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        InventoryPage inventoryPage = new InventoryPage(driver);

        Assert.assertEquals(inventoryPage.getPageTitle(), "Swag Labs", "Page title does not match!");
        Assert.assertTrue(inventoryPage.isCartIconDisplayed(), "Cart icon is not displayed!");
        Assert.assertEquals(inventoryPage.getProductCount(), 6, "Product count on inventory page is not 6!");
    }
}
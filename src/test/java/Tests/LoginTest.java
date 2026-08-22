package Tests;

import Base.BaseTests;
import com.fasterxml.jackson.databind.JsonNode;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DataDriven;

public class LoginTest extends BaseTests {

    private JsonNode testData;

    @BeforeClass
    public void loadTestData() {
        testData = DataDriven.jsonReader("testData.json");
    }

    @Test(priority = 1)
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);

        String username = testData.get("validUser").get("username").asText();
        String password = testData.get("validUser").get("password").asText();
        String Current_URL = driver.getCurrentUrl();

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        Assert.assertTrue(Current_URL.contains("/inventory.html"),
                "URL does not contain '/inventory.html'");
        System.out.println(Current_URL);
    }

    @Test(priority = 2)
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);

        String username = testData.get("invalidUser").get("username").asText();
        String password = testData.get("invalidUser").get("password").asText();

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        String actualErrorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(actualErrorMessage.contains("Username and password do not match"),
                "Error message mismatch. Found: " + actualErrorMessage);
        System.out.println(actualErrorMessage);
    }

    @Test(priority = 3)
    public void testLoginWithoutPassword() {
        LoginPage loginPage = new LoginPage(driver);

        String username = testData.get("emptyPasswordUser").get("username").asText();
        String password = testData.get("emptyPasswordUser").get("password").asText();

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        String actualErrorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(actualErrorMessage.contains("Password is required"),
                "Error message mismatch. Found: " + actualErrorMessage);
        System.out.println(actualErrorMessage);
    }
}
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private By LoginUsername = By.id("user-name");
    private By LoginPassword = By.id("password");
    private By LoginButton = By.id("login-button");
    private By ErrorMessage = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(LoginUsername).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(LoginPassword).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(LoginButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(ErrorMessage).getText();
    }
}
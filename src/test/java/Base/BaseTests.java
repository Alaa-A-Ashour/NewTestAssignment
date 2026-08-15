package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTests {
  public  WebDriver driver;

    public BaseTests() {
        this.driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }
}

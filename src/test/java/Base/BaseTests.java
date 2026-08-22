package Base;
import java.io.InputStream;
import java.io.FileInputStream;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BaseTests {
  protected WebDriver driver;
    protected LoginPage loginPage;
    protected List<String> expectedProducts = new ArrayList<>();

    @BeforeClass
    public void loadTestData() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("testData.json");

        if (inputStream == null) {
            throw new RuntimeException("CRITICAL ERROR: 'testData.json' was not found! Make sure the file exists inside 'src/test/resources/' folder.");
        }

        JsonNode rootNode = mapper.readTree(inputStream);
        JsonNode productsNode = rootNode.get("cartProducts");
        if (productsNode != null && productsNode.isArray()) {
            for (JsonNode node : productsNode) {
                expectedProducts.add(node.asText());
            }
        }
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(org.openqa.selenium.By.id("user-name")).sendKeys("standard_user");
        driver.findElement(org.openqa.selenium.By.id("password")).sendKeys("secret_sauce");
        driver.findElement(org.openqa.selenium.By.id("login-button")).click();
    }


    public BaseTests() {
        this.driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}

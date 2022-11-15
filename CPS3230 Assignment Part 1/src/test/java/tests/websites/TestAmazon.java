package tests.websites;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TestAmazon {

    WebDriver driver;

    //setup
    @BeforeEach
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Nicole\\OneDrive\\Desktop\\UOM 3RD YEAR\\SEMESTER 1\\CPS3230 FUNDAMENTALS OF SOFTWARE TESTING\\Assignments\\CPS3230 Assignment Part 1\\webtesting\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //go to google and disable cookies dialogue
        driver.manage().deleteAllCookies();
        driver.get("https://www.amazon.co.uk/");


    }

    //teardown
    @AfterEach
    public void teardown(){
        driver.quit();
    }

    //tests for REST API Request
    @Test
    public void testGetRequest() {
        HttpResponse<JsonNode> jsonResponse
                = Unirest.get("https://www.amazon.co.uk/")
                .asJson();

        assertEquals(200, jsonResponse.getStatus());
    }

    //testing the search for earbuds
    @Test
    public void testSimpleSearch() {
        //exercise
        driver.findElement(By.id("sp-cc-accept")).click();
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("earbuds");
        driver.findElement(By.id("nav-search-submit-button")).submit();

        //verify - that the result stats component exists
        List<WebElement> results = driver.findElements(By.xpath("//span[@class='a-color-state a-text-bold']"));
        Assertions.assertEquals(1,results.size());
    }

}

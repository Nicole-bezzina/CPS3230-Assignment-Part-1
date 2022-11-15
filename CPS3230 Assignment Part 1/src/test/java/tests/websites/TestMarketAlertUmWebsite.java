package tests.websites;

import cps3230.assignment.Alert.Alert;
import cps3230.assignment.Product.ProductDetails;
import io.cucumber.java.en.Then;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TestMarketAlertUmWebsite {
    WebDriver driver;
    ProductDetails productDetails;

    public TestMarketAlertUmWebsite() {
    }

    //setup
    @BeforeEach
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Nicole\\OneDrive\\Desktop\\UOM 3RD YEAR\\SEMESTER 1\\CPS3230 FUNDAMENTALS OF SOFTWARE TESTING\\Assignments\\CPS3230 Assignment Part 1\\webtesting\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //go to google and disable cookies dialogue
        driver.manage().deleteAllCookies();
        driver.get("https://www.marketalertum.com");

        //setting a product in productDetails
        this.productDetails = new ProductDetails("Wireless Earbuds, Wireless Headphones Bluetooth 5.0 Headphones 3D HiFi Stereo Headphones Noise Cancellation in-Ear Built-in Mic with Charging Case, IPX7 Waterproof for Work, Travel (White)","2199", "https://m.media-amazon.com/images/I/51CkPxblkRL._AC_SX679_.jpg","【Universal Compatibility】 - Wireless Earbuds bluetooth Compatible with most Bluetooth music players, including for iphone all system, Samsung, Huawei, Android smartphones, PC and Mac.");

    }

    //teardown
    @AfterEach
    public void teardown(){
        driver.quit();
    }

    //tests for setters and getters
    @Test
    public void testSettersGettersTitle() {
        this.productDetails.setProductTitle("Wireless Earbuds, Wireless Headphones Bluetooth 5.0 Headphones 3D HiFi Stereo Headphones Noise Cancellation in-Ear Built-in Mic with Charging Case, IPX7 Waterproof for Work, Travel (White) ");
        Assertions.assertEquals("Wireless Earbuds, Wireless Headphones Bluetooth 5.0 Headphones 3D HiFi Stereo Headphones Noise Cancellation in-Ear Built-in Mic with Charging Case, IPX7 Waterproof for Work, Travel (White) ", this.productDetails.getProductTitle());
    }

    @Test
    public void testSettersGettersPrice() {
        this.productDetails.setPrice("2199");
        Assertions.assertEquals("2199", this.productDetails.getPrice());
    }

    @Test
    public void testSettersGettersImg() {
        this.productDetails.setImg("https://m.media-amazon.com/images/I/51CkPxblkRL._AC_SX679_.jpg");
        Assertions.assertEquals("https://m.media-amazon.com/images/I/51CkPxblkRL._AC_SX679_.jpg", this.productDetails.getImg());
    }

    @Test
    public void testSettersGettersDetails() {
        this.productDetails.setProdDetails("【Universal Compatibility】 - Wireless Earbuds bluetooth Compatible with most Bluetooth music players, including for iphone all system, Samsung, Huawei, Android smartphones, PC and Mac.");
        Assertions.assertEquals("【Universal Compatibility】 - Wireless Earbuds bluetooth Compatible with most Bluetooth music players, including for iphone all system, Samsung, Huawei, Android smartphones, PC and Mac.", this.productDetails.getProdDetails());
    }

    //tests for REST API Requests
    @Test
    public void testGetRequest() {
        HttpResponse<JsonNode> jsonResponse
                = Unirest.get("https://www.marketalertum.com")
                .asJson();

        assertEquals(200, jsonResponse.getStatus());
    }

    @Test
    public void testPostRequest() {
        HttpResponse<JsonNode> jsonResponse
                = Unirest.post("https://api.marketalertum.com/Alert")
                .header("Content-Type", "application/json")
                .body("{\"alertType\":6," +
                        "\"heading\":\"Wireless Earbuds, Wireless Headphones Bluetooth 5.0 Headphones 3D HiFi Stereo Headphones Noise Cancellation in-Ear Built-in Mic with Charging Case, IPX7 Waterproof for Work, Travel (White) \"," +
                        "\"description\":\"【Universal Compatibility】 - Wireless Earbuds bluetooth Compatible with most Bluetooth music players, including for iphone all system, Samsung, Huawei, Android smartphones, PC and Mac.\"," +
                        "\"url\":\"https://www.amazon.co.uk/Wireless-Earbuds-Headphones-Cancellation-Waterproof/dp/B0B8JX92YJ/ref=sr_1_5?crid=DYDAKDXDYA7Z&keywords=earbuds&qid=1668023258&sprefix=%2Caps%2C136&sr=8-5\"," +
                        "\"imageUrl\":\"https://m.media-amazon.com/images/I/51CkPxblkRL._AC_SX679_.jpg\"," +
                        "\"postedBy\":\"1f9437aa-4071-4d95-9163-3373c4670ab7\",\"priceInCents\":2199}")
                .asJson();

        System.out.println(jsonResponse.getStatusText());

        assertEquals(201, jsonResponse.getStatus());
    }

    @Test
    public void testDeleteRequest() {
        HttpResponse<JsonNode> jsonResponse
                = Unirest.delete("https://api.marketalertum.com/Alert?userId=1f9437aa-4071-4d95-9163-3373c4670ab7")
                .header("Content-Type", "application/json")
                .asJson();
    }

}

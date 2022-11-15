package cucumbertests.marketalertwebsite;

import cps3230.assignment.Alert.Alert;
import cps3230.assignment.Alert.Electronics;
import cps3230.assignment.utils.AlertTypeProvider;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kong.unirest.Unirest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MarketAlertStepdefs {
    static WebDriver driver;
    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Nicole\\OneDrive\\Desktop\\UOM 3RD YEAR\\SEMESTER 1\\CPS3230 FUNDAMENTALS OF SOFTWARE TESTING\\Assignments\\CPS3230 Assignment Part 1\\webtesting\\chromedriver.exe");
        driver = new ChromeDriver();

    }

    @AfterEach
    public void teardown() {
        driver.quit();
        driver.close();
    }

    @Given("I am a user of marketalertum")
    public void iAmAUserOfMarketalertum(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Nicole\\OneDrive\\Desktop\\UOM 3RD YEAR\\SEMESTER 1\\CPS3230 FUNDAMENTALS OF SOFTWARE TESTING\\Assignments\\CPS3230 Assignment Part 1\\webtesting\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.marketalertum.com");
    }

    @When("I login using valid credentials")
    public void iLoginUsingValidCredentials() {
        //String userId ="1f9437aa-4071-4d95-9163-3373c4670ab7";
        driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
        driver.findElement(By.xpath("//input[@id = 'UserId']")).sendKeys("1f9437aa-4071-4d95-9163-3373c4670ab7");
        driver.findElement(By.xpath("//input[@type = 'submit']")).submit();
    }

    @Then("I should see my alerts")
    public void iShouldSeeMyAlerts() {
        //Exercise
        boolean isPresent = driver.findElements(By.xpath("//table[@border = '1']")).size() > 0;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@border = '1']")));

        //Verify
        Assertions.assertTrue(isPresent);

    }

    @When("I login using invalid credentials")
    public void iLoginUsingInvalidCredentials() {
        driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
        driver.findElement(By.xpath("//input[@id = 'UserId']")).sendKeys("1234");
        driver.findElement(By.xpath("//input[@type = 'submit']")).submit();
    }

    @Then("I should see the login screen again")
    public void iShouldSeeTheLoginScreenAgain() {
        boolean isPresent = driver.findElements(By.xpath("//form[@action='/Alerts/LoginForm']")).size() > 0;

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@action='/Alerts/LoginForm']")));

        //Verify
        Assertions.assertTrue(isPresent);

    }

    @Given("I am an administrator of the website and I upload {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAlerts(int alerts) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Nicole\\OneDrive\\Desktop\\UOM 3RD YEAR\\SEMESTER 1\\CPS3230 FUNDAMENTALS OF SOFTWARE TESTING\\Assignments\\CPS3230 Assignment Part 1\\webtesting\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.marketalertum.com");

        driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
        driver.findElement(By.xpath("//input[@id = 'UserId']")).sendKeys("1f9437aa-4071-4d95-9163-3373c4670ab7");
        driver.findElement(By.xpath("//input[@type = 'submit']")).submit();

        alerts = 3;
        for (int i = 0; i<alerts;i++) {
            Unirest.post("https://api.marketalertum.com/Alert")
                    .header("Content-Type", "application/json")
                    .body("{\"alertType\":6," +
                            "\"heading\":\"Wireless Earbuds, Wireless Headphones Bluetooth 5.0 Headphones 3D HiFi Stereo Headphones Noise Cancellation in-Ear Built-in Mic with Charging Case, IPX7 Waterproof for Work, Travel (White) \"," +
                            "\"description\":\"【Universal Compatibility】 - Wireless Earbuds bluetooth Compatible with most Bluetooth music players, including for iphone all system, Samsung, Huawei, Android smartphones, PC and Mac.\"," +
                            "\"url\":\"https://www.amazon.co.uk/Wireless-Earbuds-Headphones-Cancellation-Waterproof/dp/B0B8JX92YJ/ref=sr_1_5?crid=DYDAKDXDYA7Z&keywords=earbuds&qid=1668023258&sprefix=%2Caps%2C136&sr=8-5\"," +
                            "\"imageUrl\":\"https://m.media-amazon.com/images/I/51CkPxblkRL._AC_SX679_.jpg\"," +
                            "\"postedBy\":\"1f9437aa-4071-4d95-9163-3373c4670ab7\",\"priceInCents\":2199}")
                    .asJson();
        }

    }


    @Given("I am an administrator of the website and I upload more than {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadMoreThanAlerts(int alerts) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Nicole\\OneDrive\\Desktop\\UOM 3RD YEAR\\SEMESTER 1\\CPS3230 FUNDAMENTALS OF SOFTWARE TESTING\\Assignments\\CPS3230 Assignment Part 1\\webtesting\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.marketalertum.com");

        driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
        driver.findElement(By.xpath("//input[@id = 'UserId']")).sendKeys("1f9437aa-4071-4d95-9163-3373c4670ab7");
        driver.findElement(By.xpath("//input[@type = 'submit']")).submit();

        alerts = 5;
        for (int i = 0; i<alerts+1;i++) {
            Unirest.post("https://api.marketalertum.com/Alert")
                    .header("Content-Type", "application/json")
                    .body("{\"alertType\":6," +
                            "\"heading\":\"Wireless Earbuds, Wireless Headphones Bluetooth 5.0 Headphones 3D HiFi Stereo Headphones Noise Cancellation in-Ear Built-in Mic with Charging Case, IPX7 Waterproof for Work, Travel (White) \"," +
                            "\"description\":\"【Universal Compatibility】 - Wireless Earbuds bluetooth Compatible with most Bluetooth music players, including for iphone all system, Samsung, Huawei, Android smartphones, PC and Mac.\"," +
                            "\"url\":\"https://www.amazon.co.uk/Wireless-Earbuds-Headphones-Cancellation-Waterproof/dp/B0B8JX92YJ/ref=sr_1_5?crid=DYDAKDXDYA7Z&keywords=earbuds&qid=1668023258&sprefix=%2Caps%2C136&sr=8-5\"," +
                            "\"imageUrl\":\"https://m.media-amazon.com/images/I/51CkPxblkRL._AC_SX679_.jpg\"," +
                            "\"postedBy\":\"1f9437aa-4071-4d95-9163-3373c4670ab7\",\"priceInCents\":2199}")
                    .asJson();
        }
    }
    @Given("I am a user of marketalertum that is logged in")
    public void iAmAUserOfMarketalertumThatIsLoggedIn() {
        //Exercise
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Nicole\\OneDrive\\Desktop\\UOM 3RD YEAR\\SEMESTER 1\\CPS3230 FUNDAMENTALS OF SOFTWARE TESTING\\Assignments\\CPS3230 Assignment Part 1\\webtesting\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.marketalertum.com");

        driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
        driver.findElement(By.xpath("//input[@id = 'UserId']")).sendKeys("1f9437aa-4071-4d95-9163-3373c4670ab7");
        driver.findElement(By.xpath("//input[@type = 'submit']")).submit();

    }
    @When("I view a list of alerts")
    public void iViewAListOfAlerts() {

        boolean isPresent = driver.findElements(By.xpath("//table[@border = '1']")).size() > 0;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@border = '1']")));

        //Verify
        Assertions.assertTrue(isPresent);
    }

    @Then("each alert should contain an icon")
    public void eachAlertShouldContainAnIcon() {
        //Exercise
        boolean isPresent = driver.findElements(By.xpath("//h4//img[contains(@src,'icon')]")).size() > 1;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4//img[contains(@src,'icon')]")));

        //Verify
        Assertions.assertTrue(isPresent);

    }

    @And("each alert should contain a heading")
    public void eachAlertShouldContainAHeading() {
        //Exercise
        boolean isPresent = driver.findElements(By.xpath("//h4[text()]")).size() > 1;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()]")));

        //Verify
        Assertions.assertTrue(isPresent);
    }

    @And("each alert should contain a description")
    public void eachAlertShouldContainADescription() {
        //Exercise
        boolean isPresent = driver.findElements(By.xpath("//tr//td[text()]")).size() > 1;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr//td[text()]")));

        //Verify
        Assertions.assertTrue(isPresent);
    }

    @And("each alert should contain an image")
    public void eachAlertShouldContainAnImage() {
        //Exercise
        boolean isPresent = driver.findElements(By.xpath("//tr//td//img[contains(@src,'amazon')]")).size() > 1;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr//td//img[contains(@src,'amazon')]")));

        //Verify
        Assertions.assertTrue(isPresent);
    }

    @And("each alert should contain a price")
    public void eachAlertShouldContainAPrice() {
        //Exercise
        boolean isPresent = driver.findElements(By.xpath("//tr//td//b[contains(text(),'Price: ')]")).size() > 1;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr//td//b[contains(text(),'Price: ')]")));

        //Verify
        Assertions.assertTrue(isPresent);
    }

    @And("each alert should contain a link to the original product website")
    public void eachAlertShouldContainALinkToTheOriginalProductWebsite() {
        //Exercise
        boolean isPresent = driver.findElements(By.xpath("//tr//td//a[contains(text(),'Visit item')]")).size() > 1;
        //driver.findElement(By.linkText("Visit item")).getAttribute("href");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr//td//a[contains(text(),'Visit item')]")));

        //Verify
        Assertions.assertTrue(isPresent);
    }

    @Then("I should see {int} alerts")
    public void iShouldSeeAlerts(int alerts) {
        List <WebElement> alertBoxes = driver.findElements(By.xpath("//table[@border = '1']"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@border = '1']")));

        //Verify
        Assertions.assertEquals(alerts,alertBoxes.size());

    }

    @Given("I am an administrator of the website and I upload an alert of type <alert-type>")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfTypeAlertType(int alertType) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Nicole\\OneDrive\\Desktop\\UOM 3RD YEAR\\SEMESTER 1\\CPS3230 FUNDAMENTALS OF SOFTWARE TESTING\\Assignments\\CPS3230 Assignment Part 1\\webtesting\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.marketalertum.com");

        driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
        driver.findElement(By.xpath("//input[@id = 'UserId']")).sendKeys("1f9437aa-4071-4d95-9163-3373c4670ab7");
        driver.findElement(By.xpath("//input[@type = 'submit']")).submit();

        //upload an alert of type <alert-type>

        //specifying alert type 1 - car etc.
        //alertTypeProvider.car --> 1;
        //alertTypeProvider.getAlertSection(1) --> car;
    }

    @And("the icon displayed should be <icon file name>")
    public void theIconDisplayedShouldBeIconFileName() {
        //whatever the alert type number it matches with icon file
        //car --> car img
        //alertTypeProvider.getAlertSection(1) --> car img
    }

}

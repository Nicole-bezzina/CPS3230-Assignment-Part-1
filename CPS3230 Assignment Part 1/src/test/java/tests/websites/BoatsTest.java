package tests.websites;

import cps3230.assignment.Alert.AlertTypes;
import cps3230.assignment.Alert.Boats;
import cps3230.assignment.utils.AlertTypeProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import test.stubs.MarketInterfaceIsFavourable;
import test.stubs.MarketInterfaceIsNotFavourable;

import java.util.concurrent.TimeUnit;

public class BoatsTest {
    final int maxAlerts = 5;
    static WebDriver driver;
    Boats boats;

    @BeforeEach
    public void setup() {
        boats = new Boats();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Nicole\\OneDrive\\Desktop\\UOM 3RD YEAR\\SEMESTER 1\\CPS3230 FUNDAMENTALS OF SOFTWARE TESTING\\Assignments\\CPS3230 Assignment Part 1\\webtesting\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //go to google and disable cookies dialogue
        driver.manage().deleteAllCookies();
        driver.get("https://www.amazon.co.uk/");
        driver.findElement(By.id("sp-cc-accept")).click();
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("earbuds");
        driver.findElement(By.id("nav-search-submit-button")).submit();

    }
    //teardown
    @AfterEach
    public void teardown(){
        driver.quit();
        //driver.close();
    }

    //Test for the maximum number of tourists
    @Test
    public void testMaxAlertsOf5() {
        //Exercise + Verify
        Assertions.assertEquals(maxAlerts, boats.getMaxAlerts());
    }

    @Test
    public void testBoatsAlertType() throws Exception{
        Assertions.assertEquals(AlertTypes.BoatType,boats.getAlertType() );
    }
    @Test
    public void testAlertTypeIsBoats() throws Exception{
        //Setup
        AlertTypeProvider alertTypeProvider = Mockito.mock(AlertTypeProvider.class);
        Mockito.when(alertTypeProvider.getAlertSection()).thenReturn(AlertTypeProvider.BOATS);

        Boats boats = new Boats();
        boats.setAlertTypeProvider(alertTypeProvider);

        //Exercise
        boolean result = boats.addAlert();
        //Verify
        Assertions.assertTrue(result);

    }


    @Test
    public void testWhenMarketInterfaceIsFavourable() throws Exception{
        //Setup
        boats.setMarketService(new MarketInterfaceIsFavourable());

        boolean result = boats.addAlert();

        //Verify
        Assertions.assertTrue(result);
    }

    @Test
    public void testWhenMarketInterfaceIsNotFavourable() throws Exception{
        //Setup
        boats.setMarketService(new MarketInterfaceIsNotFavourable());

        boolean result = boats.addAlert();

        //Verify
        Assertions.assertFalse(result);
    }


}

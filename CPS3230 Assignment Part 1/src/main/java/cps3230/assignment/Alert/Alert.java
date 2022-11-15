package cps3230.assignment.Alert;

import cps3230.assignment.Product.ProductDetails;
import cps3230.assignment.market.MarketInterface;
import cps3230.assignment.utils.AlertTypeProvider;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class Alert {
    protected int maxAlerts;
    static WebDriver driver;
    //Creating an ProductDetails object class to store in product details
    static ProductDetails details = new ProductDetails();
    //Creating a JSONObject object to store the scraped values
    static JSONObject jsonObject = new JSONObject();
    public static int alertCounter = 0;
    public Alert(){

        maxAlerts = 5;
    }
    public static MarketInterface marketService;

    //Setter injection
    public void setMarketService(MarketInterface marketService){
        this.marketService = marketService;
    }

    //Mock object
    public static AlertTypeProvider alertTypeProvider;
    //Inject the interface with a setter method
    public void setAlertTypeProvider(AlertTypeProvider alertTypeProvider){
        this.alertTypeProvider = alertTypeProvider;
    }

    public abstract int getMaxAlerts();

    public abstract AlertTypes getAlertType();
    public static boolean addAlert() throws IOException {
        Alert.setup();

        //Check space in market to add alerts to market
        if (marketService != null && !marketService.areLatestResultsDisplayed()) {
            return false;
        }

        //every item's link in the results section to click for more details
        List<WebElement> link = driver.findElements(By.xpath("(//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal'])[position() < 6]"));

        //Creating a FileWriter file to write the scraped values in the json to the file
        FileWriter file = new FileWriter("C:\\Users\\Nicole\\OneDrive\\Desktop\\UOM 3RD YEAR\\SEMESTER 1\\CPS3230 FUNDAMENTALS OF SOFTWARE TESTING\\Assignments\\CPS3230 Assignment Part 1 - Copy\\product_details.json");

        for (int i = 0; i < link.size(); i++) {

            int alertType = AlertTypeProvider.ELECTRONIC;
            //to get alert type - type 6 since it is an electronic type of search
            jsonObject.put("alertType", alertType);
            if (alertType == AlertTypeProvider.ELECTRONIC) {
                //clicking on every item's link
                link = driver.findElements(By.xpath("(//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal'])[position() < 6]"));
                WebElement element = link.get(i);
                element.click();
                System.out.println("\nProduct " + (i + 1) + ": \n");

                //to get product title
                Alert.productTitle();

                //to get product details (about this item)
                Alert.productDetails();

                //to get current url
                String strUrl = driver.getCurrentUrl();
                jsonObject.put("url", strUrl);

                //to get product image
                Alert.productImg();

                //to get user id
                jsonObject.put("postedBy", "1f9437aa-4071-4d95-9163-3373c4670ab7");

                //to get product price - assuming in euro instead of pounds
                Alert.priceType();

                //writing the json object to the file
                file.write(jsonObject.toString());
                //going back to the previous web page i.e. the results page of the notebook search
                driver.navigate().back();
                //displaying the json object after each product scraping
                System.out.println("JSON file created: " + jsonObject);
                //post request to store product details in market alert website after each product scraping
                Unirest.post("https://api.marketalertum.com/Alert")
                        .header("Content-Type", "application/json")
                        .body(jsonObject.toString())
                        .asJson();
                alertCounter++;
            } else {
                System.out.println("Alert type does not match");
                return false;
            }
        }
        return  true;
    }
    public static void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Nicole\\OneDrive\\Desktop\\UOM 3RD YEAR\\SEMESTER 1\\CPS3230 FUNDAMENTALS OF SOFTWARE TESTING\\Assignments\\CPS3230 Assignment Part 1\\webtesting\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //to disable or accept cookies
        driver.manage().deleteAllCookies();
        driver.get("https://www.amazon.co.uk/");
        driver.findElement(By.id("sp-cc-accept")).click();
        //searching for notebook on amazon
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("earbuds");
        driver.findElement(By.id("nav-search-submit-button")).submit();
    }
    public static void productTitle(){
        List<WebElement> prodTitle = driver.findElements(By.xpath("//span[@id='productTitle']"));
        for(int j =0;j<prodTitle.size();j++) {
            //System.out.println("Product Title: " + prodTitle.get(j).getText());
            details.setProductTitle(prodTitle.get(j).getText());
            jsonObject.put("heading", prodTitle.get(j).getText());
        }
    }

    public static void productDetails(){
        List<WebElement> prodDetails = driver.findElements(By.xpath("//ul[(@class='a-unordered-list a-vertical a-spacing-mini')]/li"));
        //System.out.print("Product Details:");
        for (WebElement prod_details: prodDetails){
           // System.out.println(prod_details.getText());
            details.setProdDetails(prod_details.getText());
            jsonObject.put("description", prod_details.getText());
        }
    }
    public static void productImg() {
        List<WebElement> prodImg = driver.findElements(By.xpath("//*[@id=\"landingImage\"]"));
        for (WebElement img : prodImg) {
            //System.out.println("Product Image: " + img.getAttribute("src"));
            details.setImg(img.getAttribute("src"));
            jsonObject.put("imageUrl", img.getAttribute("src"));
        }
    }
    public static void priceType(){
        List<WebElement> price1 = driver.findElements(By.xpath("//span[@id = 'sns-base-price']"));
        List<WebElement> price2 = driver.findElements(By.xpath("//span[@class = 'a-price aok-align-center reinventPricePriceToPayMargin priceToPay']"));
        List<WebElement> price3 = driver.findElements(By.xpath("//span[@id = 'a-size-base a-color-price a-color-price']"));
        //MARIAH - CHANGE TO SWITCH CASE
        //if price type one:
        if(!price1.isEmpty()){
            for (WebElement price: price1){
                String p = price.getText().replace("£","").replace(".","").replace("\n","");
               // System.out.println("Product Price: " + p + "c");
                details.setPrice(price.getText());
                jsonObject.put("priceInCents", Integer.parseInt(p));
            }
        }
        //if price type two:
        else if (!price2.isEmpty()){
            for (WebElement price: price2){
                String p = price.getText().replace("£","").replace(".","").replace("\n","");
                //System.out.println("Product Price: " + p + "c");
                details.setPrice(price.getText());
                jsonObject.put("priceInCents", Integer.parseInt(p));
            }
        }
        //if price type three:
        else if (!price3.isEmpty()){
            for (WebElement price: price3){
                String p = price.getText().replace("£","").replace(".","").replace("\n","");
                //System.out.println("Product Price: " + p + "c");
                details.setPrice(price.getText());
                jsonObject.put("priceInCents", Integer.parseInt(p));
            }
        }
        //if price another type:
        else {
            //System.out.println("No Price Value found");
        }


    }
}

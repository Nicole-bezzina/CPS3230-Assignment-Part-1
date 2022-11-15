package cps3230.assignment.Alert;

import cps3230.assignment.Product.ProductDetails;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
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

import static cps3230.assignment.Alert.Alert.addAlert;
import static cps3230.assignment.Alert.Alert.alertCounter;

public class Main {
    public static void main(String args[]) throws IOException {
        if (alertCounter==0) {
            Alert.addAlert();
        }
        else {
            Unirest.delete("https://api.marketalertum.com/Alert?userId=1f9437aa-4071-4d95-9163-3373c4670ab7")
                    .header("Content-Type", "application/json")
                    .asJson();

            Alert.addAlert();
        }
    }
}
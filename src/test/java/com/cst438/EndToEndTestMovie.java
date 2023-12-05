package com.cst438;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EndToEndTestMovie {

    public static final String CHROME_DRIVER_FILE_LOCATION = "/Users/aaronmartinez/Downloads/chromedriver-mac-arm64/chromedriver";
    public static final String URL = "http://localhost:3000";
    public static final int SLEEP_DURATION = 1000; // 1 second.
    WebDriver driver;

    @Test
    public void addScheduleTest() throws Exception {

        System.setProperty(
                "webdriver.chrome.driver",
                CHROME_DRIVER_FILE_LOCATION);
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(ops);

        driver.get(URL + "/addMovie");
        Thread.sleep(SLEEP_DURATION);

        try{
            WebElement message = driver.findElement(By.id("gmessage"));
            WebElement submit = driver.findElement(By.id("submit"));
            WebElement movieTitle = driver.findElement(By.name("movieTitle"));
            WebElement movieRating = driver.findElement(By.name("movieRating"));
            WebElement movieLength = driver.findElement(By.name("movieLength"));
            WebElement priceId = driver.findElement(By.name("priceId"));

            movieTitle.sendKeys("The Fast and the Furious: Tokyo Drift");
            movieRating.sendKeys("PG-13");
            movieLength.sendKeys("103");
            priceId.sendKeys("1");
            Thread.sleep(SLEEP_DURATION);

            submit.click();
            Thread.sleep(SLEEP_DURATION);

            assertEquals("Movie Added ", message.getText());

            driver.get(URL + "/Movies");
            Thread.sleep(SLEEP_DURATION);

            boolean foundName = false;
            boolean foundRating = false;
            boolean foundLength = false;

            List<WebElement> elements2 = driver.findElements(By.xpath("//td"));
            for (WebElement we : elements2) {
                if (we.getText().equals("The Fast and the Furious: Tokyo Drift")) {
                    foundName = true;
                }

                if(we.getText().equals("PG-13")){
                    foundRating = true;
                }

                if(we.getText().equals("103")){
                    foundLength = true;
                }
            }

            assertThat(foundName).withFailMessage("The Movie Name was not found.").isTrue();
            assertThat(foundRating).withFailMessage("The Movie Rating was not found.").isTrue();
            assertThat(foundLength).withFailMessage("The Movie Length was not found.").isTrue();

        } catch (Exception ex) {
            throw ex;
        } finally {
            driver.quit();
        }
    }

    @Test
    public void deleteMovieTest() throws Exception {

        System.setProperty(
                "webdriver.chrome.driver",
                CHROME_DRIVER_FILE_LOCATION);
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(ops);

        driver.get(URL + "/Movies");
        Thread.sleep(SLEEP_DURATION);

        try{
            List<WebElement> elements = driver.findElements(By.xpath("//td"));
            Thread.sleep(SLEEP_DURATION);

            boolean foundName = false;
            boolean foundRating = false;
            boolean foundLength = false;

            for (WebElement we : elements) {
                if (we.getText().equals("Star Wars: A New Hope")) {
                    foundName = true;
                }

                if(we.getText().equals("PG")){
                    foundRating = true;
                }

                if(we.getText().equals("121")){
                    foundLength = true;
                }
            }

            assertThat(foundName).withFailMessage("The Movie Name was not found.").isTrue();
            assertThat(foundRating).withFailMessage("The Movie Rating was not found.").isTrue();
            assertThat(foundLength).withFailMessage("The Movie Length was not found.").isTrue();

            driver.get(URL + "/deleteMovie/13");
            Thread.sleep(SLEEP_DURATION);

            WebElement message = driver.findElement(By.id("gmessage"));
            WebElement Dbutton = driver.findElement(By.id("Delete"));

            Dbutton.click();
            Thread.sleep(SLEEP_DURATION);

            assertEquals("Movie Deleted. ", message.getText());

            driver.get(URL + "/Movies");
            Thread.sleep(SLEEP_DURATION);

            List<WebElement> elements2 = driver.findElements(By.xpath("//td"));
            foundName = false;
            foundRating = false;
            foundLength = false;

            for (WebElement we : elements2) {
                if (!(we.getText().equals("Star Wars: A New Hope"))) {
                    foundName = true;
                }

                if(!(we.getText().equals("PG"))){
                    foundRating = true;
                }

                if(!(we.getText().equals("121"))){
                    foundLength = true;
                    break;
                }
            }

            assertThat(foundName).withFailMessage("The Movie Name was not found.").isTrue();
            assertThat(foundRating).withFailMessage("The Movie Rating was not found.").isTrue();
            assertThat(foundLength).withFailMessage("The Movie Length was not found.").isTrue();

        } catch (Exception ex) {
            throw ex;
        } finally {
            driver.quit();
        }
    }

}

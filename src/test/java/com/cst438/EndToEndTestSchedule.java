package com.cst438;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class EndToEndTestSchedule {

    public static final String CHROME_DRIVER_FILE_LOCATION = "/Users/aaronmartinez/Downloads/chromedriver-mac-arm64/chromedriver";
    public static final String URL = "http://localhost:3000";
    public static final int SLEEP_DURATION = 1000; // 1 second.
    WebDriver driver;

    @Test
    public void editAssignmentTest() throws Exception {

        System.setProperty(
                "webdriver.chrome.driver",
                CHROME_DRIVER_FILE_LOCATION);
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(ops);

        driver.get(URL + "/editSchedule/1");
        Thread.sleep(SLEEP_DURATION);

        try{
            WebElement movieTitle = driver.findElement(By.name("title"));
            WebElement movieDate= driver.findElement(By.name("date"));
            WebElement movieSTime = driver.findElement(By.name("Stime"));
            WebElement movieETime = driver.findElement(By.name("Etime"));
            WebElement submit = driver.findElement(By.id("submit"));
            WebElement message = driver.findElement(By.id("gmessage"));

            movieTitle.clear();
            movieTitle.sendKeys("Cars");
            movieDate.clear();
            movieDate.sendKeys("2023-11-30");
            movieSTime.clear();
            movieSTime.sendKeys("2:00 PM");
            movieETime.clear();
            movieETime.sendKeys("4:00 PM");

            Thread.sleep(SLEEP_DURATION);

            submit.click();
            Thread.sleep(SLEEP_DURATION);

            assertEquals("Schedule saved. ", message.getText());

            driver.get(URL);
            Thread.sleep(SLEEP_DURATION);

            List<WebElement> elements = driver.findElements(By.xpath("//td"));
            Thread.sleep(SLEEP_DURATION);

            boolean foundName = false;
            boolean foundDate = false;
            boolean foundStime = false;
            boolean foundEtime = false;

            for (WebElement we : elements) {
                if (we.getText().equals("Cars")) {
                    foundName=true;
                }

                if(we.getText().equals("2023-11-30")){
                    foundDate = true;
                }

                if(we.getText().equals("2:00 PM")){
                    foundStime = true;
                }

                if(we.getText().equals("4:00 PM")){
                    foundEtime = true;
                    break;
                }
            }

            assertThat(foundName).withFailMessage("The test Movie Name was not found.").isTrue();
            assertThat(foundDate).withFailMessage("The test Movie Date was not found.").isTrue();
            assertThat(foundStime).withFailMessage("The test Schedule Start time was not found.").isTrue();
            assertThat(foundEtime).withFailMessage("The test assignment End time was not found.").isTrue();
        } catch (Exception ex) {
            throw ex;
        } finally {
            driver.quit();
        }
    }

    @Test
    public void addScheduleTest() throws Exception {

        System.setProperty(
                "webdriver.chrome.driver",
                CHROME_DRIVER_FILE_LOCATION);
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(ops);

        driver.get(URL + "/addSchedule");
        Thread.sleep(SLEEP_DURATION);

        try{
            WebElement message = driver.findElement(By.id("gmessage"));
            WebElement submit = driver.findElement(By.id("submit"));
            WebElement movieTitle = driver.findElement(By.name("movieTitle"));
            WebElement date = driver.findElement(By.name("date"));
            WebElement Stime = driver.findElement(By.name("start_time"));
            WebElement Etime = driver.findElement(By.name("end_time"));
            WebElement room = driver.findElement(By.name("roomId"));

            movieTitle.sendKeys("Harry Potter and the Goblet of Fire");
            date.sendKeys("2023-12-01");
            Stime.sendKeys("10:00 PM");
            Etime.sendKeys("12:00 AM");
            room.sendKeys("1");
            Thread.sleep(SLEEP_DURATION);

            submit.click();
            Thread.sleep(SLEEP_DURATION);

            assertEquals("Schedule Added ", message.getText());

            driver.get(URL);
            Thread.sleep(SLEEP_DURATION);

            boolean foundName = false;
            boolean foundDate = false;
            boolean foundStime = false;
            boolean foundEtime = false;
            boolean foundRoom = false;
            List<WebElement> elements2 = driver.findElements(By.xpath("//td"));
            for (WebElement we : elements2) {
                if (we.getText().equals("Harry Potter and the Goblet of Fire")) {
                    foundName = true;
                }

                if(we.getText().equals("2023-12-01")){
                    foundDate = true;
                }

                if(we.getText().equals("10:00 PM")){
                    foundStime = true;
                }

                if(we.getText().equals("12:00 AM")){
                    foundEtime = true;
                }

                if(we.getText().equals("50")){
                    foundRoom = true;
                }
            }

            assertThat(foundName).withFailMessage("The test Schedule Movie Name was not found.").isTrue();
            assertThat(foundDate).withFailMessage("The test Schedule Date was not found.").isTrue();
            assertThat(foundStime).withFailMessage("The test Start time was not found.").isTrue();
            assertThat(foundEtime).withFailMessage("The test End time was not found.").isTrue();
            assertThat(foundRoom).withFailMessage("The test room was not found.").isTrue();

        } catch (Exception ex) {
            throw ex;
        } finally {
            driver.quit();
        }
    }

    @Test
    public void deleteScheduleTest() throws Exception {

        System.setProperty(
                "webdriver.chrome.driver",
                CHROME_DRIVER_FILE_LOCATION);
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(ops);

        driver.get(URL);
        Thread.sleep(SLEEP_DURATION);

        try{
            List<WebElement> elements = driver.findElements(By.xpath("//td"));
            Thread.sleep(SLEEP_DURATION);

            boolean foundName = false;
            boolean foundDate = false;
            boolean foundStime = false;
            boolean foundEtime = false;

            for (WebElement we : elements) {
                if (we.getText().equals("Barbie")) {
                    foundName = true;
                }

                if(we.getText().equals("2023-11-15")){
                    foundDate = true;
                }

                if(we.getText().equals("3:00 PM")){
                    foundStime = true;
                }

                if(we.getText().equals("5:30 PM")){
                    foundEtime = true;
                }

            }

            assertThat(foundName).withFailMessage("The test Schedule Movie Name was not found.").isTrue();
            assertThat(foundDate).withFailMessage("The test Schedule Date was not found.").isTrue();
            assertThat(foundStime).withFailMessage("The test Start time was not found.").isTrue();
            assertThat(foundEtime).withFailMessage("The test End time was not found.").isTrue();

            driver.get(URL + "/deleteSchedule/2");
            Thread.sleep(SLEEP_DURATION);

            WebElement message = driver.findElement(By.id("gmessage"));
            WebElement Dbutton = driver.findElement(By.id("Delete"));

            Dbutton.click();
            Thread.sleep(SLEEP_DURATION);

            assertEquals("Schedule Deleted. ", message.getText());

            driver.get(URL);
            Thread.sleep(SLEEP_DURATION);

            List<WebElement> elements2 = driver.findElements(By.xpath("//td"));
            foundName = false;
            foundDate = false;
            foundStime = false;
            foundEtime = false;

            for (WebElement we : elements2) {
                if (!(we.getText().equals("Barbie"))) {
                    foundName = true;
                }

                if(!(we.getText().equals("2023-11-15"))){
                    foundDate = true;
                }

                if(!(we.getText().equals("3:00 PM"))){
                    foundStime = true;
                }

                if(!(we.getText().equals("5:30 PM"))){
                    foundEtime = true;
                    break;
                }

            }

            assertThat(foundName).withFailMessage("The test Schedule Movie Name was not found.").isTrue();
            assertThat(foundDate).withFailMessage("The test Schedule Date was not found.").isTrue();
            assertThat(foundStime).withFailMessage("The test Start time was not found.").isTrue();
            assertThat(foundEtime).withFailMessage("The test End time was not found.").isTrue();

        } catch (Exception ex) {
            throw ex;
        } finally {
            driver.quit();
        }
    }
}

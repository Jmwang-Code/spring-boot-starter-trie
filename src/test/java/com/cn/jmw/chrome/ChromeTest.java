package com.cn.jmw.chrome;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

/**
 * @author jmw
 * @Description TODO
 * @date 2023Äê02ÔÂ09ÈÕ 16:52
 * @Version 1.0
 */
public class ChromeTest {

    public WebDriver webDriver;

    @Test
    public void one() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("no-sandbox");
        options.addArguments("disable-gpu");
        options.addArguments("disable-features=NetworkService");
        options.addArguments("ignore-certificate-errors");
        options.addArguments("silent-launch");
        options.addArguments("disable-application-cache");
        options.addArguments("disable-web-security");
        options.addArguments("no-proxy-server");
        options.addArguments("disable-dev-shm-usage");
        options.addArguments("window-size=2048,1536");

        Path path = Paths.get("src","drivers","chromedriver.exe");
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, path.toAbsolutePath().toString());
        webDriver = new ChromeDriver(options);
        webDriver.get("http://192.168.102.70:8765/");

//        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
//
//        ExpectedCondition<WebElement> ConditionOfSign = ExpectedConditions.presenceOfElementLocated(By.id("headlessBrowserRenderSign"));
//        ExpectedCondition<WebElement> ConditionOfWidth = ExpectedConditions.presenceOfElementLocated(By.id("width"));
//        ExpectedCondition<WebElement> ConditionOfHeight = ExpectedConditions.presenceOfElementLocated(By.id("height"));
//        wait.until(ExpectedConditions.and(ConditionOfSign, ConditionOfWidth, ConditionOfHeight));
//
//        Double contentWidth = Double.parseDouble(webDriver.findElement(By.id("width")).getAttribute("value"));
//
//        Double contentHeight = Double.parseDouble(webDriver.findElement(By.id("height")).getAttribute("value"));
//
//        if (500>0 && 500 != contentWidth) {
//            // scale the window
//            webDriver.manage().window().setSize(new Dimension(500, contentHeight.intValue()));
//        }
//        Thread.sleep(1500);
//        // scale the window again
//        contentWidth = Double.parseDouble(webDriver.findElement(By.id("width")).getAttribute("value"));
//        contentWidth = contentWidth>0 ? contentWidth : 1920;
//        contentHeight = Double.parseDouble(webDriver.findElement(By.id("height")).getAttribute("value"));
//        contentHeight = contentHeight>0 ? contentHeight : 600;
//        webDriver.manage().window().setSize(new Dimension(contentWidth.intValue(), contentHeight.intValue()));
//        Thread.sleep(1000);
//        TakesScreenshot screenshot = (TakesScreenshot) webDriver;
//        File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);

    }
}

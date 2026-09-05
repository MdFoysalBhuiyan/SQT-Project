package com.srms;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InvalidLoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://localhost/student-result-management-system/admin-login.php");
    }

    @Test
    public void invalidLoginTest() {

        driver.findElement(By.name("username")).sendKeys("wronguser");
        driver.findElement(By.name("password")).sendKeys("wrongpassword");
        driver.findElement(By.name("login")).click();

        // Wait until the JavaScript alert appears
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        // Read the alert
        String alertMessage = driver.switchTo().alert().getText();

        // Verify the message
        Assert.assertEquals(
                alertMessage,
                "Invalid Details",
                "Unexpected alert message"
        );

        // Close alert
        driver.switchTo().alert().accept();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
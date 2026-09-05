package com.srms;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://localhost/student-result-management-system/admin-login.php");

        // Login first
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("Admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void logoutTest() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Find Logout link
        WebElement logoutLink = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("a[href='logout.php']")
                )
        );

        // Click using JavaScript because the toast can cover the link
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", logoutLink);

        // Verify redirect after logout
        wait.until(
                ExpectedConditions.urlContains("index.php")
        );

        Assert.assertTrue(
                driver.getCurrentUrl().contains("index.php"),
                "Logout failed - user was not redirected to index.php"
        );
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
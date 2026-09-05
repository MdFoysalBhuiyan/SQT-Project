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

public class AddResultPageTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(
            "http://localhost/student-result-management-system/admin-login.php"
        );

        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("Admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void openAddResultPageTest() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(5));

        // Click Add Result from the admin navigation
        wait.until(
            ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[href='add-result.php']")
            )
        ).click();

        // Verify page title/content
        wait.until(
            ExpectedConditions.titleContains("Add Result")
        );

        Assert.assertTrue(
            driver.getTitle().contains("Add Result"),
            "Add Result page did not open"
        );
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
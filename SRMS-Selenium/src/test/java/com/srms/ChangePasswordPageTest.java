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

public class ChangePasswordPageTest {

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
    public void openChangePasswordPageTest() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(5));

        // Open Change Password page
        wait.until(
            ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[href='change-password.php']")
            )
        ).click();

        // Verify the page heading
        wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.name("newpassword")
            )
        );

        Assert.assertTrue(
            driver.findElement(By.name("newpassword")).isDisplayed(),
            "Change Password page did not open"
        );
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
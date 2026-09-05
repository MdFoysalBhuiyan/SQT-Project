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

public class CreateSubjectPageTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(
            "http://localhost/student-result-management-system/admin-login.php"
        );

        // Login
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("Admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void openCreateSubjectPageTest() {

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(5)
        );

        // Click Create Subject from the dashboard
        wait.until(
            ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[href='create-subject.php']")
            )
        ).click();

        // Verify the page opened
        wait.until(
            ExpectedConditions.urlContains("create-subject.php")
        );

        Assert.assertTrue(
            driver.getCurrentUrl().contains("create-subject.php"),
            "Create Subject page did not open"
        );
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
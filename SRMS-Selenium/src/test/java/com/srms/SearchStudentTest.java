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

public class SearchStudentTest {

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
    public void searchStudentTest() {

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(5)
        );

        // Click Manage Students from the dashboard
        wait.until(
            ExpectedConditions.elementToBeClickable(
                By.linkText("Manage Students")
            )
        ).click();

        // Verify Manage Students page
        wait.until(
            ExpectedConditions.urlContains("manage-students.php")
        );

        Assert.assertTrue(
            driver.getCurrentUrl().contains("manage-students.php"),
            "Manage Students page did not open"
        );

        // Search for our test student
        driver.findElement(
            By.cssSelector("input[type='search']")
        ).sendKeys("Selenium Test Student");

        // Verify student appears
        Assert.assertTrue(
            driver.getPageSource().contains("Selenium Test Student"),
            "Student was not found"
        );
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
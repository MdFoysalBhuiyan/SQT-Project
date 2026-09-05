package com.srms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DashboardTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://localhost/student-result-management-system/admin-login.php");

        // Login
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("Admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void verifyDashboardTest() {

        Assert.assertTrue(
                driver.getCurrentUrl().contains("dashboard.php"),
                "Dashboard did not open"
        );
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
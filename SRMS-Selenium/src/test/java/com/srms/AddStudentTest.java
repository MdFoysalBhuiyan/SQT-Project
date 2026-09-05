package com.srms;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddStudentTest {

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
    public void addStudentTest() {

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(5)
        );

        // Click Add Students from the dashboard
        wait.until(
            ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[href='add-students.php']")
            )
        ).click();

        // Wait for the Add Student page
        wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.name("fullanme")
            )
        );

        // Fill student information
        driver.findElement(By.name("fullanme"))
                .sendKeys("Selenium Test Student");

        driver.findElement(By.name("rollid"))
                .sendKeys("99998");

        driver.findElement(By.name("emailid"))
                .sendKeys("seleniumtest2@example.com");

        // Select first available class
        Select classDropdown = new Select(
                driver.findElement(By.name("class"))
        );

        classDropdown.selectByIndex(1);

        // Date of birth
        driver.findElement(By.name("dob"))
                .sendKeys("01/15/2000");

        // Click Add
        driver.findElement(By.name("submit")).click();

        // Wait for success message
        wait.until(
            ExpectedConditions.textToBePresentInElementLocated(
                By.cssSelector(".alert-success"),
                "Student info added successfully"
            )
        );

        Assert.assertTrue(
            driver.getPageSource().contains(
                "Student info added successfully"
            ),
            "Student was not added successfully"
        );
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
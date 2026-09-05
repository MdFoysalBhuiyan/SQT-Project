package com.srms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddStudentPageTest {

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
    public void openAddStudentPageTest() {

        driver.get(
            "http://localhost/student-result-management-system/add-students.php"
        );

        // Verify the actual page heading
        String pageText = driver.findElement(
                By.tagName("body")
        ).getText();

        Assert.assertTrue(
                pageText.contains("Student Admission"),
                "Add Student page did not open"
        );
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
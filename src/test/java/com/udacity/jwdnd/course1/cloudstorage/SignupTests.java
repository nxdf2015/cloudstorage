package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.html5.AddApplicationCache;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import utilities.LoginPage;
import utilities.SignupPage;

import javax.swing.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignupTests {
    @LocalServerPort
    private int port;

    @Autowired
    UserService userService;

    private WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
        driver.get("http://localhost:" + this.port + "/");
    }

    /**
     * test that verifies that an unauthorized user can only access the login and signup pages
     */
    @Test
    public void testUnauthorized(){
        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("Login",driver.getTitle());


    }

    // test that signs up a new user, logs in, verifies that the home page is accessible, logs ou
    @Test
    public void testSignUp ()throws Exception{

        driver.get("http://localhost:" + this.port + "/home");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.toSignup();
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("aaaa","aaaa","aaaa","aaaa");

        signupPage = new SignupPage(driver);
        signupPage.toLogin();

        driver.get("http://localhost:" + this.port + "/login");


        loginPage = new LoginPage(driver);
        loginPage.login("aaaa","aaaa");

        Assertions.assertEquals("Home",driver.getTitle());
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }



}

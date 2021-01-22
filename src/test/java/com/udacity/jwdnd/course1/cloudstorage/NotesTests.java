package com.udacity.jwdnd.course1.cloudstorage;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import utilities.HomePage;
import utilities.LoginPage;
import utilities.SignupPage;
import utilities.SuccessPage;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotesTests {

    @LocalServerPort
    private int  port;

    private WebDriver driver;

    public NotesTests() {
    }

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new FirefoxDriver();
        driver.get("http://localhost:" + this.port + "/home");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.toSignup();
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("a","a","a","a");

        signupPage = new SignupPage(driver);
        signupPage.toLogin();

        Assertions.assertEquals("Login",driver.getTitle());

        loginPage = new LoginPage(driver);
        loginPage.login("a","a");
    }

    @Test
    public void addNote(){
        HomePage homePage = new HomePage(driver);
        homePage.addNote("title","a note test");
        SuccessPage successPage = new SuccessPage(driver);
        successPage.success();
        homePage = new HomePage(driver);
//
       Assertions.assertTrue(homePage.contains("title"));

    }


    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }
}

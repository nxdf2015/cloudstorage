package com.udacity.jwdnd.course1.cloudstorage;


import com.udacity.jwdnd.course1.cloudstorage.services.DataService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import utilities.*;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotesTests {

    @LocalServerPort
    private int  port;

    private WebDriver driver;

    @Autowired
    private DataService dataService;



    @BeforeAll
    static void beforeAll() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        dataService.clear();

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


//    test that edits an existing note and verifies that the changes are displayed
    @Test
    public void addNote(){
        HomePage homePage = new HomePage(driver);
        homePage.toNoteTab();
        homePage.addNote("title","a note test");

        SuccessPage successPage = new SuccessPage(driver);
        successPage.success();

        homePage = new HomePage(driver);
        homePage.toNoteTab();

       Assertions.assertTrue(homePage.contains("title"));

    }

//   test that edits an existing note and verifies that the changes are displayed.
    @Test
    public void editNote(){
        HomePage homePage;
        SuccessPage successPage;

        homePage = new HomePage(driver);
        homePage.toNoteTab();

        homePage.addNote("title 1","a note test");

        successPage = new SuccessPage(driver);
        successPage.success();

        homePage = new HomePage(driver);
        homePage.toNoteTab();

        homePage.editNote(0, "new title", "new description 1 ");

        successPage = new SuccessPage(driver);
        successPage.success();


       Assertions.assertTrue(homePage.contains("new title"));

    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }
}

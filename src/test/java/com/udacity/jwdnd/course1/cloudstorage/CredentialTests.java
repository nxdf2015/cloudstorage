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
public class CredentialTests {

    @LocalServerPort
    private int port;

    @Autowired
    DataService dataService;

    private WebDriver driver;

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

    //test that creates a set of credentials, verifies that they are displayed,
    // and verifies that the displayed password is encrypted
    @Test
    public void createCredential(){

        CredentialPage credentialPage;
        SuccessPage successPage;

        credentialPage = new CredentialPage(driver);
        credentialPage.toCredentialPage();
        credentialPage.addCredential("username","descrition","url");

        successPage = new SuccessPage(driver);
        successPage.success();

        credentialPage = new CredentialPage(driver);
        credentialPage.toCredentialPage();
        credentialPage.addCredential("username1","descrition1","url1");

        successPage = new SuccessPage(driver);
        successPage.success();

        credentialPage = new CredentialPage(driver);
        credentialPage.toCredentialPage();
        credentialPage.addCredential("username2","descrition2","url2");

        successPage = new SuccessPage(driver);
        successPage.success();

        credentialPage = new CredentialPage(driver);
        credentialPage.toCredentialPage();

        Assertions.assertEquals(3 , credentialPage.sizeCredentials());
    }

    //  test that views an existing set of credentials, verifies that the viewable password is unencrypted, edits the credentials,
    //  and verifies that the changes are displayed
    @Test
    public void editCredential( ){
        CredentialPage credentialPage;
        SuccessPage successPage;

        credentialPage = new CredentialPage(driver);
        credentialPage.toCredentialPage();
        credentialPage.addCredential("username","descrition","url");

        successPage = new SuccessPage(driver);
        successPage.success();

        credentialPage = new CredentialPage(driver);
        credentialPage.toCredentialPage();
        credentialPage.editCredential(0, "newname","new description","new url");

        successPage = new SuccessPage(driver);
        successPage.success();

        credentialPage = new CredentialPage(driver);
        credentialPage.toCredentialPage();

        Assertions.assertTrue( credentialPage.contains(0,"newname"));

    }

    // test that deletes an existing set of credentials
    // and verifies that the credentials are no longer displayed.
    @Test
    public void deleteCredential(){

        CredentialPage credentialPage;
        SuccessPage successPage;

        credentialPage = new CredentialPage(driver);
        credentialPage.toCredentialPage();
        credentialPage.addCredential("username","descrition","url");

        successPage = new SuccessPage(driver);
        successPage.success();

        credentialPage = new CredentialPage(driver);
        credentialPage.toCredentialPage();

        credentialPage.delete(0);

        successPage = new SuccessPage(driver);
        successPage.success();

        credentialPage = new CredentialPage(driver);
        credentialPage.toCredentialPage();

        Assertions.assertFalse( credentialPage.contains(0,"username"));
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

}

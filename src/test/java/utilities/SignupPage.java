package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SignupPage {

    @FindBy(id = "signup-form")
    private WebElement setupForm;

    @FindBy(id = "link-login")
    private WebElement linkToLogin;



    @Autowired
    public SignupPage(WebDriver driver) {


        PageFactory.initElements(driver, this) ;
    }

    public void enterData(WebElement element , String label, String value){
        element.findElement(By.name(label)).sendKeys(value);
    }
    public void signup(String username, String firstname, String lastname, String password){

        enterData(setupForm, "username" , username);
        enterData(setupForm, "firstname" , firstname);
        enterData(setupForm, "lastname" , lastname);
        enterData(setupForm, "password" , password);

        setupForm.findElement(By.id("submit-form")).click();


    }


    public void toLogin() {
        linkToLogin.click();
    }
}

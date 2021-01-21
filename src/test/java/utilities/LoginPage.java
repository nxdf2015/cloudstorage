package utilities;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "inputUsername")
    WebElement inputUserName;

    @FindBy(id = "inputPassword")
    WebElement inputPassword;

    @FindBy(id = "submit-login")
    WebElement submitButton;

    @FindBy(id = "signup")
    WebElement linkToSignup;


    public LoginPage(WebDriver driver){

        PageFactory.initElements(driver,this);
    }

    private  void enterData(WebElement element , String label, String value){
        element.findElement(By.name(label)).sendKeys(value);
    }

    public void login(String username, String password){
        inputUserName.sendKeys(username);
        inputPassword.sendKeys(password);
        submitButton.click();

    }




    public void toSignup(){
          linkToSignup.click();

    }
}

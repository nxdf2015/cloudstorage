package utilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessPage {

    @FindBy(id = "continue-link")
    WebElement continueLink;

    public SuccessPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void success(){
        continueLink.click();
    }
}

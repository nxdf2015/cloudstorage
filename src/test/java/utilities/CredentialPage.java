package utilities;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CredentialPage {

    @FindBy(id = "logout")
    WebElement logoutButton;

    @FindBy(id="nav-credentials-tab")
    WebElement credentialTabLink;

    @FindBy( id = "add-new-credential")
    WebElement btnAddCredential;

    @FindBy(id = "credential-username")
    WebElement inputusername;

    @FindBy(id = "credential-password")
    WebElement inputpassword;

    @FindBy(id = "credential-url")
    WebElement inputurl;

    @FindBy(id = "credential-save")
    WebElement btnsave;

    @FindBy(className = "credential-item")
    List<WebElement> credentials;



    public CredentialPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void logout(){
        logoutButton.click();
    }


    public void addCredential(String username, String password, String url){
        btnAddCredential.click();
        addData(username,password,url);
    }

    public void addData(String username,String password, String url){
        inputusername.clear();
        inputusername.sendKeys(username);

        inputpassword.clear();
        inputpassword.sendKeys(password);

        inputurl.clear();
        inputurl.sendKeys(url);

        btnsave.click();
    }

    public void editCredential(int i, String newname, String newdescription, String newurl){
        credentials.get(i)
                .findElement(By.cssSelector(".edit-credential"))
                .click();
        addData(newname,newdescription,newurl);

    }

    public void deleteCredential(){

    }

    public int sizeCredentials(){
        return credentials.size();
    }


    public void toCredentialPage() {
        credentialTabLink.click();
    }

    public boolean contains(int i, String newname) {
        try {
            return credentials.get(i).findElement(By.cssSelector(".username-item")).getText().equals(newname);
        }
        catch (Exception e){
            return false;
        }
    }

    public void delete(int i) {
         credentials.get(i).findElement(By.cssSelector(".credential-delete")).click();
    }
}

package utilities;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(id = "logout")
    WebElement logoutButton;

    @FindBy(id = "note-title" )
    WebElement inputNoteTitle;

    @FindBy(id = "note-description")
    WebElement inputNoteDescription;

    @FindBy(id = "note-submit")
    WebElement inputNoteSubmit;

    @FindBy(id ="add-new-note")
    WebElement addNewNote;

    @FindBy(id ="btn-save-note")
    WebElement btnSaveNote;

    @FindBy(id="nav-notes-tab")
    WebElement noteTabLink;

    @FindBy(tagName = "tr")
    WebElement notes;

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void logout(){
        logoutButton.click();
    }

    public void addNote(String title, String description){
        noteTabLink.click();
        addNewNote.click();
        inputNoteTitle.sendKeys(title);
        inputNoteDescription.sendKeys(description);
        btnSaveNote.click();

    }

    public boolean contains(String title){
     return  notes.findElement(By.xpath("//*[text()='title']")) != null;
    }
}

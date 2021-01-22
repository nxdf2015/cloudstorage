package utilities;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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

    @FindBy(className = "btn-edit-note")
    List<WebElement> btnEdit;

    @FindBy(tagName = "tr")
    List<WebElement> notes;


    public HomePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void logout(){
        logoutButton.click();
    }
    public void toNoteTab(){
        noteTabLink.click();
    }

    public void addNote(String title, String description){
        addNewNote.click();
        addData(title,description);

    }

    private void addData(String title, String description) {
        inputNoteTitle.sendKeys(title);
        inputNoteDescription.sendKeys(description);
        btnSaveNote.click();
    }

    public boolean contains(String title){
     return notes.stream().filter(note -> note.findElement(By.xpath("//*[text()='title']")) != null) != null;

    }

    public void editNote(int index,String newtitle, String newdescription){
        btnEdit.get(0).click();
        addData(newtitle,newdescription);
    }


}

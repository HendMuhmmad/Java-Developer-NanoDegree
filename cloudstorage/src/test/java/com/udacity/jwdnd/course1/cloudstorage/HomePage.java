package com.udacity.jwdnd.course1.cloudstorage;


import java.io.File;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;

public class HomePage {

	private WebDriverWait wait;
	@FindBy(id = "logout-button")
	private WebElement logoutButton;

	@FindBy(id = "nav-files-tab")
	private WebElement filesTab;

	@FindBy(id = "nav-notes-tab")
	private WebElement notesTab;

	@FindBy(id = "nav-credentials-tab")
	private WebElement credentialsTab;

	@FindBy(id = "add-note-button")
	private WebElement addNoteButton;

	@FindBy(id = "note-title")
	private WebElement noteTitleModal;

	@FindBy(id = "note-description")
	private WebElement noteDescriptionModal;

	@FindBy(id = "submit-note")
	private WebElement noteSubmit;

	@FindBy(className = "edit-note-button")
	private List<WebElement> editNoteButton;

	@FindBy(className = "delete-note-button")
	private List<WebElement> deleteNoteButton;

	@FindBy(className = "noteTitle")
	private List<WebElement> notesTitle;

	@FindBy(className = "noteDescription")
	private List<WebElement> notesDescription;
	
	
	@FindBy(id = "add-credential-button")
	private WebElement addCredentilButton;

	@FindBy(id = "credential-url")
	private WebElement credentialUrlModal;

	@FindBy(id = "credential-username")
	private WebElement credentialUsernameModal;
	
	@FindBy(id = "credential-password")
	private WebElement credentialPasswordModal;

	@FindBy(id = "submit-credential")
	private WebElement credentialSubmit;

	@FindBy(className = "edit-credential-button")
	private List<WebElement> editCredentialButton;

	@FindBy(className = "delete-credential-button")
	private List<WebElement> deleteCredentialButton;

	@FindBy(className = "credentialUrl")
	private List<WebElement> credentialsUrl;
	
	@FindBy(className = "credentialUsername")
	private List<WebElement> credentialsUsername;

	@FindBy(className = "credentialPassword")
	private List<WebElement> credentialsPassword;
	
	@FindBy(id="fileUpload")
	private WebElement uploadFile;
	
	@FindBy(id="uploadButton")
	private WebElement fileSubmit;
	
	@FindBy(className ="downloadFile")
	private List<WebElement> downloadFile;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10);
	}

	public void logout() {
		this.logoutButton.click();
	}

	public void addNote(String noteTitle, String noteDescription) {
		wait.until(ExpectedConditions.visibilityOf(addNoteButton));
		addNoteButton.click();
		wait.until(ExpectedConditions.visibilityOf(noteTitleModal));
		noteTitleModal.sendKeys(noteTitle);
		noteDescriptionModal.sendKeys(noteDescription);
		noteSubmit.click();
	}

	public Note getNote(int index) {
		wait.until(ExpectedConditions.visibilityOf(notesTitle.get(index)));
		return new Note(null, notesTitle.get(index).getText(), notesDescription.get(index).getText(), null);
	}

	public void editNote(int index, String editTitle, String editDescription) {
		wait.until(ExpectedConditions.visibilityOf(notesTitle.get(index)));
		editNoteButton.get(index).click();
		wait.until(ExpectedConditions.visibilityOf(noteTitleModal));
		noteTitleModal.clear();
		noteTitleModal.sendKeys(editTitle);
		noteDescriptionModal.clear();
		noteDescriptionModal.sendKeys(editDescription);
		noteSubmit.click();
	}

	public void deleteNote(int index) {
		wait.until(ExpectedConditions.visibilityOf(notesTitle.get(index)));
		deleteNoteButton.get(index).click();
	}
	
	
	public void addCredentials(String url, String username,String password) {
		wait.until(ExpectedConditions.visibilityOf(addCredentilButton));
		addCredentilButton.click();
		wait.until(ExpectedConditions.visibilityOf(credentialUrlModal));
		credentialUrlModal.sendKeys(url);
		credentialUsernameModal.sendKeys(username);
		credentialPasswordModal.sendKeys(password);
		credentialSubmit.click();
	}
	
	
	public Credential getCredential(int index) {
		wait.until(ExpectedConditions.visibilityOf(credentialsUrl.get(index)));
		return new Credential(null, credentialsUrl.get(index).getText(), credentialsUsername.get(index).getText(),null,credentialsPassword.get(0).getText(), null);
	}
	
	public void editCredential(int index, String url, String username,String password) {
		wait.until(ExpectedConditions.visibilityOf(credentialsUrl.get(index)));
		editCredentialButton.get(index).click();
		wait.until(ExpectedConditions.visibilityOf(credentialUrlModal));
		credentialUrlModal.clear();
		credentialUrlModal.sendKeys(url);
		credentialUsernameModal.clear();
		credentialUsernameModal.sendKeys(username);
		credentialPasswordModal.clear();
		credentialPasswordModal.sendKeys(password);
		credentialSubmit.click();
	}
	
	public String getEditCredentialPasswordFromModal(int index) {
		wait.until(ExpectedConditions.visibilityOf(credentialsUrl.get(index)));
		editCredentialButton.get(index).click();
		wait.until(ExpectedConditions.visibilityOf(credentialUrlModal));
		return credentialPasswordModal.getAttribute("value");
	}
	
	public void deleteCredential(int index) {
		wait.until(ExpectedConditions.visibilityOf(credentialsUrl.get(index)));
		deleteCredentialButton.get(index).click();
	}


	public void goToNotesTab() {
		notesTab.click();
	}

	public void goToCredentialsTab() {
		credentialsTab.click();
	}
	
	public void uploadFile(String fileName) {
		wait.until(ExpectedConditions.visibilityOf(uploadFile));
		uploadFile.sendKeys(new File(fileName).getAbsolutePath());
		fileSubmit.click();	
	}
	
	public void downloadFile(int index) {
		wait.until(ExpectedConditions.visibilityOf(downloadFile.get(index)));
		downloadFile.get(index).click();
	}
}

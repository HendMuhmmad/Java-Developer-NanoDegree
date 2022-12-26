package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;

import java.io.File;
import java.util.HashMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;
	
	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
		
	}

	@BeforeEach
	public void beforeEach() {
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("download.default_directory", System.getProperty("user.dir")+"\\downloads");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		this.driver = new ChromeDriver(options);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

//	@Test
//	public void getLoginPage() {
//		driver.get("http://localhost:" + this.port + "/login");
//		Assertions.assertEquals("Login", driver.getTitle());
//	}
//
	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doMockSignUp(String firstName, String lastName, String userName, String password){
		// Create a dummy account for logging in later.

		// Visit the sign-up page.
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));
		
		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement inputUsername = driver.findElement(By.id("inputUsername"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();

		/* Check that the sign up was successful. 
		// You may have to modify the element "success-msg" and the sign-up 
		// success message below depening on the rest of your code.
		*/
		Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
	}

	
	
	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doLogIn(String userName, String password)
	{
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/login");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
		loginUserName.click();
		loginUserName.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
		loginPassword.click();
		loginPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Home"));

	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling redirecting users 
	 * back to the login page after a succesful sign up.
	 * Read more about the requirement in the rubric: 
	 * https://review.udacity.com/#!/rubrics/2724/view 
	 */
	@Test
	public void testRedirection() {
		// Create a test account
		doMockSignUp("Redirection","Test","RT","123");
		
		// Check if we have been redirected to the log in page.
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling bad URLs 
	 * gracefully, for example with a custom error page.
	 * 
	 * Read more about custom error pages at: 
	 * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
	 */
	@Test
	public void testBadUrl() {
		// Create a test account
		doMockSignUp("URL","Test","UT","123");
		doLogIn("UT", "123");
		
		// Try to access a random made-up URL.
		driver.get("http://localhost:" + this.port + "/some-random-page");
		Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
	}


	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling uploading large files (>1MB),
	 * gracefully in your code. 
	 * 
	 * Read more about file size limits here: 
	 * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload Limits" section.
	 */
	@Test
	public void testLargeUpload() {
		// Create a test account
		doMockSignUp("Large File","Test","LFT","123");
		doLogIn("LFT", "123");

		// Try to upload an arbitrary large file
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		String fileName = "upload5m.zip";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
		WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
		fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

		WebElement uploadButton = driver.findElement(By.id("uploadButton"));
		uploadButton.click();
		try {
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Large File upload failed");
		}
		Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

	}
	
	@Test
	public void testAccessibleHome() {
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
	}
	
	@Test
	public void testAccessibleHomeWithLogout() {
		doMockSignUp("Test","Test","Test","123");
		doLogIn("Test", "123");
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());
		HomePage homePage = new HomePage(driver);
		homePage.logout();
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());		
	}
	
	@Test
	public void testAddNote()  {
		doMockSignUp("Test","Test","note","123");
		doLogIn("note", "123");
		HomePage homePage = new HomePage(driver);
		homePage.goToNotesTab();
		homePage.addNote("test title", "test description");
		driver.findElement(By.className("continue-link")).click();
		homePage.goToNotesTab();
		Note note = homePage.getNote(0);
		Assertions.assertEquals("test title",note.getNotetitle());
		Assertions.assertEquals("test description",note.getNotedescription());
	}
	
	@Test
	public void testEditNote()   {
		doMockSignUp("Test","Test","noteEdit","123");
		doLogIn("noteEdit", "123");
		HomePage homePage = new HomePage(driver);
		homePage.goToNotesTab();
		homePage.addNote("test title", "test description");
		driver.findElement(By.className("continue-link")).click();
		homePage.goToNotesTab();
		homePage.addNote("test title2", "test description2");
		driver.findElement(By.className("continue-link")).click();
		homePage.goToNotesTab();
		homePage.editNote(0, "test Edit","description edit");
		driver.findElement(By.className("continue-link")).click();
		homePage.goToNotesTab();
	    Note note = homePage.getNote(0);
		Assertions.assertEquals("test Edit",note.getNotetitle());
		Assertions.assertEquals("description edit",note.getNotedescription());
	}
	
	
	@Test
	public void testDeleteNote()   {
		doMockSignUp("Test","Test","noteDelete","123");
		doLogIn("noteDelete", "123");
		HomePage homePage = new HomePage(driver);
		homePage.goToNotesTab();
		homePage.addNote("test title", "test description");
		driver.findElement(By.className("continue-link")).click();
		homePage.goToNotesTab();
		homePage.addNote("test title2", "test description2");
		driver.findElement(By.className("continue-link")).click();
		homePage.goToNotesTab();
		homePage.deleteNote(0);
		driver.findElement(By.className("continue-link")).click();
		homePage.goToNotesTab();
		Note note = homePage.getNote(0);
		Assertions.assertNotEquals("test Edit",note.getNotetitle());
		
	}
	
	@Test
	public void testAddCredential()  {
		doMockSignUp("Test","Test","credential","123");
		doLogIn("credential", "123");
		HomePage homePage = new HomePage(driver);
		homePage.goToCredentialsTab();
		homePage.addCredentials("http://localhost:8080/home", "hend", "123");
		driver.findElement(By.className("continue-link")).click();
		homePage.goToCredentialsTab();
		Credential credential = homePage.getCredential(0);
		Assertions.assertEquals("http://localhost:8080/home",credential.getUrl());
		Assertions.assertEquals("hend",credential.getUsername());
	}
	
	@Test
	// test password is hashed when showed in the lisr
	public void testHashCredentialPassword()  {
		doMockSignUp("Test","Test","credentialhash","123");
		doLogIn("credentialhash", "123");
		HomePage homePage = new HomePage(driver);
		homePage.goToCredentialsTab();
		homePage.addCredentials("http://localhost:8080/home", "hend", "123");
		driver.findElement(By.className("continue-link")).click();
		homePage.goToCredentialsTab();
		Credential credential = homePage.getCredential(0);
		Assertions.assertNotEquals("123",credential.getPassword());
	}
	
	@Test
	public void testBadCredentialUrl()  {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		doMockSignUp("Test","Test","credentialurl","123");
		doLogIn("credentialurl", "123");
		HomePage homePage = new HomePage(driver);
		homePage.goToCredentialsTab();
		homePage.addCredentials("home", "hend", "123");
		try {
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("error")));
		} catch (org.openqa.selenium.TimeoutException e) {
			Assertions.assertEquals("1","2");
		}
		Assertions.assertEquals("Url is invalid. Please try again .",driver.findElement(By.id("error")).getText());
	}
	
	
	@Test
	public void testEditCredential()   {
		doMockSignUp("Test","Test","credentialedit","123");
		doLogIn("credentialedit", "123");
		HomePage homePage = new HomePage(driver);
		homePage.goToCredentialsTab();
		homePage.addCredentials("http://localhost:8080/home", "hend", "123");
		driver.findElement(By.className("continue-link")).click();
		homePage.goToCredentialsTab();
		homePage.addCredentials("http://localhost:8080/login", "rana", "123");
		driver.findElement(By.className("continue-link")).click();
		homePage.goToCredentialsTab();
		homePage.editCredential(0, "http://localhost:8080/chathome","amera","234");
		driver.findElement(By.className("continue-link")).click();
		homePage.goToCredentialsTab();
		Credential credential = homePage.getCredential(0);
		Assertions.assertEquals("http://localhost:8080/chathome",credential.getUrl());
		Assertions.assertEquals("amera",credential.getUsername());
		
	}
	
	@Test
	// test password is not hashed at edit
	public void testEditCredentialPasswordHash()   {
		doMockSignUp("Test","Test","credentialeditpassword","123");
		doLogIn("credentialeditpassword", "123");
		HomePage homePage = new HomePage(driver);
		homePage.goToCredentialsTab();
		homePage.addCredentials("http://localhost:8080/home", "hend", "123");
		driver.findElement(By.className("continue-link")).click();
		homePage.goToCredentialsTab();
		Assertions.assertEquals("123",homePage.getEditCredentialPasswordFromModal(0));
	}
	
	@Test
	public void testDeleteCredential()   {
		doMockSignUp("Test","Test","credentialDelete","123");
		doLogIn("credentialDelete", "123");
		HomePage homePage = new HomePage(driver);
		homePage.goToCredentialsTab();
		homePage.addCredentials("http://localhost:8080/home", "hend", "123");
		driver.findElement(By.className("continue-link")).click();
		homePage.goToCredentialsTab();
		homePage.addCredentials("http://localhost:8080/login", "rana", "123");
		driver.findElement(By.className("continue-link")).click();
		homePage.goToCredentialsTab();
		homePage.deleteCredential(0);
		driver.findElement(By.className("continue-link")).click();
		homePage.goToCredentialsTab();
		Credential credential = homePage.getCredential(0);
		Assertions.assertNotEquals("http://localhost:8080/home",credential.getUrl());
			
	}
	
	@Test
	public void testUploadFile()   {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		doMockSignUp("Test","Test","uploadFile","123");
		doLogIn("uploadFile", "123");

		HomePage homePage = new HomePage(driver);
		homePage.uploadFile("naruto.jfif");
		try {
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
		} catch (org.openqa.selenium.TimeoutException e) {
			Assertions.assertEquals("1","2");
		}
		Assertions.assertEquals("Success",driver.findElement(By.id("success")).getText());

		
	}
	
	@Test
	public void testDownloadFile() throws InterruptedException   {
		doMockSignUp("Test","Test","downloadFile","123");
		doLogIn("downloadFile", "123");
		HomePage homePage = new HomePage(driver);
		homePage.uploadFile("naruto.jfif");
		driver.findElement(By.className("continue-link")).click();
		homePage.downloadFile(0);
		Thread.sleep(3000);
		File folder = new File( System.getProperty("user.dir")+"\\downloads");
		File[] listOfFiles = folder.listFiles();
		boolean found = false;
		for (File file : listOfFiles) {
	         if (file.isFile()) {
	              String fileName = file.getName();
	               if (fileName.equals("naruto.jfif")) {
	                   found = true;
	                }
	            }
	        }
		Assertions.assertTrue(found);
	}

}

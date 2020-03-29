package pageClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage_PageObject {
	WebDriver driver;
	HomePage_PageObject home;
	
	@FindBy(id="email")
	WebElement emailTextBox;
	
	@FindBy(id="passwd")
	WebElement passwordTextBox;

	@FindBy(id="SubmitLogin")
	WebElement signInButton;
	
	//Constructor
	public SignInPage_PageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Method to fill the email id text box.
	public void fillEmailTextBox(String emailID) {
		emailTextBox.sendKeys(emailID);
	}
	
	//Method to fill the password text box.
	public void fillPasswordTextBox(String password) {
		passwordTextBox.sendKeys(password);
	}
	
	//Method to click the Sign In button
	public void clickSignInButton() {
		signInButton.click();
	}
	
	public void signInWith(String loginID, String password) {
		home = new HomePage_PageObject(driver);
		home.clickSignInLink();
		emailTextBox.sendKeys(loginID);
		passwordTextBox.sendKeys(password);
		signInButton.click();
	}
}

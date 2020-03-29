package pageClass;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import myUtilityClasses.FileUploadCapabilities;

public class ContactUs_PageObject {
	
	FileUploadCapabilities f = new FileUploadCapabilities();
	
	WebDriver driver;
	
	@FindBy(id="contact-link")
	WebElement contactUsLink;
	
	@FindBy(id="id_contact")
	WebElement subjectDropdown;
	
	@FindBy(id="email")
	WebElement emailAddressTextBox;
	
	@FindBy(id="id_order")
	WebElement orderReferenceTextBox;
	
	@FindBy(id="submitMessage")
	WebElement submitMessageButton;
	
	@FindBy(id="message")
	WebElement composeMessageTextField;
	
	@FindBy(xpath=".//span[text()='Choose File']")
	WebElement chooseFileButton;
	
	public void clickContactUsLink() {
		contactUsLink.click();
	}
	
	public void selectSubject() {
		Select select = new Select(subjectDropdown);
		select.selectByVisibleText("Customer service");
	}
	
	public void enterEmailAddress(String emailID) {
		emailAddressTextBox.sendKeys(emailID);
	}
	
	public void enterOrderReferenceNumber(String orderNumber) {
		orderReferenceTextBox.sendKeys(orderNumber);
	}
	
	public void composeTextMessage(String myMessage) {
		composeMessageTextField.sendKeys(myMessage);
	}
	
	public void uploadAFile() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", chooseFileButton);
		Actions act = new Actions(driver);
		act.moveToElement(chooseFileButton).click().build().perform();
		FileUploadCapabilities.fileUploadFromFileExlorer();
	}
	
	public void clickSendButton() {
		submitMessageButton.click();
	}
	
	public ContactUs_PageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}

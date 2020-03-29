/**
 * 
 */
package pageClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This is the HomePage Class that will contain all the objects/elements on the page that we are interested in.
 * Website that we are using is "http://automationpractice.com/index.php"
 *
 */
public class HomePage_PageObject {
	
	WebDriver driver;
	
	@FindBy(xpath=".//a[@class='login']")
	WebElement signIn;
	
	@FindBy(id="search_query_top")
	WebElement searchTextBox;
	
	@FindBy(xpath=".//button[@name='submit_search']")
	WebElement searchButton;
	
	
	public void clickSignInLink() {
		signIn.click();
	}	
	
	public void fillSearchTextBox(String searchKeyWord) {
		searchTextBox.sendKeys(searchKeyWord);
	}
	
	public void clickSearchButton() {
		searchButton.click();
	}
	
	public HomePage_PageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}

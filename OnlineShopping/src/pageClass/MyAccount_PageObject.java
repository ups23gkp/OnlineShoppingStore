package pageClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class MyAccount_PageObject {

	public static WebElement element = null;

	public static WebElement myAccountFooterLink(WebDriver driver) {
		return element = driver.findElement(By.xpath(".//a[@title='Manage my customer account']"));
	}

	public static WebElement myAddressButton(WebDriver driver) {
		return element = driver.findElement(By.xpath(".//span[text()='My addresses']"));
	}

	public static WebElement addNewAddressButton(WebDriver driver) {
		return element = driver.findElement(By.xpath(".//a[@title='Add an address']"));
	}

	public static WebElement addressLineOneTextBox(WebDriver driver) {
		return driver.findElement(By.id("address1"));
	}

	public static WebElement addressLineTwo(WebDriver driver) {
		return driver.findElement(By.id("address2"));
	}

	public static WebElement cityTextBox(WebDriver driver) {
		return driver.findElement(By.id("city"));
	}

	public static WebElement stateSelectList(WebDriver driver) {
		return element = driver.findElement(By.id("id_state"));
	}

	public static WebElement zipCodeTextBox(WebDriver driver) {
		return driver.findElement(By.id("postcode"));
	}

	public static WebElement homePhoneTextBox(WebDriver driver) {
		return driver.findElement(By.id("phone"));
	}

	public static WebElement mobilePhoneNumberTextBox(WebDriver driver) {
		return driver.findElement(By.id("phone_mobile"));
	}

	public static WebElement addressAliasNameTextBox(WebDriver driver) {
		return driver.findElement(By.id("alias"));
	}

	public static WebElement saveButton(WebDriver driver) {
		return driver.findElement(By.id("submitAddress"));
	}

	public static List<WebElement> deleteAddressButton(WebDriver driver) {
		return driver.findElements(By.xpath(".//span[text()='Delete']"));
	}

	public void clickMyAccountLink(WebDriver driver) {
		element = myAccountFooterLink(driver);
		element.click();
	}

	public void clickMyAddressButton(WebDriver driver) {
		element = myAddressButton(driver);
		element.click();
	}

	public void clickAddNewAddressButton(WebDriver driver) {
		element = addNewAddressButton(driver);
		element.click();
	}

	public void enterAddressDetails(WebDriver driver, String addressLine1, String addressLine2, String city,
			String zipCode, String homePhone, String mobilePhone, String state) {
		addressLineOneTextBox(driver).sendKeys(addressLine1);
		addressLineTwo(driver).sendKeys(addressLine2);
		cityTextBox(driver).sendKeys(city);
		zipCodeTextBox(driver).sendKeys(zipCode);
		homePhoneTextBox(driver).sendKeys(homePhone);
		mobilePhoneNumberTextBox(driver).sendKeys(mobilePhone);
		selectStateFromList(driver, state);
	}

	public void clickSaveButton(WebDriver driver) {
		element = saveButton(driver);
		element.click();
	}

	public void selectStateFromList(WebDriver driver, String state) {
		Select sel = new Select(stateSelectList(driver));
		sel.selectByVisibleText(state);
	}

	public void enterAliasNameForNewAddress(WebDriver driver, String myString) {
		element = addressAliasNameTextBox(driver);
		element.sendKeys(myString);
	}

	public void deleteAddresses(WebDriver driver) throws Exception {
		int size = deleteAddressButton(driver).size();

		if (size >= 1) {
			for (int i = 0; i <= size; i++) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0, 600);");
				deleteAddressButton(driver).get(i).click();
				Thread.sleep(2000);
				driver.switchTo().alert().accept();
				Thread.sleep(3000);
				driver.navigate().refresh();
				break;
			}
		} else {
			System.out.println("There are no addresses present.");
		}

	}

	public MyAccount_PageObject(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/*
	 * The below code I have written to implement a way to enter random string in
	 * alias in the address name. The code is giving me an error related to
	 * OutOfMemory. Need to analyze so that I can call this in my test method.
	 */
	public static String getRandomString(int n) {
		String myString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "1234567890";
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i--) {
			int index = (int) (myString.length() * Math.random());
			sb.append(myString.charAt(index));
		}
		return sb.toString();
	}

	public void enterAliasNameForNewAddress(WebDriver driver) {
		addressAliasNameTextBox(driver).sendKeys(getRandomString(3));
	}

}

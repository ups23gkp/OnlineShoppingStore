package pageClass;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCart_PageObject {

	WebDriver driver;

	@FindBy(xpath = ".//li[@class='ajax_block_product col-xs-12 col-sm-6 col-md-4 first-in-line first-item-of-tablet-line first-item-of-mobile-line']")
	WebElement item;

	@FindBy(xpath = ".//span[contains(text(), 'Quick view')]")
	WebElement quickView;

	@FindBy(xpath = ".//p[@id='add_to_cart']//span[text()='Add to cart']")
	WebElement addToCart;

	@FindBy(xpath = ".//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]")
	WebElement proceedToCheckoutButton;

	@FindBy(xpath = ".//a[@title='View my shopping cart']")
	WebElement shoppingCart;
	
	@FindBy(id="cgv")
	WebElement termsOfServicesCheckBox;
	
	@FindBy(xpath = ".//a[@class='bankwire']")
	WebElement payByBankWirePaymentOption;
	
	@FindBy(xpath=".//span[contains(text(), 'I confirm my order')]")
	WebElement confirmOrderButton;

	public void quickViewItem() {
		Actions ac = new Actions(driver);
		ac.moveToElement(item).perform();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 400);");
		ac.moveToElement(quickView).click().perform();
	}

	public void addItemToCart() throws Exception {
		driver.switchTo().frame(0);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", addToCart);
		Thread.sleep(1000);
		driver.navigate().refresh();
	}

	public void checkOutAnItem() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 600);");
		Actions ac = new Actions(driver);
		ac.moveToElement(proceedToCheckoutButton).click().build().perform();
	}
	
	public void verifyAddressDetails() {
		WebDriverWait wait = new WebDriverWait(driver, 3);
		boolean bool = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("invisible")));
		WebElement proceedToCheckoutButtonOnAddressFlow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[@class='button btn btn-default button-medium']//span[contains(text(),'Proceed to checkout')]")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", proceedToCheckoutButtonOnAddressFlow);
		Actions ac = new Actions(driver);
		ac.moveToElement(proceedToCheckoutButtonOnAddressFlow).build().perform();
		ac.moveToElement(proceedToCheckoutButtonOnAddressFlow).click().build().perform();
	}
	
	public void verifyShippingDetails() {
		WebDriverWait wait = new WebDriverWait(driver, 3);
		WebElement proceedToCheckoutButtonOnShippingFlow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//button[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]")));
		termsOfServicesCheckBox.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", proceedToCheckoutButtonOnShippingFlow);
		Actions ac = new Actions(driver);
		ac.moveToElement(proceedToCheckoutButtonOnShippingFlow).click().build().perform();
	}

	public void viewShoppingCart() {
		shoppingCart.click();
	}
	
	public void payBy() {
		WebDriverWait wait = new WebDriverWait(driver, 3);
		WebElement payByBankWirePaymentOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[@class='bankwire']")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", payByBankWirePaymentOption);
		Actions ac = new Actions(driver);
		ac.moveToElement(payByBankWirePaymentOption).click().build().perform();
		WebElement confirmOrderButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span[contains(text(), 'I confirm my order')]")));
		js.executeScript("arguments[0].scrollIntoView();", confirmOrderButton);
		ac.moveToElement(confirmOrderButton).click().build().perform();
	}

	public ShoppingCart_PageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}

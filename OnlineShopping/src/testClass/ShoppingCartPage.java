package testClass;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import pageClass.HomePage_PageObject;
import pageClass.ShoppingCart_PageObject;
import pageClass.SignInPage_PageObject;


public class ShoppingCartPage {
	WebDriver driver;
	public String baseURL;
	String expectedConfirmationMessage = "Your order on My Store is complete.";
	LoginToPracticePage loginPage;
	HomePage_PageObject onHomePage;
	ShoppingCart_PageObject onShoppingCart;
	SignInPage_PageObject onSignIn;

	@Parameters({"browser"})
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(String browser) {
		if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		}
		baseURL = "http://automationpractice.com/index.php";
		onHomePage = new HomePage_PageObject(driver);
		onShoppingCart = new ShoppingCart_PageObject(driver);
		onSignIn = new SignInPage_PageObject(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get(baseURL);
		System.out.println("beforeMethod() executed.");
		onSignIn.signInWith("testlogin216@gmail.com", "Test@1985");
	}

	@Test(groups={"end2end", "functionalTest", "regressionTest"})
	public void purchaseAProduct() throws Exception {
		SoftAssert sa = new SoftAssert();
		onHomePage.fillSearchTextBox("Dress");
		onHomePage.clickSearchButton();
		onShoppingCart.quickViewItem();
		onShoppingCart.addItemToCart();
		onShoppingCart.viewShoppingCart();
		onShoppingCart.checkOutAnItem();
		onShoppingCart.verifyAddressDetails();
		onShoppingCart.verifyShippingDetails();
		onShoppingCart.payBy();
		sa.assertEquals(expectedConfirmationMessage, "Your order on My Store is complete.");
		sa.assertAll();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		driver.quit();
	}

}

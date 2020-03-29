/*
 * This needs to be worked upon.
 * This class is not yet ready to be consumed.
 */

package myUtilityClasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pageClass.ContactUs_PageObject;
import pageClass.HomePage_PageObject;
import pageClass.MyAccount_PageObject;
import pageClass.ShoppingCart_PageObject;
import pageClass.SignInPage_PageObject;
import testClass.LoginToPracticePage;

public class BeforeAndAfterMethodClass extends WebDriverClassFile {
	
	public BeforeAndAfterMethodClass(WebDriver driver, String baseURL) {
		super(driver, baseURL);
		// TODO Auto-generated constructor stub
	}

	LoginToPracticePage loginPage;
	HomePage_PageObject onHomePage;
	ShoppingCart_PageObject onShoppingCart;
	SignInPage_PageObject onSignIn;
	ContactUs_PageObject onContactUs;
	MyAccount_PageObject onMyAccountPage;
	SignInPage_PageObject onSignInPage;

	//ContactUsPage
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {
		//driver = new ChromeDriver();
		driver = new FirefoxDriver();
		baseURL = "http://automationpractice.com/index.php";
		onContactUs = new ContactUs_PageObject(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get(baseURL);
	}

	//LoginToPracticePage
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod1() {
		driver = new ChromeDriver();
		baseURL = "http://automationpractice.com/index.php";
		onHomePage = new HomePage_PageObject(driver);
		onSignIn = new SignInPage_PageObject(driver);
		onShoppingCart = new ShoppingCart_PageObject(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get(baseURL);
		System.out.println("beforeMethod() executed.");
	}
	
	//MyAccountAndAddressPage
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod2() {
		driver = new ChromeDriver();
		baseURL = "http://automationpractice.com/";
		onMyAccountPage = new MyAccount_PageObject(driver);
		onSignInPage = new SignInPage_PageObject(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		driver.get(baseURL);
	}
	
	//ShoppingCartPage
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod3() {
		driver = new FirefoxDriver();
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
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		driver.quit();
		System.out.println("Test successful. afterMethod() executed for Contact Class from Utilit Class.");
	}

}
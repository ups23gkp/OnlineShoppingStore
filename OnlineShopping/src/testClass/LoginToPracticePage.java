package testClass;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageClass.HomePage_PageObject;
import pageClass.ShoppingCart_PageObject;
import pageClass.SignInPage_PageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class LoginToPracticePage {
	public WebDriver driver;
	public String baseURL;
	HomePage_PageObject onHomePage;
	SignInPage_PageObject onSignIn;
	ShoppingCart_PageObject onShoppingCart;

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {
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

	@BeforeTest
	public void beforeTest() {
		System.out.println("beforeTest() executed.");
	}

	@Test(groups = { "regressionTest", "end2end", "functionalTest" }, dataProvider="loginData", dataProviderClass=testDataPackage.testData.class)
	public void validatedLoginIsSuccessful(String loginID, String password) {
		onHomePage.clickSignInLink();
		System.out.println("Sign In link is clicked on the Home Page.");
		onSignIn.fillEmailTextBox(loginID);
		onSignIn.fillPasswordTextBox(password);
		onSignIn.clickSignInButton();
		System.out.println("User is signed In sucessfully.");
	}

	@Test(groups = { "regressionTest", "functionalTest" })
	public void searchWithoutAnyKeyword() throws Exception {
		onHomePage.fillSearchTextBox("");
		onHomePage.clickSearchButton();
		String expectedText = "Please enter a search keyword";
		String eT = driver.findElement(By.xpath(".//p[contains(text(), 'Please enter a search keyword')]")).getText();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(expectedText, eT);
		System.out.println("text matched.");
		Thread.sleep(1000);
		sa.assertAll();
	}

	@Test(groups = { "regressionTest", "smokeTest" })
	public void searchWithKeyword() {
		onHomePage.fillSearchTextBox("Dress");
		onHomePage.clickSearchButton();

	}

	@AfterTest
	public void afterTest() {
		System.out.println("afterTest() executed.");
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() throws Exception {
		/*
		 * This sleep method is used for testing purpose only. Usage of sleep is
		 * discouraged.
		 */
		Thread.sleep(3000);
		driver.quit();
		System.out.println("afterMethod() executed.");
	}
}

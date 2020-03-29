package testClass;

import org.testng.annotations.Test;

import pageClass.MyAccount_PageObject;
import pageClass.SignInPage_PageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyAccountAndAddress {
	WebDriver driver;
	String baseURL;
	MyAccount_PageObject onMyAccountPage;
	SignInPage_PageObject onSignInPage;
	
	
	@Test (groups = {"regressionTest", "smokeTest"}, dependsOnMethods= {"deleteAddressesFromThePage"})
	public void verifyNewAddressIsSaved() {
		onSignInPage.signInWith("testlogin216@gmail.com", "Test@1985");
		onMyAccountPage.clickMyAccountLink(driver);
		onMyAccountPage.clickMyAddressButton(driver);
		onMyAccountPage.clickAddNewAddressButton(driver);
		onMyAccountPage.enterAddressDetails(driver, "1234 XYZ Street", "Apt 134", "Austin", "76567", "123456789", "987654321", "Texas");
		// onMyAccountPage.enterAliasNameForNewAddress(driver);
		onMyAccountPage.enterAliasNameForNewAddress(driver, "My New Address");
		onMyAccountPage.clickSaveButton(driver);
	}
	
	@Test (groups = {"regressionTest"})
	public void deleteAddressesFromThePage() throws Exception {
		onSignInPage.signInWith("testlogin216@gmail.com", "Test@1985");
		onMyAccountPage.clickMyAccountLink(driver);
		onMyAccountPage.clickMyAddressButton(driver);
		onMyAccountPage.deleteAddresses(driver);
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {
		driver = new ChromeDriver();
		baseURL = "http://automationpractice.com/";
		onMyAccountPage = new MyAccount_PageObject(driver);
		onSignInPage = new SignInPage_PageObject(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		driver.get(baseURL);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() throws Exception {
		Thread.sleep(3000);
		driver.quit();
	}

}

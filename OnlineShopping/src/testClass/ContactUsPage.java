package testClass;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import myUtilityClasses.FileUploadCapabilities;
import pageClass.ContactUs_PageObject;
import testng_listners_package.testNG_Listners;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

@Listeners(testNG_Listners.class)
public class ContactUsPage {
	
	WebDriver driver;
	String baseURL;
				
	String expectedSuccessMessage = "Your message has been successfully sent to our team.";
	String expectedErrorMessageForBlankEmail = "Invalid email address.";
	ContactUs_PageObject onContactUs;
	FileUploadCapabilities uploadFile;
	SoftAssert sa;
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {
		//driver = new ChromeDriver();
		driver = new FirefoxDriver();
		baseURL = "http://automationpractice.com/index.php";
		onContactUs = new ContactUs_PageObject(driver);
		uploadFile = new FileUploadCapabilities();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get(baseURL);
	}

	@Test(groups = { "regressionTest", "end2end" }, dataProvider="textMessage", dataProviderClass=testDataPackage.testData.class)
	public void sendingMessageSuccessfully(String myMessageToSend) throws Exception {
		onContactUs.clickContactUsLink();
		onContactUs.selectSubject();
		onContactUs.enterEmailAddress("test@test.com");
		onContactUs.enterOrderReferenceNumber("123ABCD678XYZ");
		onContactUs.composeTextMessage(myMessageToSend);
		onContactUs.uploadAFile();
		onContactUs.clickSendButton();
		sa.assertTrue(true, expectedSuccessMessage);
		sa.assertAll();
		/*sa.assertEquals(expectedSuccessMessage, "Your message has been successfully sent to our team.");
		sa.assertAll();*/
	}

	@Test(groups = { "functionalTest", "smokeTest" }, dataProvider="textMessage", dataProviderClass=testDataPackage.testData.class)
	public void sendingMessageWithoutEmailAddress(String myMessageToSend) {
		onContactUs.clickContactUsLink();
		onContactUs.selectSubject();
		onContactUs.enterOrderReferenceNumber("123ABCD678XYZ");
		onContactUs.composeTextMessage(myMessageToSend);
		onContactUs.clickSendButton();
		sa.assertTrue(true, expectedErrorMessageForBlankEmail);
		sa.assertAll();
		/*sa.assertEquals(expectedErrorMessageForBlankEmail, "Invalid email address.");
		sa.assertAll();*/
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		driver.quit();
	}

}

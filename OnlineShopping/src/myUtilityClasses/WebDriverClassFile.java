package myUtilityClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverClassFile {
	
	public WebDriver driver;
	public String baseURL;
	
	public WebDriverClassFile(WebDriver driver, String baseURL) {
		driver = new FirefoxDriver();
		this.driver = driver;
		this.baseURL = baseURL;
	}

}

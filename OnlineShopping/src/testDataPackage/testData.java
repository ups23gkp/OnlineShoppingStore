package testDataPackage;

import org.testng.annotations.DataProvider;

public class testData {

	@DataProvider(name = "loginData")
	public Object[][] inputData() {
		return new Object[][] { 
			{ "test1@dummyemail.com", "myPassword123" }, // Invalid data
			{ "test2@dummyemail.com", "DummyPassword234" }, // Invalid data
			{ "testlogin216@gmail.com", "Test@1985" } // Valid data
		};
	}
	
	@DataProvider(name = "textMessage")
	public Object[][] inputText(){
		return new Object[][] {
			{"I have some questions related to my Order that I would like to discuss. "
					+ "I have some questions related to my Order that I would like to discuss."}
		};
	}
}

/*
 * List of group tags used in this projects - 
 * 	regressionTest 
 * 	end2end
 * 	functionalTest 
 * 	smokeTest
 */

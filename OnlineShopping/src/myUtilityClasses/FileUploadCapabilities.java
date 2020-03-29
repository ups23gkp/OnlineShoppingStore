package myUtilityClasses;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class FileUploadCapabilities {
	
	public static void fileUploadFromFileExlorer() throws Exception {
		StringSelection ss = new StringSelection("C:\\Users\\udangng.WIPRO\\Desktop\\uploadText.txt");
		Thread.sleep(4000);
		//To copy the file from file explorer
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		
		//Robot class to attach the file.
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(4000);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

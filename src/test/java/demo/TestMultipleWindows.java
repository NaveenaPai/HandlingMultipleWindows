package demo;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestMultipleWindows {

	public static void main(String[] args) {

		String parentWindowHandle, childWindowHandle;

		String url = "https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_link_target";

		String driverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);

		WebDriver driver = new ChromeDriver();

		// 1. Navigate to the test URL.
		driver.get(url);
		driver.manage().window().maximize();

		// 2. Get the window handle of the parent window
		parentWindowHandle = driver.getWindowHandle();

		// 3. Print the window handle and title of the parent window
		System.out.println("\nParent window handle id : " + parentWindowHandle);
		System.out.println("Title of parent window : " + driver.getTitle());

		// 4. Click on “Get your own website!” button
		WebElement button = driver.findElement(By.id("getwebsitebtn"));
		button.click();

		// 5. Get all window handles & print the total number of window handles
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println("\nTotal number of window handles (no. of windows open) : " + windowHandles.size());

		// 6. Switch to the child window, print the window handle and title
		Iterator<String> handleIterator = windowHandles.iterator();

		while (handleIterator.hasNext()) // while there are window handles in collection
		{
			childWindowHandle = handleIterator.next();

			if (!childWindowHandle.equals(parentWindowHandle)) // check if the window handle is of child
			{
				System.out.println("\nChild window handle id : " + childWindowHandle);
				driver.switchTo().window(childWindowHandle); // switch to child window
				System.out.println("Title of child window : " + driver.getTitle());
			}
		}
		// 7. Go back (Switch) to the first window (parent).
		driver.switchTo().window(parentWindowHandle);

		// 8. Close all the windows
		driver.quit();
	}
}

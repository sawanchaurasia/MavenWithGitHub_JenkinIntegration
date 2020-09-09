package EventHandling;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandling {
	WebDriver driver;

	@Test
	public void browserLaunch() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/browser-windows");

		String parentWindow = driver.getWindowHandle();
		System.out.println(parentWindow);

		driver.findElement(By.id("tabButton")).click();

//		driver.switchTo().newWindow(WindowType.WINDOW);
//		driver.navigate().to("https://www.facebook.com/");

		driver.switchTo().newWindow(WindowType.TAB);
		driver.navigate().to("https://www.youtube.com/");

		Set<String> set = driver.getWindowHandles();
		System.out.println("All windows and tab's Id" + set);

//		set.remove(parentWindow);
//		System.out.println("After removing parent window, Id's are: " + set);

		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			String currentWindow = itr.next();

			driver.switchTo().window(currentWindow);
		}
	}
}

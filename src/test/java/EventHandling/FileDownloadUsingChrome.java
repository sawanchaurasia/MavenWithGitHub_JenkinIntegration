package EventHandling;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FileDownloadUsingChrome {
	WebDriver driver;
	File files;
	String downloadPath = "C:\\Users\\dell\\Downloads\\AutomaticDownload";

	@BeforeMethod
	public void browserSetup() {
		// Path where we want to download

		HashMap<String, Object> chromePref = new HashMap<String, Object>();
		// Hiding Browser's generated Popups
		chromePref.put("profile.default_content_settings.popups", 0);
		// Assigning desired directory
		chromePref.put("download.default_directory", downloadPath);

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePref);

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	}

	@Test
	public void downloadingFile() throws InterruptedException {

		String appUrl = "https://demoqa.com/upload-download";
		driver.get(appUrl);

		driver.findElement(By.id("downloadButton")).click();
		Thread.sleep(2000);
	}

	@AfterMethod
	public void browserClose() throws InterruptedException {
		files = new File(downloadPath);
		File[] fileInDir = files.listFiles();

		for (File file : fileInDir) {
			String fileName = file.getName();
			System.out.println("File Namme is: " + fileName);
			if (fileName.equalsIgnoreCase("sampleFile.jpeg")) {
				System.out.println("File Downloaded Successfully");
			}
//			file.delete();
		}
		Thread.sleep(3000);
		driver.quit();
	}
}

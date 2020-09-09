package EventHandling;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DownloadFileUsingFirefox {
	WebDriver driver;
	File files;
	String downloadPath = "C:\\Users\\dell\\Downloads\\AutomaticDownload";

	@BeforeMethod
	public void browserSetup() {
		FirefoxOptions fo = new FirefoxOptions();
		fo.addPreference("browser.download.folderList", 2);
		fo.addPreference("browser.download.dir", downloadPath);
		fo.addPreference("browser.helperApps.neverAsk.saveToDisk",
				"image/jpg,image/png ,image/jpeg, application/pdf, text/html");

		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver(fo);
	}

	@Test
	public void fileDownloadFF() throws InterruptedException {

		String appUrl = "https://demoqa.com/upload-download";
//		String appUrl = "https://www.softpost.org/selenium-test-page/";
		driver.get(appUrl);

		driver.findElement(By.id("downloadButton")).click();
//		driver.findElement(By.xpath("//a[text()='Download']")).click();
		Thread.sleep(3000);
	}

	@AfterMethod
	public void browserClose() throws InterruptedException {
		files = new File(downloadPath);
		File[] fileInDir = files.listFiles();

		for (File file : fileInDir) {
			String fileName = file.getName();
			System.out.println("File Namme is: " + fileName);
			if (fileName.equalsIgnoreCase("myimage")) {
				System.out.println("File Downloaded Successfully");
			}
			file.delete();
		}
		Thread.sleep(3000);
		driver.quit();
	}
}

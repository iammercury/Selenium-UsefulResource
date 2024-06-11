package seleniumUtilities;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;

public class WaitForFileDownload {

	@Test
	public void test() {

		String downloadPath = "C:\\Users\\himan\\Downloads\\";
		String fileName = "jenkins.msi";

		ChromeOptions options = new ChromeOptions();
		/** Chrome option to enable safe browsing or else it will not allow to download the file. */
		Map<String, Object> prefs = new HashMap<>();
        prefs.put("safebrowsing.enabled", true);
        options.setExperimentalOption("prefs", prefs);
        
		WebDriver driver = new ChromeDriver(options);

		driver.get("https://get.jenkins.io/windows-stable/2.452.1/jenkins.msi");

		File file = new File(downloadPath, fileName);

		FluentWait<File> fw = new FluentWait<File>(file)
				.withTimeout(Duration.ofMinutes(5))
				.pollingEvery(Duration.ofSeconds(3))
				.ignoring(Exception.class)
				.withMessage("File not downloaded");

		try {
			boolean isDownloaded = fw.until(f -> f.exists() && f.canExecute());
			if (isDownloaded) {
				System.out.println("File is 100% downloaded");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

package seleniumUtilities;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DebuggingChrome {

	public static void main(String[] args) {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\HimanshuGoyal\\Documents\\libs\\chromedriver_win32\\chromedriver.exe");

		driver = new ChromeDriver();

		/*
		 * Passing ChromeOption object in constructor of ChromeDriver-Using which we can
		 * perform further action on the web page without running script from scratch
		 * 
		 * ChromeOptions option = new ChromeOptions();
		 * option.setExperimentalOption("debuggerAddress", "localhost:52849"); driver =
		 * new ChromeDriver(option);
		 */

		// Capability is used to get Debugger Address of the browser session
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		Map<String, Object> asMap = caps.asMap();
		asMap.forEach((key, value) -> {
			System.out.println("Key " + key + " Value " + value);
		});

		driver.get("https://google.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	}
}

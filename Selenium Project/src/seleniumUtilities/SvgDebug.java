package seleniumUtilities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SvgDebug {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.worldometers.info/coronavirus/country/india/");

		Actions act = new Actions(driver);
		List<WebElement> listElement = driver.findElements(By.xpath(
				"(//*[name()='svg' and @class='highcharts-root']//*[local-name()='g' and @class='highcharts-series-group'])[4]//*[local-name()='rect']"));

		for (WebElement list : listElement) {
			act.moveToElement(list).perform();
		}

	}

}

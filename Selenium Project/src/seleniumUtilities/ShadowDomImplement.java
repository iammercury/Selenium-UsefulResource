package seleniumUtilities;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ShadowDomImplement {

	static WebDriver driver = new ChromeDriver();
	static JavascriptExecutor js = (JavascriptExecutor) driver;

	public static WebElement findElementInShadowDom(String shadowPath, String cssSelector) {
		String[] shadowRoots = shadowPath.split(" > ");
		String script;
		if (shadowRoots.length <= 2) {
			script = "return document.querySelector('" + shadowPath + "').shadowRoot.querySelector('" + cssSelector
					+ "');";
		} else {
			String initialElement = String.format("%s > %s", shadowRoots[0], shadowRoots[1]);
			String remainingPath = Arrays.stream(shadowRoots, 2, shadowRoots.length)
					.map(root -> ".shadowRoot.querySelector('" + root + "')").collect(Collectors.joining());

			script = "return document.querySelector('" + initialElement + "')" + remainingPath
					+ ".shadowRoot.querySelector('" + cssSelector + "');";
		}
		System.out.println(script);
		try {
			WebElement shadowElement = (WebElement) js.executeScript(script);
			return shadowElement;
		} catch (Exception e) {
			throw new NoSuchElementException(
					"Unable to locate shadow DOM element using path: " + shadowPath + " and selector: " + cssSelector);
		}
	}

	public static void main(String[] args) {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		driver.get("chrome://downloads/");
		String shadowPath = "body > downloads-manager > downloads-toolbar > cr-toolbar > cr-toolbar-search-field";
		findElementInShadowDom(shadowPath, "#searchInput").sendKeys("Test");
		
		driver.navigate().to("https://books-pwakit.appspot.com/");
		shadowPath = "body > book-app";
		findElementInShadowDom(shadowPath, "#input").sendKeys("Test");
		

	}

}

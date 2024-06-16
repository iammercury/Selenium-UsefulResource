package seleniumUtilities;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class HandleCookies {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.guru99.com/test/cookie/selenium_aut.php");

		// Input Email id and Password If you are already Register
//		driver.findElement(By.name("username")).sendKeys("abc123");
//		driver.findElement(By.name("password")).sendKeys("123xyz");
//		driver.findElement(By.name("submit")).click();

//		1. Retrieve Cookies
		/*
		 * Set<Cookie> cookies = driver.manage().getCookies(); for (Cookie cookie :
		 * cookies) { System.out.println(cookie); }
		 * 
		 * Cookie specificCookie = driver.manage().getCookieNamed("cookieName");
		 * System.out.println(specificCookie);
		 */

//		2. Add Cookies
		/*
		 * Cookie cookie = new Cookie("cookie_name", "cookie_value");
		 * driver.manage().addCookie(cookie);
		 */

//      3. Delete Cookies
		/* driver.manage().deleteCookieNamed("cookie_name"); */

		/*
		 * 4. Use Cookies to Restore Session To use cookies to restore a session, follow
		 * these steps: Retrieve cookies from an active session. Save the cookies to a
		 * storage (e.g., file, database). Load the cookies from storage in a new
		 * session.
		 */

		// Retrieve cookies
//        Set<Cookie> cookies = driver.manage().getCookies();
//
//        // Convert cookies to JSON and save to a file
//        try (FileWriter file = new FileWriter("cookies.json")) {
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            gson.toJson(cookies, file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

		// Load cookies from the file
		Set<Cookie> setCookies;
		try (FileReader reader = new FileReader("cookies.json")) {
			Gson gson = new Gson();
			setCookies = gson.fromJson(reader, new TypeToken<Set<Cookie>>() {
			}.getType());
		}

		// Add cookies to the browser
		for (Cookie cook : setCookies) {
			driver.manage().addCookie(cook);
		}

		// Refresh the page to apply the cookies
		driver.navigate().refresh();

		String colour = driver.findElement(By.xpath("//h2")).getCssValue("color");
		System.out.println(colour);

		Color col = new Color(51, 51, 51);
		String hex = String.format("#%02x%02x%02x", 51, 51, 51);
		System.out.println(hex);
	}

}

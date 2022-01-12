package VariousConcept;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LearnWindowHandle {
	WebDriver driver;

	@Test
	public void init() {

		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("https://www.yahoo.com/");
		// driver.manage().window().maximize();

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		System.out.println(driver.getTitle());
		String handle = driver.getWindowHandle();
		System.out.println(handle);
        System.out.println("=....======================================================");
		driver.findElement(By.xpath("//*[@id=\"ybar-sbq\"]")).sendKeys("xpath");
		driver.findElement(By.xpath("//*[@id=\"ybar-search\"]")).click();
		System.out.println(driver.getTitle());
		String handle1 = driver.getWindowHandle();
		System.out.println(handle1);
        System.out.println("=....======================================================");

	    driver.findElement(By.xpath("//*[@id=\"web\"]/ol/li[1]/div/div[1]/h3/a")).click();

		

	//amar link text kaj kore nai, so used xpath
	
	
	
	System.out.println(driver.getTitle());
	Set<String> handles =driver.getWindowHandles();
	System.out.println(handles);
	
	for(String i: handles) {
		System.out.println(i);  //aikhane i holo individual String dibe so ailine na likhleo hobe
	driver.switchTo().window(i);
	}
	
	System.out.println(driver.getTitle());
	driver.switchTo().window(handle1);
	System.out.println(driver.getTitle());

	
	
	
	
	
	
	}
}

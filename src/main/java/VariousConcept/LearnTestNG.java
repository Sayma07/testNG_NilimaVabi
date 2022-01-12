
package VariousConcept;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import junit.framework.Assert;

public class LearnTestNG {

	WebDriver driver;
	String browser = null;
	String url = null;

	@BeforeTest
	public void readConfig() {
		Properties prop = new Properties();
		// FileReader //InputStream //BufferReader //Scanner

		try {
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("Used Browser:" + browser);
			url = prop.getProperty("url");

		
		
		}

		catch (IOException e) {

			e.printStackTrace();

		}

	}

	@BeforeMethod
	public void init() {

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Fierfox")) {
			System.setProperty("webdriver.firefox.bin",
					"C:\\Users\\nazmu\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
			System.setProperty("webdriver.gecko.driver", "driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	// @Test(priority=1)
	public void loginTest1() {

		Assert.assertEquals("LogIn Page not found", "Login - iBilling", driver.getTitle());

		WebElement UserName_Field_Element = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement Password_Field_Element = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement SIGNIN_Button_Element = driver.findElement(By.xpath("/html/body/div/div/div/form/div[3]/button"));

		UserName_Field_Element.clear();
		UserName_Field_Element.sendKeys("demo@techfios.com");
		Password_Field_Element.clear();
		Password_Field_Element.sendKeys("abc123");
		SIGNIN_Button_Element.click();

		WebElement DASHBOARD_BUTTON_ELEMENT = driver.findElement(By.linkText("Dashboard"));
		String actualaDashboardElement = DASHBOARD_BUTTON_ELEMENT.getText();
		
		
		
		By Dashboard_Field_Locator = By.xpath("//h2[contains(text(),' Dashboard ')]");
        waitForElement(driver, 3, Dashboard_Field_Locator);

		Assert.assertEquals("DashBoard page not found", "Dashboard", DASHBOARD_BUTTON_ELEMENT.getText());

	}

	@Test(priority = 2)
	public void addCustomerTest() {

		By USER_NAME_FIELD = By.id("username");
		By PASSWORD_FIELD = By.id("password");
		By SIGNIN_BUTTON = By.xpath("/html/body/div/div/div/form/div[3]/button");
		By MENUE_BUTTON = By.xpath("//i[@ class='fa fa-dedent fa-indent']");
		By Dashboard_Field_Locator = By.xpath("//h2[contains(text(),' Dashboard ')]");
		By Customer_Button = By.xpath("//span[contains(text(),'Customers')]");
		By ADD_Customer_Button = By.xpath("//a[contains(text(),'Add Customer')]");
		By Add_Contact_Locator = By.xpath("//h5[contains(text(),'Add Contact')]");
		By FULL_NAME_LOCATOR = By.xpath("//input[@id='account'  ]");
		By Company_Nmae_Locator = By.xpath("//*[@id=\"cid\"]");
		By Email_Locator = By.xpath("//*[@id=\"email\"]");
		By Phone_NO_Locator = By.xpath("//*[@id=\"phone\"]");
		By Address_Locator = By.xpath("//*[@id=\"address\"]");
		By City_Locator = By.xpath("//*[@id=\"city\"]");
		By State_Field_Locator = By.xpath("//*[@id=\"state\"]");
		By Zip_Field = By.xpath("//input[@id='zip']");
		By Submit_Button = By.xpath("");
//By List_Contact_Button=

//logIn
		String loginID = "demo@techfios.com";
		String password = "abc123";

		// Test Data
		String fullName = "Test August";
		String companyName = "Techfios";
		String emailAddress = "test@gmail.com";
		String phoneNumber = "2316564564";

		Assert.assertEquals("LogIn Page not found", "Login - iBilling", driver.getTitle());

		// login
		driver.findElement(USER_NAME_FIELD).sendKeys(loginID);
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		driver.findElement(SIGNIN_BUTTON).click();

		WebElement DASHBOARD_BUTTON_ELEMENT = driver.findElement(By.linkText("Dashboard"));
		String actualaDashboardElement = DASHBOARD_BUTTON_ELEMENT.getText();   //webelement

		
		waitForElement(driver, 4, Dashboard_Field_Locator);
		Assert.assertEquals("DashBoard page not found", "Dashboard", DASHBOARD_BUTTON_ELEMENT.getText());
		driver.findElement(Customer_Button).click();
		
		
		waitForElement(driver, 2, ADD_Customer_Button);  //aigula By class
		driver.findElement(ADD_Customer_Button).click();

		Random rnd = new Random();
		int generatedNo = rnd.nextInt(999);

		waitForElement(driver, 3, FULL_NAME_LOCATOR);

		driver.findElement(FULL_NAME_LOCATOR).sendKeys(fullName + generatedNo);
		driver.findElement(Email_Locator).sendKeys(generatedNo + emailAddress);

	}

	private void waitForElement(WebDriver driver, int timeInSeconds, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));

	}

//@AfterMethod
	public void tearDown() {

		driver.close();
		driver.quit();

	}

}

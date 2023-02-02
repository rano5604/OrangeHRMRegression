package test.com;

import java.awt.AWTException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import page.com.DirectoryPage;
import page.com.LoginPage;
import page.com.PIMPage;
@Test
public class TextExecution {

	WebDriver driver;
	WebDriverWait wait;
	@BeforeClass
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		
	}
	
	@Test(priority=1)
	public void lunchURL() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
		WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
		Assert.assertEquals(loginButton.getText(), "Login");
	}
	
	@Test(dependsOnMethods= {"lunchURL"})
	public void loginToSite() {
		LoginPage login = new LoginPage(driver);
		login.performLogin("Admin", "admin123");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-userdropdown")));
		WebElement profile = driver.findElement(By.className("oxd-userdropdown"));
		Assert.assertEquals(profile.isDisplayed(), true);
	}
	
	
	@Test(dependsOnMethods= {"loginToSite"})
	public void addEmployee() throws Exception {
		PIMPage pim = new PIMPage(driver);
		
		pim.openPIM();
		pim.addEmployee("Fahmud","Kabir","Islam");
		pim.openJobDetails();
		pim.addJobDetails("Job Title","QA Engineer");
		pim.addJobDetails("Location","Texas R&D");
		pim.addJobDetails("Joined Date","2022-12-20");
		pim.saveJobDetails();
		pim.openContactDetails();
		pim.addContactDetails("Work", "01711084452");
		pim.addContactDetails("Work Email", "rano56@gmail.com");
		pim.saveContactdetails();
		
		//pim.addContactDetails();
	}
	
	@Test(dependsOnMethods= {"addEmployee"})
	public void openDirectory() throws Exception {
		DirectoryPage dir = new DirectoryPage(driver);
		dir.openDirectory();
	}
	
	@Test(dependsOnMethods= {"openDirectory"})
	public void setSearchCriteria() throws Exception {
		DirectoryPage dir = new DirectoryPage(driver);
		dir.setSearchCriteria("Job Title", "QA Engineer");
		dir.setSearchCriteria("Location", "Texas R&D");
		dir.performSearch();
	}
	
	@Test(dependsOnMethods= {"setSearchCriteria"})
	public void performSearch() throws Exception {
		DirectoryPage dir = new DirectoryPage(driver);
		dir.performSearch();
	}
	
	@Test(dependsOnMethods= {"performSearch"})
	public void verifySearch() throws Exception {
		DirectoryPage dir = new DirectoryPage(driver);
		dir.verifySeach("Fahmud Kabir Islam","QA Engineer","Texas R&D","01711084452","rano56@gmail.com");
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
	
}

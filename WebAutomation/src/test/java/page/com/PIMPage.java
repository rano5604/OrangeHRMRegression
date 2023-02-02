package page.com;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PIMPage {
	WebDriver driver;
	WebDriverWait wait;

	public PIMPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public void openPIM() {
		driver.findElement(By.xpath("//a[@href='/web/index.php/pim/viewPimModule']")).click();
		String url = driver.getCurrentUrl();
		if (!(url.contains("pim"))) {
			Assert.assertTrue(false, "PIM Page Not Loaded");
		}
	}

	public void addEmployee(String firstname, String middlename, String lastname) throws AWTException, Exception {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Add Employee")));

		driver.findElement(By.linkText("Add Employee")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='firstName']")));

		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(firstname);
		driver.findElement(By.xpath("//input[@name='middleName']")).sendKeys(middlename);
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lastname);

		WebElement upload = driver.findElement(By.xpath("//button[@class='oxd-icon-button employee-image-action']"));

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", upload);

		driver.findElement(By.xpath("//button[@class='oxd-icon-button employee-image-action']")).click();

		StringSelection ss = new StringSelection(System.getProperty("user.dir") + "\\Images\\sanaul.png");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(3000);
		ss = new StringSelection("sanaul.png");

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(5000);
		
		/*
		 * String msg = driver.findElement(By.
		 * xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']"
		 * )).getText();
		 * 
		 * if(msg.contains("Employee Id already exists")) { String id =
		 * driver.findElement(By.
		 * xpath("//input[@class='oxd-input oxd-input--active oxd-input--error']")).
		 * getText(); int newid = Integer.parseInt(id); newid++; driver.findElement(By.
		 * xpath("//input[@class='oxd-input oxd-input--active oxd-input--error']")).
		 * sendKeys(String.valueOf(newid)); }
		 */
		String url = driver.getCurrentUrl();

		if (!(url.contains("/pim/viewPersonalDetails/empNumber"))) {
			Assert.assertTrue(false, "Employee Not Added Successfully");
		}

	}

	public void addJobDetails() throws Exception {
		Thread.sleep(2000);
		List<WebElement> menu = driver.findElements(By.xpath("//a[@class='orangehrm-tabs-item']"));

		for (WebElement m : menu) {
			if (m.getText().equals("Job")) {
				m.click();
				m.click();
			}
		}
		
		/*
		 * driver.get(driver.getCurrentUrl()); Thread.sleep(5000);
		 * driver.findElement(By.xpath("//i[@class='oxd-icon bi-chevron-left']")).click(
		 * ); wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "//input[@placeholder='yyyy-mm-dd']")));
		 * driver.findElement(By.xpath("//input[@placeholder='yyyy-mm-dd']")).sendKeys(
		 * "2022-12-20");
		 */
	}
	
	
	public void addContactDetails() throws Exception {
		Thread.sleep(2000);
		List<WebElement> menu = driver.findElements(By.xpath("//div[@class='orangehrm-tabs-wrapper']"));

		for (WebElement m : menu) {
			if (m.getText().equals("Contact Details")) {
				m.click();
			}
		}

		driver.findElement(By.xpath("//div[@class='oxd-date-input']")).sendKeys("2022-12-20");
	}

}

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
import org.openqa.selenium.interactions.Actions;
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

		String url = driver.getCurrentUrl();

		if (!(url.contains("/pim/viewPersonalDetails/empNumber"))) {
			Assert.assertTrue(false, "Employee Not Added Successfully");
			System.out.println("hello123");
			String error = driver
					.findElement(By.xpath("//input[@class='oxd-input oxd-input--active oxd-input--error']")).getText();
			System.out.println(error);
		}

	}

	public void openJobDetails() throws Exception {
		Thread.sleep(2000);
		List<WebElement> menu = driver.findElements(By.xpath("//a[contains(text(),'Job')]"));

		for (WebElement m : menu) {
			if (m.getText().equals("Job")) {
				m.click();
				break;
			}
		}
		Thread.sleep(5000);
	}

	public void addJobDetails(String title, String value) throws Exception {

		List<WebElement> input = driver.findElements(By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters']"));
		for (WebElement in : input) {

			if (in.getText().contains(title)) {
				in.click();
				Thread.sleep(2000);
				if (!title.contains("Joined Date")) {
					driver.findElement(By.xpath(
							"//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//*[contains(text(),'"
									+ value + "')]"))
							.click();
				} else {
					driver.switchTo().activeElement().sendKeys(value);
				}

				Thread.sleep(2000);
			}
		}

	}

	public void saveJobDetails() throws InterruptedException {
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2500);
		String msg = driver.findElement(By.xpath("//div[@class='oxd-toast-container oxd-toast-container--bottom']"))
				.getText();

		if (!msg.contains("Success"))
			Assert.assertTrue(false, "Job Details Update Not Successful");

		Thread.sleep(3000);

	}

	public void openContactDetails() throws Exception {
		Thread.sleep(2000);
		List<WebElement> menu = driver.findElements(By.xpath("//a[contains(text(),'Contact Details')]"));

		for (WebElement m : menu) {
			if (m.getText().equals("Contact Details")) {
				m.click();
			}
		}

		Thread.sleep(3000);
	}

	public void addContactDetails(String title, String value) throws InterruptedException {

		List<WebElement> input = driver.findElements(By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters']"));
		for (WebElement in : input) {

			if (in.getText().equals(title)) {
				in.click();
				driver.switchTo().activeElement().sendKeys(value);
			}
		}
		Thread.sleep(3000);
	}
	
	public void saveContactdetails() throws Exception {
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3500);
		String msg = driver.findElement(By.xpath("//div[@class='oxd-toast-container oxd-toast-container--bottom']"))
				.getText();

		if (!msg.contains("Success"))
			Assert.assertTrue(false, "Contact Details Update Not Successful");
		Thread.sleep(3000);
	}

}

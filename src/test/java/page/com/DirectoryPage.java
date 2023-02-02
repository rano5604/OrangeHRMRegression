package page.com;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DirectoryPage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public DirectoryPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	
	public void openDirectory() throws Exception {
		Thread.sleep(3000);
		List<WebElement> menu = driver.findElements(By.xpath("//a[@href]"));

		for (WebElement m : menu) {
			if (m.getText().contains("Directory")) {
				m.click();
				break;
			}
		}
		Thread.sleep(2002);
	}
	
	public void setSearchCriteria(String title, String value) throws Exception {

		List<WebElement> input = driver.findElements(By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters']"));
		for (WebElement in : input) {

			if (in.getText().contains(title)) {
				System.out.println("Inside tile:"+in.getText());
				in.click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@class='oxd-table-filter']//*[contains(text(),'"+ value + "')]")).click();
				break;
			}
		}
		Thread.sleep(2000);
	}
	
	public void performSearch() throws Exception {
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']")));
		String msg = driver.findElement(By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']")).getText();
		if(!msg.contains("Found")) {
			Assert.assertTrue(false, "Employee Not Found");
		}
		Thread.sleep(5000);
	}
	
	public void verifySeach(String name, String jobtitle, String location, String phone, String email) throws Exception {
		List<WebElement> searchresult = driver.findElements(By.xpath("//div[@class='oxd-sheet oxd-sheet--rounded oxd-sheet--white orangehrm-directory-card']"));
		for(WebElement s:searchresult) {
			if(s.getText().contains(name)) {
				s.click();
				break;
			}
		}
		Thread.sleep(2000);
		String employeename=driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-directory-card-header']")).getText();
		String employeedepartment=driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-directory-card-subtitle']")).getText();
		
		List<WebElement> contact=driver.findElements(By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--toast-title']"));
		
		if(!contact.get(0).getText().equals(phone)) {
				Assert.assertTrue(false, "Work Telephone Not Matched");
			}
		if(!contact.get(1).getText().equals(email)) {
			Assert.assertTrue(false, "Work Email Not Matched");
		}
		if(!employeename.equals(name)) {
			Assert.assertTrue(false, "Employee Name Not Matched");
		}
		if(!employeedepartment.equals(jobtitle)) {
			Assert.assertTrue(false, "Employee Department Not Matched");
		}
		
	}

}

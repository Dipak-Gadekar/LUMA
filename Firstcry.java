package Task;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Firstcry {
	public WebDriver driver;

	@Test
	public void SetUp() throws InterruptedException {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.firstcry.ae/");
		
		
		driver.findElement(By.partialLinkText("Login")).click();
		Thread.sleep(5000);
		driver.switchTo().frame(driver.findElement(By.id("main-con")));
		
		driver.findElement(By.id("loginPhoneFlag")).sendKeys("dipak");
	}
	
}

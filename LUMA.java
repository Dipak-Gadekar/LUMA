package Task;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import junit.framework.Assert;

public class LUMA {

	public WebDriver driver;

	@BeforeSuite
	public void SetUp() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.navigate().to("https://demo:accorin@demo.accorin.us/");
	}

	@Test
	public void Login() throws InterruptedException {
		// Click on Sign In button
		driver.findElement(By.linkText("Sign In")).click();

		// Enter User ID: Dipakgadekar45@gmail.com
		driver.findElement(By.id("email")).sendKeys("Gadekardipak45@gmail.com");

		// Enter Passwword: Dipak@2013
		driver.findElement(By.id("pass")).sendKeys("Dipak@2013");

		// Click on Sign IN button
		driver.findElement(By.xpath("//button[@class='action login primary']")).click();

		// MouseOver on Bag dropdown
		Actions action = new Actions(driver);
		WebElement gear = driver.findElement(By.id("ui-id-6"));
		action.moveToElement(gear).perform();
		driver.findElement(By.xpath("//*[text()='Bags']")).click();

		// click on any product name
		Thread.sleep(3000);
		String ProductName = driver.findElement(By.linkText("Push It Messenger Bag")).getText();

		driver.findElement(By.cssSelector(
				"span[class='product-image-container product-image-container-14'] img[alt='Push It Messenger Bag']"))
				.click();
		Thread.sleep(5000);

		// Verify Your product Name is same as your Product List Page (PLP)
		String PDP = driver.findElement(By.xpath("//span[contains(text(),'Push It Messenger Bag')]")).getText();
		Assert.assertEquals(ProductName, PDP);

		Thread.sleep(3000);

		// click on add to cart button
		driver.findElement(By.xpath("//button[@title='Add to Cart']")).click();
		Thread.sleep(5000);

		// Verify that after clicking on Add to cart button "You added Push It Messenger
		// Bag to your shopping cart." message is showing

		String ExpectedMsg = "You added Push It Messenger Bag to your shopping cart.";
		String ActualMsg = driver
				.findElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']"))
				.getText();
		Assert.assertEquals(ExpectedMsg, ActualMsg);

		// Click on cart button
		driver.findElement(By.xpath("//span[@class='counter qty']")).click();

		String PriceInCart = driver.findElement(By.xpath("//span[@class='price']")).getText();

		driver.findElement(By.id("top-cart-btn-checkout")).click();

		// Add youre Street Address 
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@name='street[0]']")).sendKeys("SG Highway");

		// select California State/Province from dropdown
		Select drop = new Select(driver.findElement(By.name("region_id")));
		drop.selectByVisibleText("California");

		// Enter your City Name
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Bell");

		// Enter your Zip/Postal Code
		driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys("90202");

		// Enter your telephone
		driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys("959583212");

		// Click on radio button
		driver.findElement(By.xpath("//input[@name='ko_unique_1']")).click();

		// Click on Next Button
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();

		// Verify the cart amount is same as user selected quentity in the cart
		String PriceInBill = driver.findElement(By.cssSelector("span[data-th='Cart Subtotal']")).getText();

		// Verify that product price in the cart and price at the bill page is same
		Assert.assertEquals(PriceInCart, PriceInBill);

		// Click on Place order button
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("button[title='Place Order']")).click();
		// driver.findElement(By.className("action primary checkout")).click();

		// Print order ID
		System.out.println("Thank you Dipak for your purchase! Your order No is : "
				+ driver.findElement(By.cssSelector("a[class='order-number'] strong")).getText());

	}

	@AfterSuite
	public void WindowClose() {

		// Close chrome window Tabs
		driver.quit();
	}

}

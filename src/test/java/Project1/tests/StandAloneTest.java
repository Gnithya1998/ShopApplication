package Project1.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
	
	public static void main(String args[]) throws InterruptedException {
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		//Login page
		driver.findElement(By.id("userEmail")).sendKeys("nikki@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Bashaa@1998");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		//Checking Products list
		WebElement prod = (WebElement) products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//Checking products in cart
	    List<WebElement> cartList = driver.findElements(By.cssSelector(".cartSection h3"));
	    Boolean match = cartList.stream().anyMatch(cart-> cart.getText().equalsIgnoreCase(productName));
	    Assert.assertTrue(match);
	    Actions act = new Actions(driver);
	    act.scrollToElement(driver.findElement(By.xpath("//span[text()='Total']"))).build().perform();
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@class,'btn btn-primary')])[2]"))).click();
	    
	    //Order confirmation
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Select Country']"))).click();
	    driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("india");
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));	 
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	    js.executeScript("arguments[0].click();", driver.findElement(By.xpath(("//button[contains(@class, 'ta-item')][2]"))));
	    js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".action__submit")));
	    String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
	    Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
		
	}
}

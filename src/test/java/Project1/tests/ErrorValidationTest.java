package Project1.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.output.BrokenWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Project1.TestComponents.BaseTest;
import Project1.TestComponents.Retry;
import Project1.pageObjectModel.CartPage;
import Project1.pageObjectModel.LoginPage;
import Project1.pageObjectModel.OrderConfirmationPage;
import Project1.pageObjectModel.ProductCatalouge;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationTest extends BaseTest{
	
	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void errorValidation() throws IOException, InterruptedException {
		
		
		loginPage.login("nikki@gmail.com", "Bashaa@1998123");
		Assert.assertEquals("Incorrect email or password.", loginPage.errorMessage());
		
		
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		
		String productName = "ZARA COAT 3";
		ProductCatalouge productCatalouge = loginPage.login("nikki123@gmail.com", "Bashaa@1998");
		List<WebElement> products = productCatalouge.listOfProducts();
		WebElement prod = productCatalouge.SearchProduct(productName);
		productCatalouge.clickOnAddButton(prod);
		CartPage cartPage = new CartPage(driver);
		cartPage.navigationToCart();
	    boolean match = cartPage.checkCartList("ZARA COAT 33");
	    Assert.assertFalse(match);
		
	}
}

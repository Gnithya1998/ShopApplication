package Project1.tests;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.BrokenWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.browsingcontext.NavigationResult;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Project1.TestComponents.BaseTest;
import Project1.pageObjectModel.CartPage;
import Project1.pageObjectModel.LoginPage;
import Project1.pageObjectModel.OrderConfirmationPage;
import Project1.pageObjectModel.OrderPage;
import Project1.pageObjectModel.ProductCatalouge;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{
	
	public String productName = "ZARA COAT 3";
	
	@Test(dataProvider = "getData", groups = {"purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		
		
		//Products catalogue
		ProductCatalouge productCatalouge = loginPage.login(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalouge.listOfProducts();
		WebElement prod = productCatalouge.SearchProduct(input.get("productName"));
		productCatalouge.clickOnAddButton(prod);
		CartPage cartPage = new CartPage(driver);
		cartPage.navigationToCart();
	    boolean match = cartPage.checkCartList(input.get("productName"));
	    Assert.assertTrue(match);
	    cartPage.navigationToTotal();
	    OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
	    orderConfirmationPage.selectCountry();
	    String confirmMsg = orderConfirmationPage.orderConfirmation();
	    Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	    System.out.println("Test run completed successfully");
		
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderDisplay() throws IOException, InterruptedException {
		
		//Products catalogue
		ProductCatalouge productCatalouge = loginPage.login("nikki@gmail.com", "Bashaa@1998");
		OrderPage orderPage = new OrderPage(driver);
		orderPage.navigationToOrder();
		Assert.assertTrue(orderPage.verifyOrderList(productName));
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
//		HashMap<String, String> map = new HashMap<String, String>(); 
//		map.put("email", "nikki@gmail.com");
//		map.put("password", "Bashaa@1998");
//		map.put("productName", "ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>(); 
//		map1.put("email", "nikki123@gmail.com");
//		map1.put("password", "Bashaa@1998");
//		map1.put("productName", "ADIDAS ORIGINAL");
		
		List<HashMap<String, String>> data = getDataToRead(System.getProperty("user.dir")+"\\src\\test\\java\\Project1\\dataProvider\\dataProvider.json");
		return new Object[][]{{data.get(0)},{data.get(1)}};
	}
	
//	@DataProvider
//	public Object[][] getData1(){
//		return new Object[][] {{"nikki@gmail.com", "Bashaa@1998", "ZARA COAT 3"},{"nikki123@gmail.com", "Bashaa@1998", "ADIDAS ORIGINAL"}};
//	}
		
}

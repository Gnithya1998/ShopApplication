package Project1.pageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Project1.AbstractComponets;

public class CartPage extends AbstractComponets{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='/dashboard/cart']")
	WebElement cartButtonElement;
	
	@FindBy(css = "[routerlink*='/myorders']")
	WebElement ordersButtoElement;
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartList;
	
	@FindBy(xpath = "//span[text()='Total']")
	WebElement totalElement;
	
	By btnPrimaryBy = By.xpath("(//button[contains(@class,'btn btn-primary')])[2]");
	
	public void navigationToCart() throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(ordersButtoElement).build().perform();
		Thread.sleep(5000);
		cartButtonElement.click();
	}
	
	public boolean checkCartList(String productName) {
		Boolean match = cartList.stream().anyMatch(cart-> cart.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public void navigationToTotal() {
		Actions act = new Actions(driver);
		act.moveToElement(totalElement).build().perform();
		waitForElementToClick(btnPrimaryBy);
	}

}

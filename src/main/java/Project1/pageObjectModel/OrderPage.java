package Project1.pageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Project1.AbstractComponets;

public class OrderPage extends AbstractComponets{
	
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='/dashboard/cart']")
	WebElement cartButtonElement;
	
	@FindBy(css = "[routerlink*='/myorders']")
	WebElement ordersButtonElement;
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderList;
	
	public void navigationToOrder() throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(ordersButtonElement).build().perform();
		Thread.sleep(5000);
		ordersButtonElement.click();
	}
	
	public boolean verifyOrderList(String productName) {
		Boolean match = orderList.stream().anyMatch(cart-> cart.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	

}

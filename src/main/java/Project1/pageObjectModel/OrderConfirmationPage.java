package Project1.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Project1.AbstractComponets;

public class OrderConfirmationPage extends AbstractComponets{
	
	WebDriver driver;
	
	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement countryElement;
	
	@FindBy(xpath = "//button[contains(@class, 'ta-item')][2]")
	WebElement taButtonElement;
	
	@FindBy(css = ".action__submit")
	WebElement orderElement;
	
	@FindBy(css = ".hero-primary")
	WebElement msgElement;
	
	By placeHolderBy = By.xpath("//input[@placeholder='Select Country']");
	By taResultBy = By.cssSelector(".ta-results");
	
	public void selectCountry() {
		waitForPresenceOfElement(placeHolderBy);
		countryElement.sendKeys("india");
		waitElementToAppear(taResultBy);	
	}
	
	public String orderConfirmation() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", taButtonElement);
		js.executeScript("arguments[0].click();", orderElement);
		String msgString = msgElement.getText();
		return msgString;
	}

}

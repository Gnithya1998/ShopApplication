package Project1.pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Project1.AbstractComponets;


public class LoginPage extends AbstractComponets {
	
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id = "userPassword")
	WebElement userPassword;
	
	@FindBy(id = "login")
	WebElement loginbtn;
	
	@FindBy(css= "[class*='flyInOut']")
	WebElement errorMsg;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalouge login(String username, String password) {
		userEmail.sendKeys(username);
		userPassword.sendKeys(password);
		loginbtn.click();
		ProductCatalouge productCatalouge = new ProductCatalouge(driver);
		return productCatalouge;
	}
	
	public String errorMessage() {
		waitElementToApprear(errorMsg);
		return errorMsg.getText();
	}
	
	
	
	
	

}

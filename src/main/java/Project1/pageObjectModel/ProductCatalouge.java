package Project1.pageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Project1.AbstractComponets;

public class ProductCatalouge extends AbstractComponets{

	WebDriver driver;
	
	public ProductCatalouge(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addButtonBy = By.cssSelector(".card-body button:last-of-type");
	By toasterBy = By.cssSelector("#toast-container");
	
	
	public List<WebElement> listOfProducts() {
		waitElementToAppear(productsBy);
		return products;
	}
	
	public WebElement SearchProduct(String productName) {
		WebElement prod = (WebElement) products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void clickOnAddButton(WebElement prod) {
		prod.findElement(addButtonBy).click();
		waitElementToAppear(toasterBy);
		//waitElementToInvisible(spinner);
	}
	
	
	

}

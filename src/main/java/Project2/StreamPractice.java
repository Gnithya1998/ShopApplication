package Project2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.dockerjava.api.model.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StreamPractice {
	
	//@Test
	public void testStream() {
		
		ArrayList<String> names = new ArrayList<>();
		names.add("Nikki");
		names.add("Bambam");
		names.add("Bashaa");
		names.add("Velu");
		names.add("Adam");
		
		long count = names.stream().filter(s->s.contains("aa")).count();
		System.out.println(count);
	}
	
	WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
	}
	
	@Test
	public void practice1() {
		driver.findElement(By.xpath("//tr/th[1]")).click();
		List<WebElement> veggies = driver.findElements(By.xpath("//tr/td[1]"));
		long count = veggies.stream().filter(s->s.getText().equalsIgnoreCase("Beans")).count();
		System.out.println("Beans count " + count);
		driver.close();
	}
}

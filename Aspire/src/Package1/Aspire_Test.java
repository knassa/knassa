package Package1;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Keys;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Aspire_Test 
{	
	private WebDriver driver;
	public String partid ="Test_Product_xyz";
	
	
	@BeforeTest
	  public void beforeMethod() 
	  {		  
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		  String baseUrl = "https://aspireapp.odoo.com/";
		  driver.get(baseUrl);	
		  String expectedPageTitle = "Odoo";
		  Assert.assertTrue(driver.getTitle().contains(expectedPageTitle), "Test Failed");
		  
	  }	
  @Test
  public void Login() 
  {
	  driver.findElement(By.xpath("//input[@id=\'login\']")).sendKeys("user@aspireapp.com");
	  driver.findElement(By.xpath("//input[@id='password']")).sendKeys("@sp1r3app");
	  driver.findElement(By.className("btn-block")).click();
	  driver.manage().window().maximize();
	  
  }
  

  @Test
  public void create_new_product() throws InterruptedException
  {		
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//div[contains(text(),'Inventory')]")).click();
	  driver.findElement(By.xpath("//a[contains(text(),'Products')]")).click();
	  driver.findElement(By.xpath("//span[contains(text(),\'Product\')]")).click();
	  driver.findElement(By.xpath("//button[contains(text(),'Create')]")).click();
	  driver.findElement(By.name("name")).sendKeys(partid);
	  //driver.findElement(By.xpath("//span[contains(text(),'123')]")).click();
	  driver.findElement(By.xpath("//span[contains(text(),'Update Quantity')]")).click(); 
	  driver.findElement(By.className("o_list_button_add")).click();
	  driver.findElement(By.xpath("//tbody/tr[1]/td[2]/div[1]/div[1]/input[1]")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//tbody/tr[1]/td[2]/div[1]/div[1]/input[1]")).sendKeys("Partner Locations/HN",Keys.TAB,Keys.TAB,Keys.TAB,"100");	 
	  
	   driver.findElement(By.xpath("//button[contains(text(),'\n"
	   		+ "            Save\n"
	   		+ "        ')]")).click();
	   
	   //Navigating to manufacturing page and creating a new order.
	  driver.findElement(By.xpath("/html/body/header/nav/a[1]")).click();  
	  driver.findElement(By.xpath("//div[contains(text(),'Manufacturing')]")).click();
	  driver.findElement(By.xpath("//button[contains(text(),'Create')]")).click(); // click create under manufacturing
	  
	  List <WebElement> lst = driver.findElements(By.xpath("//input[starts-with(@id,\'o_field_input_\')]"));
	  lst.get(0).sendKeys(partid);
	  Thread.sleep(2000);
	  lst.get(0).sendKeys(Keys.TAB);	  
	  Thread.sleep(2000);
	  driver.findElement(By.linkText("Add a line")).click();
	  driver.findElement(By.xpath("//tbody/tr[1]/td[1]/div[1]/div[1]/input[1]")).sendKeys(partid);
	  Thread.sleep(4000);
	  driver.findElement(By.xpath("//tbody/tr[1]/td[1]/div[1]/div[1]/input[1]")).sendKeys(Keys.TAB , Keys.TAB,"14");
	  driver.findElement(By.xpath("//span[contains(text(),'Confirm')]")).click();
	  Thread.sleep(4000);
	  driver.findElement(By.xpath("//span[contains(text(),'Mark as Done')]")).click();
	  WebElement Apply_button = driver.findElement(By.xpath("//span[contains(text(),'Apply')]"));
	  Apply_button.click();
	  WebElement bb = driver.findElement(By.xpath("//button[contains(text(),'\n"
		   		+ "            Save\n"
		   		+ "        ')]"));
	  bb.click();
  }
  
  @AfterMethod
  public void endSession() 
  {	
	  driver.findElement(By.xpath("//span[contains(text(),'user')]")).click();
	  driver.findElement(By.xpath("//a[contains(text(),'Log out')]")).click();
	  driver.quit();
  }

}

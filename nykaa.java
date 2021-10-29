package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class nykaa {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//1) Go to https://www.nykaa.com/
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.nykaa.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(2000);
		driver.manage().window().maximize();
		WebElement brand = driver.findElement(By.xpath("//*[@id=\"headerMenu\"]/div[1]/ul[2]/li/a"));
		//2) Mouseover on Brands and Search L'Oreal Paris
		Actions builder = new Actions(driver);
		builder.moveToElement(brand).perform();
		driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");
		driver.findElement(By.id("brandSearchBox")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		//3) Click L'Oreal Paris
		driver.findElement(By.xpath("//div[@id='list_L']/following::a")).click();
		Thread.sleep(2000);
		String title = driver.getTitle();
		//4) Click sort By and select customer top rated
		driver.findElement(By.xpath("//span[@class='sort-name']")).click();

		driver.findElement(By.xpath("(//div[@class='control-value'])[4]/span")).click();
		driver.findElement(By.xpath("//div[@class='css-xdicx1']")).click();
		Thread.sleep(2000);
		//5) Check the title contains L'Oreal Paris(Hint-GetTitle)
		if(title.contains("L'Oreal"))
		{
			System.out.println("Title Contains L'Oreal Paris");
		}
		else
			System.out.println("Brand is not L'Oreal Paris");
		//6) Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("//ul[@id='custom-scroll']//li/div//span")).click();
		driver.findElement(By.xpath("//ul[@id='custom-scroll']//li/ul/li/div/span")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		String shampoo = driver.findElement(By.xpath("//span[@class='filter-value']")).getText();
		//7)check whether the Filter is applied with Shampoo
	    if(shampoo.contains("Shampoo"))
	    {
	    	System.out.println("Shampoo filter is applied");
	    }
	  //7)check whether the Filter is applied with Shampoo
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Color')]")).click();
		//8)Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("//div[@class='css-10zjw4o']")).click();
		//9) GO to the new window and select size as 175ml
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> myList = new ArrayList<String>(windowHandles);
		WebDriver window = driver.switchTo().window(myList.get(1));
		Thread.sleep(2000);
		//10)print MRP of the product
		String mrp = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[2]/div/div[1]/span[2]")).getText();
		System.out.println("MRP of the item is"+mrp);
		//11) Click on ADD to BAG
		driver.findElement(By.xpath("//span[@class='btn-text']")).click();
		//12) Go to Shopping Bag 
		driver.findElement(By.xpath("//*[@id=\"header_id\"]/div[2]/div/div[2]/div[2]/button")).click();
		driver.switchTo().frame(0);
		//13) Print the Grand Total amount
	
		String text = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText();
		String substring = text.substring(1, 4);
		System.out.println("Grand Total on adding to cart:"+substring);
		//15) Click Proceed
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		//16) Click on Continue as Guest
		driver.findElement(By.xpath(
				"//*[@id=\"app\"]/div/div/div[1]/div/div[2]/div/div/div/div[2]/div/div[2]/div/div/div/div/div[4]/button"))
				.click();
		String text2 = driver.findElement(By.xpath("(//div[@class='name'])[3]/following::span")).getText();
		System.out.println("Grand Total after clicking Checkout"+text2);
		//17)Check if this grand total is the same in step 14
		if (substring.equals(text2)) {
			System.out.println("Prices are equal");
		}
		else
		{
			System.out.println("Prices are not equal");
		}
		//18) Close all windows
		driver.quit();
	}

}

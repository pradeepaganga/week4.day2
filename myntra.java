package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class myntra {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
//1) Open https://www.myntra.com/
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.myntra.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		String text4 = "";
		//2) Mouse hover on MeN 
		WebElement men = driver.findElement(By.xpath("//*[@id=\"desktop-header-cnt\"]/div[2]/nav/div/div[1]/div/a"));
		Actions builder = new Actions(driver);
		builder.moveToElement(men).perform();
		Thread.sleep(2000);
		//3) Click Jackets 
		driver.findElement(By.xpath("(//a[@class='desktop-categoryLink'])[6]")).click();
		//4) Find the total count of item 
		String text = driver.findElement(By.xpath("//span[@class='title-count']")).getText();
		String substring = text.substring(3, 7);
		System.out.println("Total Count of the Item"+substring);

		String text2 = driver.findElement(By.xpath("//span[@class='categories-num']")).getText();

		String text3 = driver.findElement(By.xpath("(//span[@class='categories-num'])[2]")).getText();

		String sub1 = text2.substring(1, 5);
		System.out.println("first item" + sub1);
		String sub2 = text3.substring(1, 3);
		System.out.println("second item" + sub2);
		//5) Validate the sum of categories count matches
		int n1 = Integer.parseInt(sub1);
		int n2 = Integer.parseInt(sub2);
		int total = Integer.parseInt(substring);
		if ((n1 + n2) == total) {
			System.out.println("Total is same");
		} else
			System.out.println("Total is not same");
		//6) Check jackets

		driver.findElement(By.xpath("//div[@class='common-checkboxIndicator']")).click();
		//7) Click + More option under BRAND
		driver.findElement(By.xpath("//div[@class='brand-more']")).click();
		//8) Type Duke and click checkbox
		driver.findElement(By.xpath("//input[@class='FilterDirectory-searchInput']")).sendKeys("Duke");
		//9) Close the pop-up x
		driver.findElement(By.xpath(
				"//*[@id=\"mountRoot\"]/div/div[1]/main/div[3]/div[1]/section/div/div[3]/div[3]/div[2]/ul/li[2]/label/div"))
				.click();
		driver.findElement(By.xpath("//ul[@class='FilterDirectory-indices']/following::span")).click();
		//10) Confirm all the Coats are of brand Duke
		List<WebElement> list = driver.findElements(By.xpath("//h3[text()='Duke']"));
		for (int i = 0; i < list.size(); i++) {

			text4 = list.get(i).getText();

		}
		if (text4.equals("Duke")) {
			System.out.println("All the brands are Duke ");
		} else {
			System.out.println("All the brands are not Duke");
		}
        //11) Sort by Better Discount
		driver.findElement(By.xpath("//div[@class='sort-sortBy']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class='myntraweb-sprite sort-downArrow sprites-downArrow']")).click();

		driver.findElement(
				By.xpath("//*[@id=\"desktopSearchResults\"]/div[1]/section/div[1]/div[1]/div/div/div/ul/li[3]/label"))
				.click();
		//12) Find the price of first displayed item
		String text5 = driver.findElement(By.xpath("//span[@class='product-discountedPrice']")).getText();
		System.out.println("Price of First Listed Item is"+ text5);
		//13) Click on the first product
		driver.findElement(By.xpath("//div[@class='product-sliderContainer']")).click();

		Set<String> windowHandles = driver.getWindowHandles();
		List<String> myList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(myList.get(1));
		WebElement snapShot = driver.findElement(By.xpath("//div[@class='image-grid-container common-clearfix']"));
		//14) Take a screen shot
		File src = snapShot.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snapShot/pic1.png");
		FileUtils.copyFile(src, dst);
		driver.switchTo().window(myList.get(0));
		Thread.sleep(3000);
		//15) Click on WishList Now
		driver.findElement(By.xpath("//span[text()='Wishlist']")).click();
		//16) Close Browser
		driver.close();

	}

}

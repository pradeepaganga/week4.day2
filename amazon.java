package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAreaChart;

import io.github.bonigarcia.wdm.WebDriverManager;

public class amazon {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		// 1.Load the uRL https://www.amazon.in/
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		// 2.search as oneplus 9 pro
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro" + Keys.ENTER);
		// 3.Get the price of the first product
		String text = driver.findElement(By.xpath("//span[@class='a-price-whole']")).getText();
		System.out.println("Price of first product is" + text);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		List<WebElement> list = driver
				.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
		list.get(0);

		driver.findElement(By.xpath("//i[@class='a-icon a-icon-star-small a-star-small-4 aok-align-bottom']")).click();
		Thread.sleep(2000);
		// 4. Print the number of customer ratings for the first displayed product
		String text4 = driver.findElement(By.xpath("//*[@id=\"a-popover-content-2\"]/div/div/div/div[1]/span"))
				.getText();

		System.out.println("Customer rating of the product is" + text4);

		// 6. Get the percentage of ratings for the 5 star.
		String text5 = driver.findElement(By.xpath("(//span[@class='a-size-base']/a)[2]")).getText();
		System.out.println("Percentage of 5 star rating is" + text5);
		// 7. Click the first text link of the first image
		String text2 = driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"))
				.getText();
		System.out.println("First Text Link of first image is" + text2);
		WebElement screenShot = driver
				.findElement(By.xpath("//div[@class='a-section aok-relative s-image-fixed-height']/img"));
		// 8. Take a screen shot of the product displayed
		File src = screenShot.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/amazon.png");
		FileUtils.copyFile(src, dst);
		// 9. Click 'Add to Cart' button
		driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']")).click();
		Thread.sleep(2000);
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> myList = new ArrayList<String>(windowHandles);
		WebDriver window = driver.switchTo().window(myList.get(1));
		// 10. Get the cart subtotal and verify if it is correct.

		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();

		String text3 = driver.findElement(By.xpath("//span[@id='attach-accessory-cart-subtotal']")).getText();
		System.out.println(text3);

		if (text.contains(text3)) {
			System.out.println("Price and cart subtotal is same");

		} else
			System.out.println("not equal");

	}

}

package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class snapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		// 1. Launch https://www.snapdeal.com/
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.snapdeal.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		// 2. Go to Mens Fashion
		// 3. Go to Sports Shoes
		WebElement men = driver.findElement(By.xpath("(//span[@class='catText'])[6]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(men).perform();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement findElement = driver.findElement(By.xpath("//*[@id=\"category6Data\"]/div[1]/div/div/p[2]/a/span"));
		wait.until(ExpectedConditions.elementToBeClickable(findElement));
		findElement.click();
		// 4. Get the count of the sports shoes
		String shoeCount = driver.findElement(By.xpath("//div[@class='child-cat-count ']")).getText();
		System.out.println("Count of sports shoes is" + shoeCount);
		// 5. Click Training shoes
		driver.findElement(By.xpath("(//div[@class='child-cat-name '])[2]")).click();
		// 6. Sort by Low to High
		driver.findElement(By.xpath("//div[@class='sort-selected']")).click();
		driver.findElement(By.xpath("//li[@class='search-li']")).click();
		driver.navigate().refresh();
		// 7. Check if the items displayed are sorted correctly
		List<WebElement> price = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));

		List<String> priceList = new ArrayList<String>();

		for (int i = 0; i < price.size(); i++) {
			WebElement webElement = price.get(i);
			String text2 = webElement.getText();
			System.out.println(text2);
			priceList.add(text2);

		}
		Collections.sort(priceList);

		ArrayList<String> sortedList = new ArrayList<String>();
		for (String s : priceList) {
			sortedList.add(s);
		}
		if (sortedList.equals(priceList)) {
			System.out.println("Items are sorted as expected");
		}
		// 8.Select the price range (900-1200)

		driver.findElement(By.xpath("//input[@name='fromVal']")).clear();
		driver.findElement(By.xpath("//input[@name='fromVal']")).sendKeys("900");
		driver.findElement(By.xpath("//input[@name='toVal']")).clear();
		driver.findElement(By.xpath("//input[@name='toVal']")).sendKeys("1200");
		driver.findElement(By.xpath("//div[@class='filter-content']//div[5]")).click();
		// 9.Filter with color Navy

		driver.findElement(By.xpath("//div[@class='sdCheckbox filters-list ']/label")).click();
		// 10 verify the all applied filters
		boolean displayed = driver.findElement(By.xpath("//a[@data-key='Price|Price']")).isEnabled();

		boolean displayed2 = driver.findElement(By.xpath("//a[@data-key='Color_s|Color']")).isEnabled();

		if (displayed == displayed2) {
			System.out.println("Filters applied as expected");
		}

		driver.navigate().refresh();
		// 11. Mouse Hover on first resulting Training shoes

		builder.moveToElement(driver.findElement(By.xpath("//p[@class='product-title']"))).perform();
		// 12. click QuickView button

		driver.findElement(By.xpath("//div[@class='clearfix row-disc']/div")).click();
		// 13. Print the cost and the discount percentage

		String text2 = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();

		System.out.println(text2);
		String text3 = driver.findElement(By.xpath("//span[@class='payBlkBig']/following::span")).getText();
		System.out.println("Discount of the First Displayed product is" + text3);
		// 14. Take the snapshot of the shoes.
		WebElement snap = driver.findElement(By.xpath("//*[@id=\"bx-slider-qv-image-panel\"]/li[1]/img"));
		File src = snap.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/snapDeal.png");
		FileUtils.copyFile(src, dst);
	

	}
}

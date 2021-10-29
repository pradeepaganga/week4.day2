package week4.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sortable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://jqueryui.com/sortable");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions builder=new Actions(driver);
		driver.switchTo().frame(0);
		WebElement itemNew1 = driver.findElement(By.xpath("//li[text()='Item 1']"));
		WebElement itemNew5 = driver.findElement(By.xpath("//li[text()='Item 5']"));
		Point location = itemNew5.getLocation();
		int x=location.getX();
		int y=location.getY();
		builder.dragAndDropBy(itemNew1, x, y).perform();
	}

}

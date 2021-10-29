package week4.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class draggable {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://jqueryui.com/draggable");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Draggable
		driver.switchTo().frame(0);
		WebElement draggable = driver.findElement(By.id("draggable"));
		Actions builder=new Actions(driver);
		builder.dragAndDropBy(draggable, 200,200).perform();
		driver.switchTo().defaultContent();
		
		}

}

package com.revature.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:/webdrivers/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://localhost:8080");
		
		WebElement addInput1 = driver.findElement(By.id("addNum1"));
		WebElement addInput2 = driver.findElement(By.id("addNum2"));
		WebElement addBut = driver.findElement(By.id("addBut"));
		
		addInput1.sendKeys("10.0");
		addInput2.sendKeys("26.8");
		addBut.click();
		
		driver.switchTo().frame("result");
		WebElement addOutput = driver.findElement(By.tagName("pre"));
		System.out.println("The result of adding:" + addOutput.getText());
		
		driver.switchTo().parentFrame();
		
		WebElement subInput1 = driver.findElement(By.id("subNum1"));
		WebElement subInput2 = driver.findElement(By.id("subNum2"));
		WebElement subBut = driver.findElement(By.id("subBut"));
		
		subInput1.sendKeys("36.8");
		subInput2.sendKeys("24.8");
		subBut.click();
		
		driver.switchTo().frame("result1");
		WebElement subOutput = driver.findElement(By.tagName("pre"));
		System.out.println("The result of subtracting:" + subOutput.getText());
		
		driver.switchTo().parentFrame();
			
		WebElement mulInput1 = driver.findElement(By.id("mulNum1"));
		WebElement mulInput2 = driver.findElement(By.id("mulNum2"));
		WebElement mulBut = driver.findElement(By.id("mulBut"));
		
		mulInput1.sendKeys("12");
		mulInput2.sendKeys("12");
		mulBut.click();
		
		driver.switchTo().frame("result2");
		WebElement mulOutput = driver.findElement(By.tagName("pre"));
		System.out.println("The result of subtracting:" + mulOutput.getText());
		
		driver.switchTo().parentFrame();
		
		WebElement divInput1 = driver.findElement(By.id("divNum1"));
		WebElement divInput2 = driver.findElement(By.id("divNum2"));
		WebElement divBut = driver.findElement(By.id("divBut"));
		
		divInput1.sendKeys("144");
		divInput2.sendKeys("6");
		divBut.click();
		
		driver.switchTo().frame("result3");
		WebElement divOutput = driver.findElement(By.tagName("pre"));
		System.out.println("The result of subtracting:" + divOutput.getText());
		
	
		
		Thread.sleep(7000);
		driver.quit();
		

	}

}

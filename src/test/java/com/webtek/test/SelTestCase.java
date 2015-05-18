package com.webtek.test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.annotations.*;

public class SelTestCase {
	
	public WebDriver driver;
	
	@BeforeClass
	public void setUp(){
		driver =  new FirefoxDriver();
		driver.get("http://automationpractice.com/index.php");
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public void tearDown(){
		
		//Capture the screen once test fail
		captureScreen();
		
		//Closing the browser instance
		driver.close();
		driver.quit();
	}
	
	public String captureScreen() {

		String path;
		try {
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File source = ((TakesScreenshot) augmentedDriver)
					.getScreenshotAs(OutputType.FILE);
			path = "./target/screenshots/" + source.getName();
			FileUtils.copyFile(source, new File(path));
		} catch (IOException e) {
			path = "Failed to capture screenshot: " + e.getMessage();
		}
		return path;

	}

}

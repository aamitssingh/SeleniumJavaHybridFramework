package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/*
	 * why we need ThreadLocal :: 
	 * 								--it is used is parallel execution of the TCs.
	 * 								--suppose 5 TCs are running parallely, if 1st TC is using driver, then other TCs will have to wait for the driver
	 * 								--To OverCome with this situation we use ThreadLocal.
	 *ThreadLocal has two method get() and Set() 
	 */
	
	public WebDriver initDriver(Properties prop)
	{
		String highlight = prop.getProperty("highlight").trim();
		String browser = prop.getProperty("browser").trim();
		System.out.println("Current browser = "+browser);
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver());
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.chromedriver().setup();
			//driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());
		}
		else
		{
			System.out.println("Please pass the correct browser.." +browser);
		}
		
		getDriver().manage().window().maximize();
		//driver.manage().deleteAllCookies();
		//driver.get("https://demo.opencart.com/index.php?route=account/login");
		getDriver().get(prop.getProperty("url").trim());
		
		return getDriver();
	}
	
	/*
	 * This method will return Thread Local Copy of the driver.
	 * synchronized used :: suppose 5 TCs are executing parallely and all are using same copy of driver, so all should be in sync.
	 */
	public synchronized WebDriver getDriver()
	{
		return tlDriver.get(); 
	}
	
	public Properties initProp()
	{
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
	/*
	 * Take Screen Shot:
	 */
	
	public String getScreenshot()
	{
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
		
	}

}

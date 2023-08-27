package com.practice;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;


public class CustomListener extends extentReporttest {
	
	static String format;
	
	public static String m1(String name) throws IOException {
		
		Date date = new Date();
		SimpleDateFormat tm = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
		format = tm.format(date);
		System.out.println(format);
		
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path="C:\\Users\\biswa\\Desktop\\New folder (4)\\New folder\\junitTest\\screenshot\\" + "image_" + format + ".png";
		
		Files.copy(screenshot, new File(path));
		
		System.out.println("biswajit sahoo");
		return path;
		
	}
	
	@FindBy (id = "twotabsearchtextbox")
	private WebElement moveituser;
	
	public void clickElement() {
		moveituser.click();
	}
	
	public  void SendKey() {
		moveituser.sendKeys("iphone 13");
	}
	
	@FindBy (xpath = "//*[@value='Go']")
	private WebElement Ckicksearchbutton;
	
	public void SearchButton() {
		Ckicksearchbutton.click();
	}
	
	
	
	
	
}

package com.practice;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

public class extentReporttest {

	public static WebDriver driver;
	public ExtentHtmlReporter htmlReport;
	public ExtentReports extent;
	public static ExtentTest test;
	

	static CustomListener jj = new CustomListener();

	@BeforeTest
	public void setExtent() {
		htmlReport = new ExtentHtmlReporter("Report.html");
		htmlReport.config().setDocumentTitle("Automation Report");// Title of the report
		htmlReport.config().setReportName("Functional Report");// Name of the report
		htmlReport.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(htmlReport);
		extent.setSystemInfo("Hostname", "LocalHost");
		extent.setSystemInfo("OS", "Window11");
		extent.setSystemInfo("Tester Name", "Biswajit");
		extent.setSystemInfo("Browser", "Chrome");
	}

	@AfterTest
	public void endReport() {
		extent.flush();
	}

	@BeforeMethod
	public void setUp() {
		

		System.setProperty("webdriver.chrome.driver", "C:\\browserdrivers\\chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
	}

	@Test
	public void test1() throws InterruptedException, IOException {

		test = extent.createTest("To Verify The Functionalty of sign in button");
		try {

			driver.findElement(By.id("twotabsearchtextbox")).click();
			driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone 13");
			WebElement search = driver.findElement(By.xpath("//*[@value='Go']"));
			boolean actual = search.isDisplayed();
			boolean excepted = true;
			try {

				if (actual == excepted) {
					test.log(Status.INFO, MarkupHelper.createLabel("search box is display", ExtentColor.GREEN));

					test.log(Status.PASS, "", MediaEntityBuilder
							.createScreenCaptureFromPath(jj.m1("search box is display")).build());
				}

			} catch (Exception e) {

				test.log(Status.INFO, MarkupHelper.createLabel("search box is not display", ExtentColor.RED));

				test.log(Status.PASS, "",
						MediaEntityBuilder.createScreenCaptureFromPath(jj.m1("search box is not display")).build());

			}

			search.click();

		} catch (Exception e) {
			e.printStackTrace();
		}

		Thread.sleep(3000);
		try {

			WebElement phone = driver.findElement(By.xpath("//*[text()='Apple iPhone 13 (128GB) - Midnight']"));
			boolean actual1 = phone.isDisplayed();
			boolean excepted1 = true;

			

				if (actual1 == excepted1) {
					test.log(Status.INFO, MarkupHelper.createLabel("phone is  display", ExtentColor.GREEN));

					test.log(Status.PASS, "", MediaEntityBuilder
							.createScreenCaptureFromPath(jj.m1("phone is  display")).build());

				}

			

				

			phone.click();

		} catch (Exception e) {
			test.log(Status.INFO, MarkupHelper.createLabel("phone is not display", ExtentColor.RED));

			test.log(Status.PASS, "",
					MediaEntityBuilder.createScreenCaptureFromPath(jj.m1("phone is not display")).build());
		}

	}

	@AfterMethod
	public void browserquit() {
		driver.quit();
	}

//	public static void screenshot(String status) {
//		try {
//
//			// creating a new object of the class Date
//			Date date = new Date();
//			SimpleDateFormat tm = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
//			format = tm.format(date);
//			System.out.println(format);
//
//			if (status.equalsIgnoreCase("pass")) {
////				File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
////				Files.copy(screenshot, new File(
////						"C:\\Users\\biswa\\Desktop\\New folder (4)\\New folder\\junitTest\\screenshot\\" + "image_" + format + ".png"));
//
//				test.log(Status.INFO, MarkupHelper.createLabel("search key click properly", ExtentColor.GREEN));
//
//				test.log(Status.PASS, "",
//						MediaEntityBuilder.createScreenCaptureFromPath(jj.m1("search key  click properly")).build());
////				C:\\Users\\biswa\\Desktop\\New folder (4)\\New folder\\junitTest\\screenshot\\
//
//			} else {
////				File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
////				Files.copy(screenshot, new File(
////						"C:\\Users\\biswa\\Desktop\\New folder (4)\\New folder\\junitTest\\screenshot\\" + "image_" + format + ".png"));
//
//				test.log(Status.INFO, MarkupHelper.createLabel("search key not click properly", ExtentColor.RED));
//
//				test.log(Status.FAIL, "",
//						MediaEntityBuilder.createScreenCaptureFromPath(jj.m1("search key not click properly")).build());
//			}
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//	}

}

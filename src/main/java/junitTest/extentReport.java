package junitTest;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.io.Files;

public class extentReport {

	public static void main(String[] args) throws IOException {
		
		File file=new File("Report8.html");
		ExtentHtmlReporter htmlReport=new ExtentHtmlReporter(file);
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(htmlReport);
		
		ExtentTest ex=extent.createTest("this is login page","I always with you");
		ex.log(Status.INFO, "login to amazon");
		ex.log(Status.PASS, "Title is verified");
		ex.pass("this is good for the health");
		ex.fail("THis is not for the health");
		ex.log(Status.ERROR, "Sorry!!! it is not good");
		ex.pass("this is good for the health");
		ex.fail("THis is not for the health");
		ex.log(Status.ERROR, "Sorry!!! it is not good");
		ex.pass("this is good for the health");
		ex.fail("THis is not for the health");
		ex.log(Status.ERROR, "Sorry!!! it is not good");
				 
		//selenium
		
		System.setProperty("webdriver.chrome.driver", "C:\\browserdrivers\\chromedriver.exe");
		ChromeOptions option=new ChromeOptions();
		 option.addArguments("--remote-allow-origins=*");
		WebDriver driver=new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.get("https://www.browserstack.com/guide/extent-reports-in-selenium");
		
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        
		Files.copy(screenshot, new File("C:\\Users\\biswa\\Desktop\\New folder (4)\\New folder\\junitTest\\homePageScreenshot.png"));
		ex.log(Status.INFO, "Details of the Screenshot",MediaEntityBuilder.createScreenCaptureFromPath("C:\\Users\\biswa\\Desktop\\New folder (4)\\New folder\\junitTest\\homePageScreenshot.png").build());
		extent.flush();

	}

}

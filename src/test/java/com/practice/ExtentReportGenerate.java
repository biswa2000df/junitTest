package com.practice;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.mongodb.gridfs.CLI;

public class ExtentReportGenerate {
	
	public WebDriver driver;
	public ExtentHtmlReporter htmlReport;
	public ExtentReports extent;
	public ExtentTest test;
	static FileReader f;
	static Properties prop;
	
	@BeforeClass
	public static void readConfigProperties() throws IOException {
		 f=new FileReader("C:\\Users\\biswa\\Desktop\\New folder (4)\\New folder\\junitTest\\configure properties");
		prop=new Properties();
		prop.load(f);
		System.out.println(prop.getProperty("url"));
		
	}
	
	@BeforeTest
	public void setExtent()
	{
		htmlReport=new ExtentHtmlReporter("report1.html");		
		htmlReport.config().setDocumentTitle("Automation Report");//Title of the report
		htmlReport.config().setReportName("Functional Report");//Name of the report
		htmlReport.config().setTheme(Theme.DARK);
		extent=new ExtentReports();
		extent.attachReporter(htmlReport);		
		extent.setSystemInfo("Hostname", "LocalHost");
		extent.setSystemInfo("OS", "Window11");
		extent.setSystemInfo("Tester Name", "Biswajit");
		extent.setSystemInfo("Browser", "Chrome");
		
		
		
	}
	
	@AfterTest
	public void endReport()
	{
		extent.flush();
	} 
	
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\browserdrivers\\chromedriver.exe");
		ChromeOptions option=new ChromeOptions();
		 option.addArguments("--remote-allow-origins=*");
		 driver=new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
//		driver.get("http://192.168.21.175:8081/employeeportal/#/login");
		
	}
	
	@Test
	public void Testcase1() throws InterruptedException
	{
		
		test=extent.createTest("To Verify The Functionalty of sign in button");
		WebElement ele=driver.findElement(By.xpath("/html/body/app-root/app-body/div/app-login/main/div/div/div/div[2]/button"));
		Boolean b=ele.isDisplayed();
//		
//		String title=driver.getTitle();
//		System.out.println(title);
//		Assert.assertEquals(title,"YouTube");
		Assert.assertTrue(b);

		///login page
		
		
		
		test=extent.createTest("login page");
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys(prop.getProperty("username"));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='userPassword']")).sendKeys(prop.getProperty("password"));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[text()='SIGN IN']")).click();
		
		test=extent.createTest("To Verify The Validation of OTP box");
		Thread.sleep(3000);
		WebElement otp=driver.findElement(By.xpath("//*[@id=\"userOtp\"]"));
		Boolean b1=otp.isDisplayed();
		Assert.assertTrue(b1);
		Thread.sleep(3000);
		test=extent.createTest("OTP Enter");
		driver.findElement(By.xpath("//*[@id=\"userOtp\"]")).sendKeys(prop.getProperty("otp"));	
		Thread.sleep(3000);	
		driver.findElement(By.xpath("//*[text()='Confirm']")).click();
		
		Thread.sleep(5000);
		
		test=extent.createTest("Setting icon check");
		WebElement seticon=driver.findElement(By.xpath("/html/body/app-root/app-sidenav/div/ul/li[2]/a"));
		Boolean b2=seticon.isDisplayed();
		Assert.assertTrue(b2);	
		driver.findElement(By.xpath("/html/body/app-root/app-sidenav/div/ul/li[2]/a")).click();
		Thread.sleep(3000);
		
		test=extent.createTest("Add new Employee");
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-employee-config/div[2]/div/div[2]/div[1]/div/app-accordion/div/app-accordion-item[1]/div[2]/div/div/div/div[1]/div/div/input")).click();
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-employee-config/div[2]/div/div[2]/div[1]/div/app-accordion/div/app-accordion-item[1]/div[2]/div/div/div/div[1]/div/div/input")).sendKeys(prop.getProperty("EmpId"));
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-employee-config/div[2]/div/div[2]/div[1]/div/app-accordion/div/app-accordion-item[1]/div[2]/div/div/div/div[2]/div/div/input")).sendKeys(prop.getProperty("FullName"));
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-employee-config/div[2]/div/div[2]/div[1]/div/app-accordion/div/app-accordion-item[1]/div[2]/div/div/div/div[3]/div/div/input")).sendKeys(prop.getProperty("EmailID"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-employee-config/div[2]/div/div[2]/div[1]/div/app-accordion/div/app-accordion-item[1]/div[2]/div/div/div/div[4]/div/div/input")).sendKeys(prop.getProperty("Secondary_EmailID"));
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-employee-config/div[2]/div/div[2]/div[1]/div/app-accordion/div/app-accordion-item[1]/div[2]/div/div/div/div[5]/div/div/div/mat-datepicker-toggle/button")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"mat-datepicker-0\"]/mat-calendar-header/div/div/button[1]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"mat-datepicker-0\"]/mat-calendar-header/div/div/button[2]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"mat-datepicker-0\"]/div/mat-multi-year-view/table/tbody/tr[2]/td[3]/div[1]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"mat-datepicker-0\"]/div/mat-year-view/table/tbody/tr[2]/td[3]/div[1]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"mat-datepicker-0\"]/div/mat-month-view/table/tbody/tr[2]/td[2]/div[1]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-employee-config/div[2]/div/div[2]/div[1]/div/app-accordion/div/app-accordion-item[1]/div[2]/div/div/div/div[6]/div/div/input")).sendKeys(prop.getProperty("Mobile_number"));
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[text()=' Employment Information ']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		

		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-employee-config/div[2]/div/div[2]/div[1]/div/app-accordion/div/app-accordion-item[2]/div[2]/div/div/div/div[1]/div/div/div/mat-datepicker-toggle/button")).click();
	
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"mat-datepicker-1\"]/mat-calendar-header/div/div/button[1]")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"mat-datepicker-1\"]/div/mat-multi-year-view/table/tbody/tr[2]/td[1]/div[1]")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"mat-datepicker-1\"]/div/mat-year-view/table/tbody/tr[2]/td[2]/div[1]")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"mat-datepicker-1\"]/div/mat-month-view/table/tbody/tr[1]/td[2]/div[1]")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-employee-config/div[2]/div/div[2]/div[1]/div/app-accordion/div/app-accordion-item[2]/div[2]/div/div/div/div[2]/div/div/select")).click();
		Thread.sleep(3000);
		
		Select s=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-employee-config/div[2]/div/div[2]/div[1]/div/app-accordion/div/app-accordion-item[2]/div[2]/div/div/div/div[2]/div/div/select")));
		s.selectByVisibleText(prop.getProperty("Employment_Status"));
		
		Thread.sleep(2000);
		Select s1=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-employee-config/div[2]/div/div[2]/div[1]/div/app-accordion/div/app-accordion-item[2]/div[2]/div/div/div/div[5]/div/div/select")));
		s1.selectByVisibleText(prop.getProperty("Manager"));
		
		Thread.sleep(2000);
		Select s2=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-employee-config/div[2]/div/div[2]/div[1]/div/app-accordion/div/app-accordion-item[2]/div[2]/div/div/div/div[7]/div/div/select")));
		s2.selectByVisibleText(prop.getProperty("department"));
		
		Thread.sleep(2000);
		Select s3=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-employee-config/div[2]/div/div[2]/div[1]/div/app-accordion/div/app-accordion-item[2]/div[2]/div/div/div/div[8]/div/div/select")));
		s3.selectByVisibleText(prop.getProperty("Designation"));
		
		Thread.sleep(2000);
		Select s4=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-employee-config/div[2]/div/div[2]/div[1]/div/app-accordion/div/app-accordion-item[2]/div[2]/div/div/div/div[9]/div/div/select")));
		s4.selectByVisibleText(prop.getProperty("JobRole"));
		
		Thread.sleep(2000);
		Select s5=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-employee-config/div[2]/div/div[2]/div[1]/div/app-accordion/div/app-accordion-item[2]/div[2]/div/div/div/div[10]/div/div/select")));
		s5.selectByVisibleText(prop.getProperty("Experience"));
		
		Thread.sleep(2000);
		Select s6=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-employee-config/div[2]/div/div[2]/div[1]/div/app-accordion/div/app-accordion-item[2]/div[2]/div/div/div/div[11]/div/div/select")));
		s6.selectByVisibleText(prop.getProperty("Work_Location"));
		
		Thread.sleep(2000);
		Select s7=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-employee-config/div[2]/div/div[2]/div[1]/div/app-accordion/div/app-accordion-item[2]/div[2]/div/div/div/div[12]/div/div/select")));
		s7.selectByVisibleText(prop.getProperty("Bilable"));
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("(//*[text()='Create Employee'])[2]")).click();
		Thread.sleep(9000);
		
		driver.findElement(By.xpath("//*[text()='OK']")).click();
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-employee-config/div[1]/div/button[2]")).click();
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//*[text()=\"View All Employees\"]")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[text()=\"View All Employees Draft\"]")).click();
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//*[text()=\"View All Domain\"]")).click();		
		Thread.sleep(3000);
		
		test=extent.createTest("Department Choose");
		
		WebElement Dept=driver.findElement(By.xpath("//*[@aria-controls='dept']"));
		Boolean dept=Dept.isDisplayed();
		Assert.assertTrue(dept);
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@aria-controls='dept']")).click();
		
		
		
		Thread.sleep(3000);
		
		
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-dept-config/div[2]/div/div[2]/div[1]/div[1]/div/div/input")).sendKeys(prop.getProperty("department_Name"));
		
		
		Thread.sleep(2000);
		Select depthead=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-dept-config/div[2]/div/div[2]/div[1]/div[2]/div/div/select")));
		depthead.selectByVisibleText(prop.getProperty("Head_of_Department"));
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-dept-config/div[2]/div/div[2]/div[2]/div/button")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[text()='OK']")).click();
		Thread.sleep(10000);
		
		test=extent.createTest("Role Choose");
		
		WebElement Degination=driver.findElement(By.xpath("//*[text()='Designation']"));
		Boolean deg=Degination.isDisplayed();
		Assert.assertTrue(deg);
		
		driver.findElement(By.xpath("//*[text()='Designation']")).click();
		Thread.sleep(2000);
		
		WebElement Role=driver.findElement(By.xpath("//*[text()='Role']"));
		Boolean rol=Role.isDisplayed();
		Assert.assertTrue(rol);
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[text()='Role']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[text()='Add New Role']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-role-config/div[2]/div/div[2]/div[1]/div[1]/div/div/input")).sendKeys(prop.getProperty("Role_job"));
		Thread.sleep(5000);
		
		
		Select Employee_Role=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-role-config/div[2]/div/div[2]/div[1]/div[2]/div/div/select")));
		Employee_Role.selectByVisibleText(prop.getProperty("Employee_Role"));
		
		Thread.sleep(2000);
		Select Employee_Department=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-role-config/div[2]/div/div[2]/div[1]/div[3]/div/div/select")));
		Employee_Department.selectByVisibleText(prop.getProperty("Employee_Department"));
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//*[text()='Create Role'])[2]")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='OK']")).click();
		
		Thread.sleep(15000);
		driver.findElement(By.xpath("//*[text()='Leave']")).click();
		Thread.sleep(5000);
		
		WebElement Holiday=driver.findElement(By.xpath("//*[text()='Add Holidays']"));
		Boolean holiday=Holiday.isDisplayed();
		Assert.assertTrue(holiday);
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[text()='Add Holidays']")).click();
		Thread.sleep(3000);
		
		Thread.sleep(2000);
		Select Select_Holiday_type=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[2]/div/div[2]/div[1]/div[1]/div/div/select")));
		Select_Holiday_type.selectByVisibleText(prop.getProperty("Select_Holiday_type"));
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[2]/div/div[2]/div[1]/div[2]/div/div/input")).sendKeys(prop.getProperty("Occasion"));
		Thread.sleep(5000);
		
		
		
		((JavascriptExecutor) driver).executeScript("document.getElementById('occasionDate').value='2023-03-23'");

		//=========================================
		
		Select State=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[2]/div/div[2]/div[2]/div[3]/div/div/select")));
		State.selectByVisibleText(prop.getProperty("State"));
		Thread.sleep(3000);
		
		
		test=extent.createTest("View Holidays");
		
		WebElement ViewHoliday=driver.findElement(By.xpath("//*[text()='View Holidays']"));
		Boolean viewholiday=ViewHoliday.isDisplayed();
		Assert.assertTrue(viewholiday);
		
		driver.findElement(By.xpath("//*[text()='View Holidays']")).click();
		Thread.sleep(3000);
		
		test=extent.createTest("Add Leave");
		
		WebElement AddLeave=driver.findElement(By.xpath("//*[text()='Add Leave Type']"));
		Boolean addleave=AddLeave.isDisplayed();
		Assert.assertTrue(addleave);
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[text()='Add Leave Type']")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[3]/div/div[2]/div[1]/div[1]/div/div/input")).sendKeys(prop.getProperty("Leave_Type_N"));
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[3]/div/div[2]/div[1]/div[2]/div/div/input")).sendKeys(prop.getProperty("Leave_Code_N"));
		Thread.sleep(2000);
		
		Thread.sleep(2000);
		Select Gender=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[3]/div/div[2]/div[1]/div[3]/div/div/select")));
		Gender.selectByVisibleText(prop.getProperty("Gender"));
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[3]/div/div[2]/div[2]/div[1]/div/div/input")).sendKeys(prop.getProperty("Default_Leave"));
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"leaveDesc\"]")).sendKeys(prop.getProperty("Description"));
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"leaveRules\"]")).sendKeys(prop.getProperty("Rules"));
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[text()='Add']")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[text()='OK']")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[text()='View Leave Types']")).click();
		Thread.sleep(3000);
		
		
		test=extent.createTest("Manage Employee leave Balance");
		
		driver.findElement(By.xpath("//*[text()='Manage Employee Leave Balance']")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[4]/div/div[2]/div[1]/div[1]/div/div/input")).sendKeys("151345");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[text()='Get Employee Leave Balance']")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[1]/div/button[6]")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[5]/div/div[2]/div[1]/div[1]/div/div/input")).sendKeys(prop.getProperty("Policy_Name"));
		Thread.sleep(3000);
		
		Select Employment_Status=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[5]/div/div[2]/div[1]/div[2]/div/div/select")));
		Employment_Status .selectByVisibleText(prop.getProperty("Employment_Status"));
		
		Thread.sleep(3000);
		
		Select Leave_Type=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[5]/div/div[2]/div[1]/div[3]/div/div/select")));
		Leave_Type .selectByVisibleText(prop.getProperty("Leave_Type"));	
		Thread.sleep(3000);
		
		Select Allow_LeaveApplication=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[5]/div/div[2]/div[2]/div/div/div/select")));
		Allow_LeaveApplication .selectByVisibleText(prop.getProperty("Allow_Leave_Application"));	
		Thread.sleep(3000);
		
		Select MonthlyIncrement=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[5]/div/div[2]/div[3]/div/div/div/select")));
		MonthlyIncrement .selectByVisibleText(prop.getProperty("Monthly_Increment"));	
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[5]/div/div[2]/div[3]/div[2]/div/div/input")).sendKeys(prop.getProperty("Monthly_Increment_value"));
		Thread.sleep(3000);
		
		Select One_Time_LeaveLimit=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[5]/div/div[2]/div[4]/div[1]/div/div/select")));
		One_Time_LeaveLimit .selectByVisibleText(prop.getProperty("One_Time_Leave_Limit"));	
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[5]/div/div[2]/div[4]/div[2]/div/div/input")).sendKeys(prop.getProperty("One_Time_Leave_Minimum_Limit_Value"));
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[5]/div/div[2]/div[4]/div[3]/div/div/input")).sendKeys(prop.getProperty("One_Time_Leave_Maximum_Limit_Value"));
		Thread.sleep(3000);
		
		Select Leave_CarryForward=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[5]/div/div[2]/div[5]/div[1]/div/div/select")));
		Leave_CarryForward .selectByVisibleText(prop.getProperty("Leave_Carry_Forward"));	
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[5]/div/div[2]/div[5]/div[2]/div/div/input")).sendKeys(prop.getProperty("Leave_Carry_Forward_Value"));
		Thread.sleep(3000);
		
		Select Leave_ValidationExpiration=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[5]/div/div[2]/div[6]/div/div/div/select")));
		Leave_ValidationExpiration .selectByVisibleText(prop.getProperty("Leave_Validation_Expiration"));	
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[5]/div/div[2]/div[6]/div[2]/div/div/input")).sendKeys(prop.getProperty("Leave_Validation_Expiration_period"));
		Thread.sleep(3000);
		
		Select Leave_App_CountLocking=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[5]/div/div[2]/div[7]/div/div/div/select")));
		Leave_App_CountLocking .selectByVisibleText(prop.getProperty("Leave_App_Count_Locking"));	
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[5]/div/div[2]/div[7]/div[2]/div/div/input")).sendKeys(prop.getProperty("Leave_App_Count_Locking_Period"));
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[5]/div/div[2]/div[7]/div[3]/div/div/input")).sendKeys(prop.getProperty("Leave_App_Count_value"));
		Thread.sleep(3000);
		
		Select Leave_AppProbation=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[5]/div/div[2]/div[8]/div[1]/div/div/select")));
		Leave_AppProbation .selectByVisibleText(prop.getProperty("Leave_App_Probation"));	
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[5]/div/div[2]/div[8]/div[2]/div/div/input")).sendKeys(prop.getProperty("Leave_Probation_Period"));
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-leave-config/div[5]/div/div[2]/div[9]/div/button")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[text()='OK']")).click();
		Thread.sleep(3000);
		
		WebElement Leave_Policies=driver.findElement(By.xpath("//*[text()='View Leave Policies']"));
		Boolean leave_ploicies=Leave_Policies.isDisplayed();
		Assert.assertTrue(leave_ploicies);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[text()='View Leave Policies']")).click();
		Thread.sleep(3000);
		
		
		test=extent.createTest("Home Page");
		
		
		WebElement HomePage=driver.findElement(By.xpath("//*[text()='Home']"));
		Boolean homepage=HomePage.isDisplayed();
		Assert.assertTrue(homepage);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[text()='Home']")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-home-config/div[2]/div/div[2]/div[1]/div/div/div/input")).sendKeys(prop.getProperty("Event_Name"));
		Thread.sleep(3000);
		
//		driver.findElement(By.xpath("//*[@type='file']")).sendKeys("‪C:\\Users\\biswa\\Downloads\\download.jpg");
//		Thread.sleep(7000);
		
		
		driver.findElement(By.xpath("(//*[text()='Set Notification'])[1]")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"notificationMsg\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"notificationMsg\"]")).sendKeys(prop.getProperty("Notification"));
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[text()='Set']")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[text()='OK']")).click();
		Thread.sleep(3000);
		
		
		test=extent.createTest("Portal Page");
		
		WebElement Portalpage=driver.findElement(By.xpath("(//*[@class=\"nav-link config__tab-item__link\"])[6]"));
		Boolean portalpage=Portalpage.isDisplayed();
		Assert.assertTrue(portalpage);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("(//*[@class=\"nav-link config__tab-item__link\"])[6]")).click();
		Thread.sleep(3000);
		
		WebElement AppreacationEvent=driver.findElement(By.xpath("(//*[text()='Enable Appreciation Event'])[1]"));
		Boolean appreacationevent=AppreacationEvent.isDisplayed();
		Assert.assertTrue(appreacationevent);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("(//*[text()='Enable Appreciation Event'])[1]")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"fromDatePicker\"]")).click();
		Thread.sleep(6000);
		
		driver.findElement(By.xpath("//*[@id=\"mat-datepicker-2\"]/div/mat-month-view/table/tbody/tr[4]/td[7]/div[1]")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"toDatePicker\"]")).click();
		Thread.sleep(6000);
		
		driver.findElement(By.xpath("//*[@id=\"mat-datepicker-3\"]/div/mat-month-view/table/tbody/tr[4]/td[7]/div[1]")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-portal-config/div[3]/div/div[2]/div[2]/div[1]/div/div/input")).sendKeys(prop.getProperty("Event_Name"));
		Thread.sleep(3000);
		
		Select SelectEmployees=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-portal-config/div[3]/div/div[2]/div[2]/div[2]/div/div/select")));
		SelectEmployees .selectByVisibleText(prop.getProperty("Select_Employees"));	
		Thread.sleep(9000);
		
		driver.findElement(By.xpath("//*[text()='Create Appreciation Event']")).click();
		Thread.sleep(9000);
		
		driver.findElement(By.xpath("//*[text()='OK']")).click();
		
		Thread.sleep(3000);
		
		WebElement ViewAppreacation=driver.findElement(By.xpath("(//*[text()='View Appreciation'])[1]"));
		Boolean viewappreacation=ViewAppreacation.isDisplayed();
		Assert.assertTrue(viewappreacation);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("(//*[text()='View Appreciation'])[1]")).click();
		Thread.sleep(3000);
		
		Select AppreciationEvent=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-portal-config/div[4]/div/div[2]/div[1]/div/div/div/select")));
		AppreciationEvent .selectByVisibleText(prop.getProperty("Appreciation_Event"));	
		Thread.sleep(3000);
		
		Select AppreciationCategories=new Select(driver.findElement(By.xpath("//*[@id=\"userTabContent\"]/div/app-portal-config/div[4]/div/div[2]/div[2]/div/div/div/select")));
		AppreciationCategories .selectByVisibleText(prop.getProperty("Appreciation_Categories"));	
		Thread.sleep(3000);
		
		test=extent.createTest("Logout Page");
		
		driver.findElement(By.xpath("//*[@id=\"user-dropdown\"]/i")).click();
		Thread.sleep(3000);
		
		WebElement LogOut=driver.findElement(By.xpath("/html/body/app-root/app-body/nav/ul/li/div/a[2]"));
		Boolean logout=LogOut.isDisplayed();
		Assert.assertTrue(logout);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("/html/body/app-root/app-body/nav/ul/li/div/a[2]")).click();
		Thread.sleep(10000);
		
		System.out.println("COMPLETED");
		
	
		
	}

	
	@AfterMethod
	public void tearDown(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			test.log(Status.FAIL, "Test is Fail"+ result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			test.log(Status.PASS, "Test case are pass");
		}
		driver.close();
	}
	
	

}

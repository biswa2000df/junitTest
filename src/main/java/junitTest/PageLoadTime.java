package junitTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;

public class PageLoadTime {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
			
		System.setProperty("webdriver.chrome.driver", "C:\\browserdrivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://mail.apmosys.com/webmail/");
		Thread.sleep(1000);
		driver.findElement(By.name("email-address")).sendKeys("biswajit.sahoo@apmosys.com");
		driver.findElement(By.name("next")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("password")).sendKeys("Apmosys@2022");
		driver.findElement(By.name("remember-me")).click();
		
		long start=System.currentTimeMillis();
		driver.findElement(By.name("next")).click();
		Thread.sleep(4000);
		long end=System.currentTimeMillis();
		System.out.println(start);
		System.out.println(end);
		long finish=(end-start);
		long sec=finish/1000;
		System.out.println(finish);
		System.out.println(sec);
		
		
		
		
		if(sec>3)
		{
			 Properties props = new Properties();
		        props.put("mail.smtp.host", "smtp.gmail.com");
		        props.put("mail.smtp.socketFactory.port", "465");
		        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		        props.put("mail.smtp.auth", "true");
		        props.put("mail.smtp.prot", "465");

		        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		                return new PasswordAuthentication("biswajitkanha11@gmail.com","zchaabytnungtkpp");
		            }
		        });

		        try {
		            Message message = new MimeMessage(session);
		            message.setFrom(new InternetAddress("biswajitkanha11@gmail.com"));
		            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("biswajit.sahoo@apmosys.com"));
		            message.setSubject("This is my subject");
		            message.setText("Hello");
		            Transport.send(message);

		        } catch (Exception e) {
		            JOptionPane.showMessageDialog(null, e);
		        }
		        System.out.println("Mail sent Successfully");
		}
		driver.navigate().refresh();

		driver.navigate().refresh();
		File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(f, new File("C:\\Users\\biswa\\Desktop\\New folder (4)\\New folder\\junitTest\\BISWAJITkanha.jpeg"));
		Thread.sleep(8000);
		driver.quit();
		
		
	}

}

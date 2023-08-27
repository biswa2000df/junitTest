package junitTest;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class JavaMail {

	public static void main(String[] args) {
		
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
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("priyankagouda187@gmail.com"));
	            message.setSubject("This is my subject");
	            message.setText("Hello");
	            Transport.send(message);

	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e);
	        }
		System.out.println("Done");

	}

}

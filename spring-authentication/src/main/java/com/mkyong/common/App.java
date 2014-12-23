package com.mkyong.common;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
	public static void sendMail(String mailId,String subject, String msg) {

	    final String username = "betty.columbia1@gmail.com";
	    final String password = "columbia123";
	    
	    Properties properties = new Properties(); 
	    properties.setProperty("mail.smtp.host", "smtp.gmail.com");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.setProperty("mail.smtp.port", "25");
	    properties.setProperty("mail.smtp.user", username);
	    properties.setProperty("mail.smtp.password", password);
	    properties.setProperty("mail.smtp.auth", "true");       
	    properties.setProperty("mail.debug", "true");
	    Session session = Session.getInstance(properties,
	    		  new javax.mail.Authenticator() {
	    		     protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
	    		      return new PasswordAuthentication(username, password);
	    		      }
	    		   });
	    try {
	      MimeMessage message = new MimeMessage(session);
	      message.setSubject("My Movie Buddy :" + subject);
	      message.setFrom(new InternetAddress("vnv2102@columbia.edu"));
	      message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(mailId));
	      message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(username));
	      System.out.println("REACHED HERE");
	      String body = msg;
	      message.setContent(body,"text/html");
	      Transport transport = session.getTransport("smtp");
          transport.connect( null,username,password); //host, 25, "myemailhere", "mypasshere");
          message.saveChanges();
          transport.sendMessage(message,message.getAllRecipients());
          System.out.println("Message sent");
          transport.close();
	    } catch (Exception exception) {

	    }
	}
	
	
	public static void sendMail(String mailIdTo,String mailIdTo2,String subject, String msg) {

	    final String username = "betty.columbia1@gmail.com";
	    final String password = "columbia123";
	    
	    Properties properties = new Properties(); 
	    properties.setProperty("mail.smtp.host", "smtp.gmail.com");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.setProperty("mail.smtp.port", "25");
	    properties.setProperty("mail.smtp.user", username);
	    properties.setProperty("mail.smtp.password", password);
	    properties.setProperty("mail.smtp.auth", "true");       
	    properties.setProperty("mail.debug", "true");
	    Session session = Session.getInstance(properties,
	    		  new javax.mail.Authenticator() {
	    		     protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
	    		      return new PasswordAuthentication(username, password);
	    		      }
	    		   });
	    try {
	      MimeMessage message = new MimeMessage(session);
	      message.setSubject("My Movie Buddy :" + subject);
	      message.setFrom(new InternetAddress("betty.columbia1@gmail.com"));
	      message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(mailIdTo));
	      message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(mailIdTo2));
	      message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(username));
	      System.out.println("REACHED HERE");
	      String body = msg;
	      message.setContent(body,"text/html");
	      Transport transport = session.getTransport("smtp");
          transport.connect( null,username,password); //host, 25, "myemailhere", "mypasshere");
          message.saveChanges();
          transport.sendMessage(message,message.getAllRecipients());
          System.out.println("Message sent");
          transport.close();
	    } catch (Exception exception) {

	    }
	}

	
    public static void sendMailFromSpring()
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
    	MailMail mm = (MailMail) context.getBean("mailMail");
    	mm.sendMail("Betty", "This is text content");        
    }
}

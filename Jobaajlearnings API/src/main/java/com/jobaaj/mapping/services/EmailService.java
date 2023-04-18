package com.jobaaj.mapping.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;





@Service
public class EmailService {

	
	@Autowired
	private JavaMailSender mailSender;
	
	
    @Value("${spring.mail.username}") private String sender;

    
	
	public String sendMail(String toEmail,
						 String subject,
						 String body) {
		
		try {
			
			
		 SimpleMailMessage mailMessage = new SimpleMailMessage();
	
	     // Setting up necessary details
	     mailMessage.setFrom("info@mailer.jobaaj.com");
	     mailMessage.setTo(toEmail);
	     mailMessage.setText(body);
	     mailMessage.setSubject(subject);
	
	     // Sending the mail
	     mailSender.send(mailMessage);
	     
	     return "Send Succesfully";
	     
     
		}
		
		catch (Exception e) {
			
			return e.getLocalizedMessage();
       
        }
 
	}
	
	

}

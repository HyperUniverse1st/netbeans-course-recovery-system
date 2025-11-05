/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.courserecoverysystem.controller;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
/**
 *
 * @author User
 */
public class EmailService {
    private static final String SENDER_EMAIL = "seanngzhixuan@gmail.com";
    private static final String APP_PASSWORD = "obcaqpwehcoanism";

    public static boolean sendOtpEmail(String toEmail, String otp) {
        try {
            Properties props = new Properties();
            // Gmail over STARTTLS (recommended)
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            // Force modern TLS
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(SENDER_EMAIL, APP_PASSWORD);
                }
            });

            // Turn on SMTP debug to see what's happening
            session.setDebug(true);

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(SENDER_EMAIL));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject("Your OTP Code");
            msg.setText("Your verification code is: " + otp + "\nIt expires in 10 minutes.");

            Transport.send(msg);
            System.out.println("Email sent to: " + toEmail);
            return true;

        } catch (MessagingException e) {
            System.out.println("Failed to send email: " + e.getMessage());
            e.printStackTrace(); // <-- Read the stack trace in Output window
            return false;
        }
    }
}

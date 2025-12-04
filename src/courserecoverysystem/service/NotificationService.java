/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.service;

/**
 *
 * @author seany
 */

/*
so far only one method so... call me if you need more
*/

public class NotificationService {

    public static boolean sendOTPEmail(String toEmail, String otp) {
        String subject = "Your OTP Code";
        String body = "Your OTP code is: " + otp + "\nIt will expire in 3 minutes.";
        return EmailService.sendEmail(toEmail, subject, body);
    }
}

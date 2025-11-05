/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.courserecoverysystem.controller;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author User
 */
public class OTPService {
    private static final Map<String, String> otpByEmail = new HashMap<>();
    
    //generate 6 digit number
    public static String generate6Digit(){
        int n = (int)(Math.random()*1_000_000);
        return String.format("%06d", n);
    }
    
    //save the otp linked to an email
    public static void saveOtp(String email, String otp){
        String key = email == null ? "" : email.trim().toLowerCase();
        otpByEmail.put(key, otp);
        System.out.println("Debug saveOtp: key=" + key + ", otp=" + otp);
    }
    
    //check if OTP is correct
    public static boolean checkOtp(String email, String userOtp) {
        String key = email == null ?"": email.trim().toLowerCase();
        String real = otpByEmail.get(key);
        System.out.println("DEBUG checkOtp: key=" + key + ", stored=" + real + ", input=" + userOtp);
        return real != null && real.equals(userOtp);
    }
    
    //clear OTP after success
    public static void clear(String email){
        String key = email == null ? "" : email.trim().toLowerCase();
        otpByEmail.remove(key);
        System.out.println("DEBUG clearOtp: key=" + key);
    }
}

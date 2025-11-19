/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.service;

/**
 *
 * @author seany
 */
import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.*;

/*
This file was deleted and i want to kill myself dont touch or i will end myself
*/

public class OTPService {
    
    private static final long validityDuration = 18000;
    private static final Map<String, OTPEntry> otpMap = new ConcurrentHashMap<>();
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final SecureRandom random = new SecureRandom();

    public String generateOTP(String email) {
        int otpInt = 100000 + random.nextInt(900000);
        String otp = String.valueOf(otpInt);

        long now = System.currentTimeMillis();
        OTPEntry entry = new OTPEntry(otp, now);
        otpMap.put(email, entry);

        scheduler.schedule(() -> clearOTP(email), validityDuration, TimeUnit.MILLISECONDS);

        return otp;
    }

    public boolean verifyOTP(String email, String userOtp) {
        OTPEntry entry = otpMap.get(email);
        if (entry == null) return false;
        
        if (System.currentTimeMillis() > entry.timestamp + validityDuration) {
            otpMap.remove(email);
            return false;
        }
        return entry.otp.equals(userOtp);
    }

    public void clearOTP(String email) {
        otpMap.remove(email);
    }

    private static class OTPEntry {
        final String otp;
        final long timestamp;

        OTPEntry(String otp, long timestamp) {
            this.otp = otp;
            this.timestamp = timestamp;
        }
    }}

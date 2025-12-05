/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.service;

/**
 *
 * @author seany
 */
import java.time.Instant;
import java.time.Duration;
import java.time.format.DateTimeParseException;

public class TimeService {

    public boolean isOverlapping(Instant start1, Instant end1, 
                                 Instant start2, Instant end2) {

        return !end1.isBefore(start2) && !end2.isBefore(start1);
    }

    public boolean isValidInstant(String timestamp) {
        try {
            Instant.parse(timestamp);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public Instant createInstant(String timestamp) {
        try {
            return Instant.parse(timestamp);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    
    public boolean isValidRange(Instant start, Instant end) {
        return start.isBefore(end);
    }
    
    public Instant addTime(Instant time, long hour, long minutes) {
        return time.plus(Duration.ofHours(hour).plusMinutes(minutes));
        
    }
}
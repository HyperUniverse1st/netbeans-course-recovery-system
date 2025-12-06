/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.service;

/**
 *
 * @author seany
 */
public class ExceptionService extends RuntimeException {
    
    public static void invalidLength(String filename) {
        throw new RuntimeException("Invalid length in file: " + filename);
    }

    public static void emptyFile(String filename) {
        throw new RuntimeException("No data found in file: " + filename);
    }

    public static void invalidColumn(String column) {
        throw new RuntimeException("Invalid column: " + column);
    }

    
}

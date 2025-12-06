/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.uiElements;

import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
/**
 *
 * @author User
 */
public class TableUtils {
    private TableUtils(){}
    public static void starAutoRefresh(int delayMs, Runnable reloadAction){
        reloadAction.run();
        
        Timer timer = new Timer(delayMs, e -> reloadAction.run());
        timer.start();
    }
    
    //Center all columns and the header text in a JTable
    public static void centerAllColumns(JTable table){
        //This renderer will be used to center text in table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        //Apply to every column in table
        for (int i = 0; i < table.getColumnCount(); i++){
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        //Center the header text in table
        DefaultTableCellRenderer headerRenderer = 
                (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
    }
}

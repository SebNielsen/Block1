/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread2_excercise3;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author sebastiannielsen
 */
public class BackUpHandler implements Runnable {
    private List<String> textlines;
    private List<Observer> observers = new ArrayList();
    private final String userDir = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
    private final String fileName = "backup.txt";
    private final File filepath = new File(userDir, fileName);
    private String message;
    private boolean keepRunning = true;
    
    public BackUpHandler(List<String> textlines){
        this.textlines = textlines;
    }
    
    public void registerObserver(Observer o){
        observers.add(o);
    }
    
    private void save(){
        FileWriter writer; 
        PrintWriter out;
        try {
                writer = new FileWriter(filepath, false);
                out = new PrintWriter(writer);
                for (String textline : textlines) {                     
                    out.write(textline + " ");
                }
                message = "saved";
                out.close();  //Closing the file
        } catch (Exception ex) {  //If something goes wrong everything is send to system out.
            message = "not saved";
        }
    }
    
    private void notifyObservers(String msg){
        for(Observer obs : observers){
            obs.dataSaved(msg);
        }
    }

    public void run(){
        while(keepRunning){
            try {
                save();
                notifyObservers(message);
                Thread.sleep(15000);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(BackUpHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
}

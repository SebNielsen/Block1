/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread1_excercise2;

/**
 *
 * @author sebastiannielsen
 */
public class Excercise2 {
    public static Even even = new Even();
//    private static Object lockObject = new Object();
    
    public static class Thread1 extends Thread {
        public void run(){
           
                boolean evenNumber;
                for(int i = 0; i < 100000; i++){
                    int j = even.next();
                    if(j % 2 == 0){
                      evenNumber = true; 
                     } else{
                       evenNumber = false;
                       System.out.println(evenNumber + " " + j);
                     }
                }
           
        }
    }
    
    public static void main(String[] args) {
        
        Thread thread1 = new Thread1();
        Thread thread2 = new Thread1();
        
        thread1.start();
        thread2.start();
    }
    
}

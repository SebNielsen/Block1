/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread1_excercise1;

/**
 *
 * @author sebastiannielsen
 */
public class Exercise1 {
    
    public static class Thread1 extends Thread {
        public void run() {
            long sum = 0;
            for(long i = 0; i < 1000000; i++){
                sum += i;
            }
            System.out.println("Sum: " + sum);
            
        }
    }
    
    public static class Thread2 extends Thread {
        public void run() {
            for(int i = 0; i <= 5; i++){
                System.out.println(i);
                try {
                    Thread2.sleep(2000);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
    
    public static class Thread3 extends Thread {
        public static boolean finished = false;
        private int number = 10;
        
        public void run() {
            while(!finished){
                System.out.println(number);
                number++;
                try {
                    Thread3.sleep(3000);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
            }       
        }
    }    

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
        new Thread3().start();
        
        try {
            Thread3.sleep(10000);
            }
        catch (InterruptedException e) {
            // fall through
        }
        Thread3.finished = true;
        
       
    }
}    
  

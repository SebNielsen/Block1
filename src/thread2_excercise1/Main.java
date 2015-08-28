package thread2_excercise1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



/**
 *
 * @author sebastiannielsen
 */




public class Main {
    
    private static String[] links = {"https://fronter.com/cphbusiness/design_images/images.php/Classic/login/fronter_big-logo.png",
                     "https://fronter.com/cphbusiness/design_images/images.php/Classic/login/folder-image-transp.png",
                     "https://fronter.com/volY12-cache/cache/img/design_images/Classic/other_images/button_bg.png"
    };
    
    public static class SequentialCalc{
       SequentialCalc(){
           int totalBytes = 0;
            CalculateBytes[] bytes = {new CalculateBytes(links[0]),
                                     new CalculateBytes(links[1]),
                                     new CalculateBytes(links[2])
            };
            
            Thread t1 = new Thread(bytes[0]);
            Thread t2 = new Thread(bytes[1]);
            Thread t3 = new Thread(bytes[2]);
            
            long start = System.currentTimeMillis();
            t1.run();
            t2.run();
            t3.run();
            long end = System.currentTimeMillis();
            
            for(CalculateBytes cal : bytes){
                totalBytes+=cal.getSum();
            }
            System.out.println(totalBytes);
            System.out.println("Time Sequental: "+(end-start));
       }
       
    }
   
    public static class ParallelCalc{
        
        ParallelCalc() throws InterruptedException{
            int totalBytes = 0;
            CalculateBytes[] bytes = {new CalculateBytes(links[0]),
                                     new CalculateBytes(links[1]),
                                     new CalculateBytes(links[2])

            };
            
            ExecutorService executor = Executors.newFixedThreadPool(bytes.length);
            long start = System.currentTimeMillis();
            for(CalculateBytes cal : bytes){
                executor.submit(cal);
            }
            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);
            long end = System.currentTimeMillis();
            for(CalculateBytes cal : bytes){
                totalBytes += cal.getSum();
            }

            System.out.println(totalBytes);
            System.out.println("Time Parallel: "+(end-start));
        }
    }
   
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Avilable Processors: " + Runtime.getRuntime().availableProcessors());
        SequentialCalc calc1 = new SequentialCalc();
        ParallelCalc calc2 = new ParallelCalc();
    }
    

}

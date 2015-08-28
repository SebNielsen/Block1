package thread2_excercise4;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 * @author Lars Mortensen
 */
class DeadLockDetector implements Runnable {

  ThreadMXBean tmxb = ManagementFactory.getThreadMXBean();
  boolean doRun = true;
  
  public void stop() {
    this.doRun = false;
  }
  
  @Override
  public void run() {
    while (doRun) {
      long[] threadIds = tmxb.findDeadlockedThreads();
        System.out.println(threadIds);
//        if(threadIds.length > 0){
//            for(int i = 0; i < threadIds.length; i++){
//                System.out.println(threadIds[i]);
//            }
//            stop();
//        }
    }
  }
}
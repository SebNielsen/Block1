package thread1_excercise4;

import java.util.concurrent.locks.ReentrantLock;

public class TurnstileCounter {
  private final ReentrantLock lock = new ReentrantLock();
  static final long DELAY_VAL = 10000;
  long count = 0;

  public long getValue() {
    return count;
  }

  public /*synchronized*/ void incr() {

    lock.lock();
    try{
        count++;
    } finally{
        lock.unlock();
    }
    
  }
}

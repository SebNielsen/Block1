package thread2_excercise2;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class FibonacciTask extends Observable implements Runnable{
    private long inputNumber;
    List<FibonacciObserver> observers = new ArrayList();
    
    public void registerFibonacciObserver(FibonacciObserver o){
      observers.add(o);
    }
    
    public FibonacciTask(long input) {
        this.inputNumber = input;
    }
    
    private void notifyObservers(long fibonacciNumber){
        for(FibonacciObserver observer : observers){
          observer.dataReady(fibonacciNumber);
        }
    }
    @Override
    public void run() {
        notifyObservers(fib(inputNumber)); 
    }
    
    private long fib(long n) {
        if ((n == 0) || (n == 1)) {
          return n;
        } else {
          return fib(n - 1) + fib(n - 2);
        }
  }


}

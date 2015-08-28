package thread1_excercise5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// NOT THREADSAFE
public class BankAccountUnsynchronized {

  private double balance;
  private Lock lock = new ReentrantLock();

  public BankAccountUnsynchronized() {
    balance = 0;
  }

  public /*synchronized*/ void deposit(double amount) {
//    double newBalance = balance + amount;
//    balance = newBalance;
    lock.lock();
    try{
        balance += amount;
    } finally{
        lock.unlock();
    }
    
    
   }

  public /*synchronized*/ void withdraw(double amount) {
//    double newBalance = balance - amount;
//    balance = newBalance;
     lock.lock();
     try{
     balance -= amount;
     } finally{
         lock.unlock();
     }
   }

  public double getBalance() {
    return balance;
  }
}

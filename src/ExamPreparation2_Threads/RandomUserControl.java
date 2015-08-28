package ExamPreparation2_Threads;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import randomperson.RandomUser;
import randomperson.RandomUserGenerator;

public class RandomUserControl extends Observable implements Runnable  {
  
    
  List<RandomUserObserver> observers = new ArrayList();
  
  public void registerRandomUserObserver(RandomUserObserver o){
      observers.add(o);
  }
  
  @Override
  public void run() {
    RandomUser user = null;
        try {
          user = RandomUserGenerator.getRandomUser();
        } catch (InterruptedException ex) {
          Logger.getLogger(RandomUserControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(RandomUserObserver observer : observers){
            observer.update(this, user);
        }
        setChanged();
        notifyObservers(user);
   }
     
}

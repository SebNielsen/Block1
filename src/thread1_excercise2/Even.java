package thread1_excercise2;

/**
 *
 * @author sebastiannielsen
 */
public class Even {
  private int n = 0;
  
  public synchronized int next()
    {
        n++;
        n++;
        return n;
    } 
}


import java.util.Iterator;
import java.util.NoSuchElementException;

public class Subset {
   public static void main(String[] args){
   RandomizedQueue<String> RndQueue = new RandomizedQueue<String>();
	int N = Integer.parseInt(args[0]); 
	while (!StdIn.isEmpty()) {
        String item = StdIn.readString();
	    RndQueue.enqueue(item);
     }

    Iterator it =RndQueue.iterator();
    int count = 0;
		while(it.hasNext() && count < N) {
			String item = (String) it.next();
			StdOut.println( item );
			count++;
		}
    }
}
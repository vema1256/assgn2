import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int N;
	private Item[] rndqueue;
	private int first, last;
	public RandomizedQueue() {                // construct an empty randomized queue
		N = 0;
		first = last = 0;
		rndqueue = (Item[]) new Object[2];
	}

	public boolean isEmpty() { return N==0;}                 // is the queue empty?

	public int size() { return N;}                        // return the number of items on the queue

	public void enqueue(Item item){
		if (N == rndqueue.length) resize(2*rndqueue.length);   // double size of array if necessary
		rndqueue[N++] = item;
		last = N;
	}           // add the item

	public Item dequeue() {                    // delete and return a random item
		if (isEmpty()) throw new NoSuchElementException(" ");
		int idx = StdRandom.uniform( N );
		Item x = rndqueue[idx];
		for(int i=idx; i < N-1; i++){
			rndqueue[i] = rndqueue[i+1];
		}
         // shrink size of array if necessary
        if (N > 0 && N == rndqueue.length/4) resize(rndqueue.length/2); 
        

		return x;
	}

	public Item sample()     {                // return (but do not delete) a random item
		int idx = StdRandom.uniform( N);
		return rndqueue[idx];           
	}

	 
	  // resize the underlying array
    private void resize(int max) {
        assert max >= N;
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = rndqueue[i];
        }
        rndqueue = temp;
        first = 0;
        last  = N;
    }

	public Iterator<Item> iterator() {
        return new ArrayIterator( );
    }

     private class ArrayIterator  implements Iterator<Item> {
        private boolean[] sampledmat;
        private int numemit;

        public ArrayIterator( ) {
            sampledmat = new boolean [N];
            for (int i =0 ; i < N; i++){
            	sampledmat[i] = false;
            }
            numemit = 0;
        }

        public boolean hasNext()  { return numemit < N; }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) {throw new NoSuchElementException();}
            while(true) {
	            int idx = StdRandom.uniform(N);
	            Item x =  rndqueue[( idx) % rndqueue.length];  	
	            if (!sampledmat[idx]) {
	            	numemit++;
	            	sampledmat[idx] = true;
	            	return x;
	            }
        	}
        }
    }



	public static void main(String[] args){   // unit testing
		RandomizedQueue<String> s = new RandomizedQueue<String>();
		for (int i =0; i < 20; i++){
			String stg = String.format("Hello %d", i); 
    		s.enqueue(stg);	
    	}

    	for (String stg : s)
   			StdOut.printf("Iter ---%s\n",stg);


	}
}


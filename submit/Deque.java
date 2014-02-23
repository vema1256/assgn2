import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private int N;                // size of the stack
	private Node<Item> first;     // top of stack
	private Node<Item> last;     // top of stack

	// helper linked list class
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
		private Node<Item> back;

	}

   private void checkValidItem(Item item){
   		if (item == null ) { throw new  NullPointerException() ;}
   }

   public Deque()     {
   		N =0;
   		first =  null;
   		 
    }
   
   public boolean isEmpty() { return first== null && last == null;}                 // is the deque empty?
   public int size()        { return N;}                 // return the number of items on the deque
   
   public void addFirst(Item item){          // insert the item at the front
   	checkValidItem(item);
   		if( !isEmpty() ) {
	   		Node<Item> oldfirst = first;
			first = new Node<Item>();
			first.item = item;
			first.next = oldfirst;
			first.back = null;
			oldfirst.back = first;
		} else {

			first = last = new Node<Item>();
			first.item = last.item =  item;
			first.next = last.next = null;
			first.back = last.back = null;
 		}
		N++;
    }

   public void addLast(Item item) {          // insert the item at the end
   	checkValidItem(  item);
   	if( !isEmpty() ) {
		Node<Item> oldlast = last;
		last = new Node<Item>();
		last.item = item;
		last.next = null;
		oldlast.next = last;
		last.back = oldlast;
	} else {

		first = last = new Node<Item>();
		first.item = last.item =  item;
		first.next = last.next = null;
		first.back = last.back = null;
 		
	}
	N++;
    }

   public Item removeFirst() {               // delete and return the item at the front
   	 if(isEmpty() ) { throw new UnsupportedOperationException(); }
   	 Node<Item> second = first.next;
   	 Node<Item> oldfirst = first;
   	 //delete(first);
   	 first = second;
   	 first.back = null;
   	 N--;
   	 return oldfirst.item;
   }

   public Item removeLast() {                 // delete and return the item at the end
   	if(isEmpty() ) { throw new UnsupportedOperationException(); }
   	 Node<Item> secondlast = last.back;
   	 Node<Item> oldlast = last;
   	 //delete(last);
   	 secondlast.next = null;
   	 last = secondlast;
   	 N--;
   	 return oldlast.item;
   }

    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

     private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) {throw new NoSuchElementException();}
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }

 
 	public static void main(String[] args) {
        Deque<String> s = new Deque<String>();
    	s.addLast("Hello4");
   		s.addLast("Hello5");
   		s.addLast("Hello6");
   		for (String stg : s)
   			StdOut.printf("---%s\n",stg);
   		s.addFirst("Hello3");
   		s.addFirst("Hello2");
   		s.addFirst("Hello1");

		for (String stg : s)
   			StdOut.printf("---%s\n",stg);

   		s.removeLast();
   		s.removeFirst();

		for (String stg : s)
   			StdOut.printf("AfterDelt---%s\n",stg);

   		s.removeLast();
   		s.removeFirst();
   		s.removeLast();
   		s.removeFirst();
   		s.removeLast();
   		s.removeFirst();
       }

}

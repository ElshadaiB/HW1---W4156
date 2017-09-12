/*
*	Description: Class keeps track of k largest elements in a collection 
*	Author: Elshadai Biru - etb2119
*	Date: 11/21/2016
*	
*/
import java.util.*;
import java.lang.*;
import java.util.PriorityQueue;

public class KBestCounter<T extends Comparable<? super T>> {

    PriorityQueue<T> heap;	
    int k;
    Iterator<T> itr;
    LinkedList<T> list; 
    /*
     * Summary: Instantiates heap object with specified size
     * Parameters: k, size of the heap 
     * Return: void
     */
    public KBestCounter(int k) {
        this.k = k; 
        heap = new PriorityQueue<T>(k);
    }

    /*
     * Summary: Processes specified element and determines whether or not to add to k largest heap.
     * Parameters: Object x, element to be processed
     * Return: void
     */
    public void count(T x) {
        if(heap.peek() == null || heap.size() < k){
        	heap.offer(x); //automatically adds to heap if heap has less than k elements
        }
        else {
        	int i = heap.peek().compareTo(x);
        	if(i < 0){
        		heap.remove();
        		heap.offer(x);	//removes min element if new element is greater than it, adds new element to heap.
        	}
        }
    }

    /*
     * Summary: Returns list of k largest elements in descending order.
     * Parameters: None
     * Return: List of k largest elements.
     */
    public List<T> kbest() {
    	list = new LinkedList<T>();
    	itr = heap.iterator();
    	while(itr.hasNext()){
    		list.addFirst(heap.remove()); //pops minimum in heap and adds to list.
    	}
    	heap.addAll(list); //restores heap
    	return list;   
    }

}

import java.util.Arrays;
import java.util.List;

public class TestKBest{
   
    public static void main(String[] args) { 
        int k = 2;
        KBestCounter<Integer> counter = new KBestCounter<>(k);

        System.out.println("Inserting 1, 2, .... 14."); 
        for(int i = 1; i<=14; i++)
            counter.count(i);

        System.out.println("2-best should be [14,13]: "+counter.kbest());
        counter.count(50);
        
        System.out.println("Inserting another 50.");
        System.out.println("2-best should be [50,14]: "+counter.kbest());
        System.out.println("Printing again to check if heap restored: " + counter.kbest());

        System.out.println("Inserting even numbers from 4..99.");
        for (int i = 4; i < 100; i++){
        	if(i%2==0)
        	counter.count(i);
        }
            

        System.out.println("2-best should be [98,96]: " + counter.kbest());
        
        System.out.println("Inserting 20000");
        counter.count(20000);
        
        System.out.println("2-best should be [20000, 98]: " + counter.kbest());
    }
}


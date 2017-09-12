import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.util.LinkedList;


public class Problem2 {
	static StringBuilder tmp = new StringBuilder();
	static AvlTree tree = new AvlTree();
	static char c;
	static String readLine;
	static String word;
	static int linect  = 1;
	public static void main(String[] args) throws FileNotFoundException, IOException{

	BufferedReader br = new BufferedReader(new FileReader( "C:/Users/Elshadai Biru/workspace/Data2/src/hi.txt" ));
	while ((readLine = br.readLine()) != null){
		linect++;
    for (int i = 0; i < readLine.length(); i++){
    	c = readLine.charAt(i);
    	if(c == ' '){
    		word = (tmp.toString().toLowerCase());
    		tree.indexWord(word, linect);
    	}
    	else if (c == '.' || c == ',' || c == '\'' || c == '?'){
    		
    	}
    	else {
    		tmp.append(c);
    	}
    }   
}
	tree.printIndex();
	}
	 
}

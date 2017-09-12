import java.lang.*;
import java.util.LinkedList;
import java.util.List;







public class AvlTree{
    /**
     * Construct the tree.
     */
	 private AvlNode root;
	 
    public AvlTree( )
    {
        root = null;
    }
    private static class AvlNode
    {
    
    	 AvlElement element;      // The data in the node
         AvlNode  left;         // Left child
         AvlNode  right;        // Right child
         int               height;       // Height
     
            // Constructors
        AvlNode( AvlElement theElement )
        {
            this( theElement, null, null );
        }

        AvlNode( AvlElement theElement, AvlNode lt, AvlNode rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
            height   = 0;
        }
        	

        }
    public static class AvlElement{
     	private String word;
     	private LinkedList<Integer> l = new LinkedList<Integer>();
     	AvlElement(String x, int y){
     		word = x;
     		l.add(y);
     	}

   
    }
    private void insert( AvlElement x )
    {
        root = insert( x, root );
    }
    public void indexWord(String word, int line){
    	while(root != null){
    	if (this.contains(word, line)){
    		
    	}
    	else
    	insert(new AvlElement(word, line));
    	}
    }
    private AvlNode insert( AvlElement x, AvlNode t )
    {
        if( t == null )
            return new AvlNode( x, null, null );
        
        int compareResult = (x.word).compareTo( t.element.word );
        
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return balance( t );
    }
private static final int ALLOWED_IMBALANCE = 1;
    
    // Assume t is either balanced or within one of being balanced
    private AvlNode balance( AvlNode t )
    {
        if( t == null )
            return t;
        
        if( height( t.left ) - height( t.right ) > ALLOWED_IMBALANCE )
            if( height( t.left.left ) >= height( t.left.right ) )
                t = rotateWithLeftChild( t );
            else
                t = doubleWithLeftChild( t );
        else
        if( height( t.right ) - height( t.left ) > ALLOWED_IMBALANCE )
            if( height( t.right.right ) >= height( t.right.left ) )
                t = rotateWithRightChild( t );
            else
                t = doubleWithRightChild( t );

        t.height = Math.max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }
    
    public void checkBalance( )
    {
        checkBalance( root );
    }
    
    private int checkBalance( AvlNode t )
    {
        if( t == null )
            return -1;
        
        if( t != null )
        {
            int hl = checkBalance( t.left );
            int hr = checkBalance( t.right );
            if( Math.abs( height( t.left ) - height( t.right ) ) > 1 ||
                    height( t.left ) != hl || height( t.right ) != hr )
                System.out.println( "OOPS!!" );
        }
        
        return height( t );
    }
    private int height( AvlNode t )
    {
        return t == null ? -1 : t.height;
    }
    private AvlNode rotateWithLeftChild( AvlNode k2 )
    {
        AvlNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = Math.max( height( k1.left ), k2.height ) + 1;
        return k1;
    }

    /**
     * Rotate binary tree node with right child.
     * For AVL trees, this is a single rotation for case 4.
     * Update heights, then return new root.
     */
    private AvlNode rotateWithRightChild( AvlNode k1 )
    {
        AvlNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = Math.max( height( k2.right ), k1.height ) + 1;
        return k2;
    }

    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child.
     * For AVL trees, this is a double rotation for case 2.
     * Update heights, then return new root.
     */
    private AvlNode doubleWithLeftChild( AvlNode k3 )
    {
        k3.left = rotateWithRightChild( k3.left );
        return rotateWithLeftChild( k3 );
    }

    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child.
     * For AVL trees, this is a double rotation for case 3.
     * Update heights, then return new root.
     */
    private AvlNode doubleWithRightChild( AvlNode k1 )
    {
        k1.right = rotateWithLeftChild( k1.right );
        return rotateWithRightChild( k1 );
    }
    public boolean contains( String x, int y)
    {
        return contains( x, y, root );
    }
    private boolean contains( String x, int y, AvlNode t )
    {
        while( t != null )
        {
            int compareResult = x.compareTo( t.element.word );
            
            if( compareResult < 0 )
                t = t.left;
            else if( compareResult > 0 )
                t = t.right;
            else{
            	if(t.element.l.contains(y)){
            		
            	}
            	else {
            	t.element.l.add(y);
                return true; 
                // Match
            	}
            }
            }

        return false;   // No match
    }
    public List getLinesForWord(String word){
    	AvlNode p = getWord(word, root);
    	  	 return p.element.l;
    	
    }
    public void printIndex(){
    	final StringBuilder currstr = new StringBuilder();
    		printIndex2(root);
    	}
    private void printIndex2(AvlNode x){
    	if(x == null){
			return;
		}
    	System.out.println(x.element + ":");
    	for(int i = 0; i < x.element.l.size(); i++){
    		System.out.println(x.element.l.get(i));
    	}
    	printIndex2(x.left);
    	printIndex2(x.right);
    	return;
    }
    	
    
    public AvlNode getWord(String word, AvlNode t){
    	while( t != null )
        {
            int compareResult = word.compareTo( t.element.word );
            
            if( compareResult < 0 )
                t = t.left;
            else if( compareResult > 0 )
                t = t.right;
            else{
                return t; 
                // Match
            	}
            }
    	return null;
            }



    }

    

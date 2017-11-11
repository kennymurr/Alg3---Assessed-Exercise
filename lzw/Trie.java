import java.util.ArrayList;

public class Trie {
	
	// create root of the trie
	private Node root; 
	
	public Trie() {
		// null character in the root
		root = new Node(Character.MIN_VALUE); 
	}        
	
	// possible outcomes of a search
	private enum Outcomes {INSERT, NOTHING, UNKNOWN}
	
	
	public void search(String w)
	{
		Outcomes outcome = Outcomes.UNKNOWN;
		int i = 0;//position in word 'w'
		Node current = root;
		Node next = current.getChild();
		while (outcome == Outcomes.UNKNOWN)
		{
			if (next!=null && next.getLetter()==w.charAt(i))//chars match
			{
				current = next;
				next = current.getChild();
				i++;
			}else if (next!=null)//try next sibling
			{
				next = next.getSibling();
			}else//if no siblings are same as w.charat(i)
			{
				insert(w.charAt(i), current);//insert w.charAt(i) to the trie
				outcome = Outcomes.INSERT;
			}
		}		
	}
	
	
	public void insert(char c, Node n)
	{
		if (n==null)
		{
			n=root;
		}
		Node current = n;
		Node newNode = new Node(c);
		newNode.setSibling(current.getChild());
		current.setChild(newNode);
	}
	
	// delete method to be added
	// traverse method (extracting words) to be added
}

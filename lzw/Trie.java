public class Trie {
	
	// create root of the trie
	private Node root; 
	
	public Trie() 
	{
		// null character in the root
		root = new Node(Character.MIN_VALUE); 
	}        
	
	// possible outcomes of a search
	private enum Outcomes {INSERT, ITERATE, UNKNOWN}
	
	public Node search(char c, Node n)
	{
		if(n==null)
		{
			n=root;
		}
		Node current = n;
		Node next = n.getChild();//Node to be used in comparisons is child of node passed
		Outcomes outcome = Outcomes.UNKNOWN;
		while (outcome == Outcomes.UNKNOWN)
		{	
			if (next!=null && next.getLetter()==c)//If next is a node and has character c
			{
				outcome=Outcomes.ITERATE;//Character has been located in trie, move on to next
				return next;
			}else if (next!=null)
			{
				next=next.getSibling();

			}else
			{
				outcome=Outcomes.INSERT;
				return current;
			}
		}
		return null;
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
		
		/*
		if ((int)c > 5)
		{
			//System.out.println(current);
			System.out.println(newNode.getSibling().getLetter());
			System.out.println(newNode.getSibling().getSibling().getLetter());
			System.out.println(newNode.getSibling().getSibling().getSibling().getLetter());
			System.out.println("Trie" + n.getChild().getLetter());
		}
		*/
	}
}

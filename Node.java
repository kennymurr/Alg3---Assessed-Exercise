
public class Node {
	
	private char letter; // label on incoming branch
	private Node sibling; // next sibling (when it exists)
	private Node child; // first child (when it exists)
	
	/** create a new node with letter c */
	public Node(char c)
	{
		letter = c;
		sibling = null;
		child = null;
	}
	
	public char getLetter() {
		return this.letter;
	}

	
	public Node getSibling() {
		return this.sibling;
	}
	
	public Node getChild() {
		return this.child;
	}

	public void setLetter(char c) {
		this.letter = c;
	}

	public void setSibling(Node n) {
		this.sibling = n;
	}
	
	public void setChild(Node c) {
		this.child = c;
	}
	
}

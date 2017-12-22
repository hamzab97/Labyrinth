

/**
 * 
 * @author hamza
 * 250857725
 * CS2210
 * Assignment 5
 * Class number: 13
 * @param <N>
 *
 */
public class Node {
	//class respresents the node of the graph
	
	private int name;
	private boolean mark;

	/**
	 * constructor for the class. creates node with the given name
	 * @param name of the node is an integer value between 0 and n-1 where n is the number of vertices in the graph ot which the node belongs
	 * a node can be marked with a value that is either true or false using teh following method
	 * 
	 */
	public Node(int name) {
		this.name = name;
	}
	
	/**
	 * marks the node with the specified value
	 * @param mark - either true or false
	 */
	public void setMark (boolean mark) {
		this.mark = mark;
	}
	
	/**
	 * returns the value with which the node has been marked
	 * @return
	 */
	public boolean getMark() {
		return mark;
	}
	
	/**
	 * returns the name of the node
	 * @return
	 */
	public int getName() {
		return name;
	}

}

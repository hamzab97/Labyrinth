/**
 * 
 * @author hamza
 * 250857725
 * CS2210
 * Assignment 5
 * Class number: 13
 *
 */
public class Edge{
	//class represents an edge of the graph
	
	private String type;
	private Node u, v;

	/**
	 * constructor
	 * first two parameters are the end points of the edge
	 * last parameter is the type of the edge, can be corridor, wall, thickWall or metalWall
	 * @param u
	 * @param v
	 * @param type
	 */
	public Edge (Node u, Node v, String type) {
		this.u = u;//first end point
		this.v = v;//2nd end point
		this.type = type;
	}
	
	/**
	 * returns the first endpoint of the edge
	 * @return
	 */
	public Node firstEndpoint() {
		return u;
	}
	
	/**
	 * returns the second endpoint of the edge
	 * @return
	 */
	public Node secondEndpoint() {
		return v;
	}
	
	/**
	 * returns the type of the edge
	 * @return
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * sets the type of the edge to the specified value
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
}

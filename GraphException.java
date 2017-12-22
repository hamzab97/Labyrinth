/**
 * 
 * @author hamza
 * 250857725
 * CS2210
 * Assignment 5
 * Class number: 13
 *
 */
public class GraphException extends Exception{//graph expcetion class, throw new exception with message
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GraphException(String message) {
		super(message);
	}
	public GraphException(String message, Throwable throwable) {
		super(message, throwable);
	}
}

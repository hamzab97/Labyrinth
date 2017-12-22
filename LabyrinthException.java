/**
 * 
 * @author hamza
 * 250857725
 * CS2210
 * Assignment 5
 * Class number: 13
 *
 */
public class LabyrinthException extends Exception{//Labyrinth expcetion class, throw new exception with message
	public LabyrinthException(String message) {
		super(message);
	}
	public LabyrinthException(String message, Throwable throwable) {
		super(message, throwable);
	}
}

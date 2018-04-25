package FitnessTracker.Exceptions;

import java.io.IOException;
/*
 * @author Garth Terlizzi III
 */
public class UserAlreadyInDatabaseException extends IOException{
	public UserAlreadyInDatabaseException(String message) {
		super("message");
	}
}

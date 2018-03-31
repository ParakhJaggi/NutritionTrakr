package FitnessTracker.Exceptions;

import java.io.IOException;

public class UserAlreadyInDatabaseException extends IOException{
	public UserAlreadyInDatabaseException(String message) {
		super("message");
	}
}

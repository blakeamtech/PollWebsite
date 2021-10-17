package Exceptions;

public class InvalidPollStateException extends AssignmentException{

    public InvalidPollStateException(String status, String action){
        super(String.format("The poll has the wrong status (%s) for the current action (%s)", status, action));
    }
}

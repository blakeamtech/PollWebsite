package Exceptions;

public class InvalidSessionException extends AssignmentException{

    public InvalidSessionException(){
        super("The given session is invalid.");
    }
}

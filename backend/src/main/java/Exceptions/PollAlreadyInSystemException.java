package Exceptions;

public class PollAlreadyInSystemException extends AssignmentException{

    public PollAlreadyInSystemException(){
        super("There is already a poll in the system.");
    }

}

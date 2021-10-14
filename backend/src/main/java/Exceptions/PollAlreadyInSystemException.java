package Exceptions;

public class PollAlreadyInSystemException extends Exception{

    public PollAlreadyInSystemException(){
        super("There is already a poll in the system.");
    }

}

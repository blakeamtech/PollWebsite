package Exceptions;

public class InvalidChoiceException extends AssignmentException{

    public InvalidChoiceException(){
        super("The given choice is empty or invalid.");
    }
}

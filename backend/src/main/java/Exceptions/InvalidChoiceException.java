package Exceptions;

public class InvalidChoiceException extends Exception{

    public InvalidChoiceException(){
        super("The given choice is empty or invalid.");
    }
}

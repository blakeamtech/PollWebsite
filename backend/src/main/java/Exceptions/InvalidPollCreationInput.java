package Exceptions;

public class InvalidPollCreationInput extends Exception{

    public InvalidPollCreationInput(){
        super("Given input for creating the poll is invalid.");
    }

}

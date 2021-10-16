package Exceptions;

public class InvalidPollCreationInput extends AssignmentException{

    public InvalidPollCreationInput(){
        super("Given input for creating the poll is invalid.");
    }

}

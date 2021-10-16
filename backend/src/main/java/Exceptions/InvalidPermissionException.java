package Exceptions;

public class InvalidPermissionException extends AssignmentException{

    public InvalidPermissionException(){
        super("You are not poll manager, and therefore cannot perform this action.");
    }
}

package Exceptions;

public class InvalidPermissionException extends AssignmentException{

    public InvalidPermissionException(){
        super("Invalid permission for action.");
    }
}

package Storage.responses;

import Exceptions.InvalidPermissionException;

public class DBResponse {

    private DBResponse() throws InvalidPermissionException {
        throw new InvalidPermissionException();
    }


}

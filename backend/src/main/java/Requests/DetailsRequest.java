package Requests;

import Responses.Response;


/**
 * Like the Results request, the Details request requires no permissions
 */
public class DetailsRequest implements Request {

    DetailsRequest(){};

    @Override
    public Response call() {
        /**
         * Return details
         */
        return null;
    }
}

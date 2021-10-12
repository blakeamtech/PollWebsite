package Requests;

import Responses.Response;

public class InvalidRequest implements IRequest{

    InvalidRequest(){};

    @Override
    public Response call() {
        return null;
    }
}
